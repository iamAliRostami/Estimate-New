package com.leon.estimate_new.fragments.forms;

import static com.leon.estimate_new.helpers.Constants.SECOND_FRAGMENT;
import static com.leon.estimate_new.helpers.MyApplication.getLocationTracker;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.esri.arcgisruntime.geometry.GeodeticCurveType;
import com.esri.arcgisruntime.geometry.Geometry;
import com.esri.arcgisruntime.geometry.GeometryEngine;
import com.esri.arcgisruntime.geometry.LinearUnit;
import com.esri.arcgisruntime.geometry.LinearUnitId;
import com.esri.arcgisruntime.geometry.Point;
import com.esri.arcgisruntime.geometry.PointCollection;
import com.esri.arcgisruntime.geometry.Polyline;
import com.esri.arcgisruntime.geometry.SpatialReferences;
import com.esri.arcgisruntime.mapping.ArcGISMap;
import com.esri.arcgisruntime.mapping.Viewpoint;
import com.esri.arcgisruntime.mapping.view.Callout;
import com.esri.arcgisruntime.mapping.view.DefaultMapViewOnTouchListener;
import com.esri.arcgisruntime.mapping.view.Graphic;
import com.esri.arcgisruntime.mapping.view.GraphicsOverlay;
import com.esri.arcgisruntime.symbology.SimpleLineSymbol;
import com.esri.arcgisruntime.symbology.SimpleMarkerSymbol;
import com.leon.estimate_new.R;
import com.leon.estimate_new.databinding.FragmentMapDescriptionBinding;
import com.leon.estimate_new.tables.ExaminerDuties;
import com.leon.estimate_new.utils.gis.OsmMapLayer;

import java.util.Arrays;

public class MapDescriptionFragment extends Fragment {
    private FragmentMapDescriptionBinding binding;
    private Callback formActivity;
    private int pointIndex;


    public MapDescriptionFragment() {
    }

    public static MapDescriptionFragment newInstance() {
        return new MapDescriptionFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        formActivity.setTitle(getString(R.string.app_name).concat(" / ").concat("صفحه پنجم"), false);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMapDescriptionBinding.inflate(inflater, container, false);
        initialize();
        return binding.getRoot();
    }

    private void initialize() {
        setOnClickListener();
        initializeMap();
    }

    private void setOnClickListener() {
        binding.buttonPre.setOnClickListener(v -> formActivity.setOnPreClickListener(SECOND_FRAGMENT));
        binding.buttonEditCrooki.setOnClickListener(v -> {
            formActivity.setMapDescription();
        });
    }

    private void initializeMap() {
        final ArcGISMap map = new ArcGISMap();
//        final ArcGISMap map = new ArcGISMap(BasemapStyle.ARCGIS_IMAGERY);
        binding.mapView.setMap(map);
//        binding.mapView.getMap().getBasemap().getBaseLayers().add(new OpenStreetMapLayer());
        binding.mapView.getMap().getBasemap().getBaseLayers().add(new OsmMapLayer().createLayer());
//        binding.mapView.getMap().getBasemap().getBaseLayers().add(new GoogleMapLayer().createLayer(MapType.VECTOR));

        binding.mapView.setMagnifierEnabled(true);
        binding.mapView.setCanMagnifierPanMap(true);

        AsyncTask.execute(() -> {
            while (getLocationTracker(requireActivity()).getLocation() == null)
                binding.progressBar.setVisibility(View.VISIBLE);
            binding.mapView.setViewpoint(new Viewpoint(getLocationTracker(requireActivity()).getLatitude()
                    , getLocationTracker(requireActivity()).getLongitude(), 3600));
            Log.e("long 1", String.valueOf(getLocationTracker(requireActivity()).getLongitude()));
            requireActivity().runOnUiThread(() -> binding.progressBar.setVisibility(View.GONE));
        });
        onMapClickListener();
    }

    @SuppressLint("ClickableViewAccessibility")
    private void onMapClickListener() {
        binding.mapView.setOnTouchListener(new DefaultMapViewOnTouchListener(requireContext(), binding.mapView) {
            @Override
            public boolean onSingleTapConfirmed(MotionEvent event) {
                android.graphics.Point point = new android.graphics.Point((int) event.getX(), (int) event.getY());
                final Point mapPoint = mMapView.screenToLocation(point);
                addPolygon(mapPoint);
                return super.onSingleTapConfirmed(event);
            }

            @Override
            public void onLongPress(MotionEvent event) {
                if (pointIndex % 2 == 0)
                    binding.mapView.getGraphicsOverlays().clear();
                addPoint(mMapView.screenToLocation(new android.graphics.Point((int) event.getX(),
                        (int) event.getY())));
                pointIndex++;
                super.onLongPress(event);
            }
        });
    }

    private void addPoint(Point graphicPoint) {
        final GraphicsOverlay graphicsOverlay = new GraphicsOverlay();
        binding.mapView.getGraphicsOverlays().add(graphicsOverlay);
        final SimpleMarkerSymbol symbol = new SimpleMarkerSymbol(SimpleMarkerSymbol.Style.CIRCLE,
                pointIndex % 2 == 0 ? Color.RED : Color.BLUE, 12);
        final Graphic graphic = new Graphic(graphicPoint, symbol);
        graphicsOverlay.getGraphics().add(graphic);
    }

    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    private void addPolygon(Point mapPoint) {
        final GraphicsOverlay graphicsOverlay = new GraphicsOverlay();
        final Graphic path = new Graphic(mapPoint);
        path.setSymbol(new SimpleLineSymbol(SimpleLineSymbol.Style.DASH, Color.YELLOW, 500));
        graphicsOverlay.getGraphics().add(path);


        final Graphic endLocation = new Graphic();


        final Point destination = (Point) GeometryEngine.project(mapPoint, SpatialReferences.getWgs84());


        Point point = new Point(getLocationTracker(requireActivity()).getLatitude()
                , getLocationTracker(requireActivity()).getLongitude(),SpatialReferences.getWgs84());
        endLocation.setGeometry(destination);
        // create a straight line path between the start and end locations
        PointCollection points = new PointCollection(Arrays.asList(point, destination), SpatialReferences.getWgs84());
        Polyline polyline = new Polyline(points);
        // densify the path as a geodesic curve and show it with the path graphic
        Geometry pathGeometry = GeometryEngine
                .densifyGeodetic(polyline, 1, new LinearUnit(LinearUnitId.KILOMETERS), GeodeticCurveType.GEODESIC);
        path.setGeometry(pathGeometry);
        // calculate the path distance
        double distance = GeometryEngine.lengthGeodetic(pathGeometry, new LinearUnit(LinearUnitId.KILOMETERS), GeodeticCurveType.GEODESIC);


        final TextView contentTextView = new TextView(requireContext());
        contentTextView.setTextColor(Color.BLACK);
        contentTextView.setSingleLine();
        // format coordinates to 2 decimal places
        contentTextView.setText("Distance: " + String.format("%.2f", distance));
        final Callout callout = binding.mapView.getCallout();
        callout.setLocation(mapPoint);
        callout.setContent(contentTextView);
        callout.show();

    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof Activity) {
            formActivity = (Callback) context;
        }
    }

    @Override
    public void onResume() {
        binding.mapView.resume();
        super.onResume();
    }

    @Override
    public void onPause() {
        binding.mapView.pause();
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding.mapView.dispose();
    }

    public interface Callback {
        void setOnPreClickListener(int position);

        void setTitle(String title, boolean showMenu);

        ExaminerDuties getExaminerDuty();

        void setMapDescription();
    }
}