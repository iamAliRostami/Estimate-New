package com.leon.estimate_new.fragments.forms;

import static com.leon.estimate_new.helpers.Constants.SECOND_FRAGMENT;
import static com.leon.estimate_new.helpers.MyApplication.getLocationTracker;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.esri.arcgisruntime.geometry.GeodeticCurveType;
import com.esri.arcgisruntime.geometry.GeometryEngine;
import com.esri.arcgisruntime.geometry.LinearUnit;
import com.esri.arcgisruntime.geometry.LinearUnitId;
import com.esri.arcgisruntime.geometry.Point;
import com.esri.arcgisruntime.geometry.PointCollection;
import com.esri.arcgisruntime.geometry.Polyline;
import com.esri.arcgisruntime.geometry.SpatialReferences;
import com.esri.arcgisruntime.mapping.ArcGISMap;
import com.esri.arcgisruntime.mapping.Viewpoint;
import com.esri.arcgisruntime.mapping.view.DefaultMapViewOnTouchListener;
import com.esri.arcgisruntime.mapping.view.Graphic;
import com.esri.arcgisruntime.mapping.view.GraphicsOverlay;
import com.esri.arcgisruntime.symbology.PictureMarkerSymbol;
import com.esri.arcgisruntime.symbology.SimpleLineSymbol;
import com.leon.estimate_new.R;
import com.leon.estimate_new.databinding.FragmentMapDescriptionBinding;
import com.leon.estimate_new.tables.CalculationUserInput;
import com.leon.estimate_new.tables.ExaminerDuties;
import com.leon.estimate_new.utils.gis.OsmMapLayer;

public class MapDescriptionFragment extends Fragment {
    private final PointCollection points = new PointCollection(SpatialReferences.getWgs84());
    private final GraphicsOverlay graphicsOverlay = new GraphicsOverlay();
    private final Graphic path = new Graphic();
    private Callback formActivity;
    private int pointWater = -1, pointSiphon = -1;
    private FragmentMapDescriptionBinding binding;

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
        initializeMap();
        setOnClickListener();
        binding.editTextDescription.setText(formActivity.getExaminerDuty().mapDescription);
    }

    private void setOnClickListener() {
        binding.imageViewRefresh.setOnClickListener(v -> {
            clearMap();
            initializeMap();
        });
        binding.buttonPre.setOnClickListener(v -> formActivity.setOnPreClickListener(SECOND_FRAGMENT));
        binding.buttonEditCrooki.setOnClickListener(v -> {
            clearMap();
            formActivity.setMapDescription(binding.editTextDescription.getText().toString());
        });
    }

    private void clearMap() {
        points.clear();
        addPolygon(new Point(0, 0));
        points.clear();
        binding.mapView.getGraphicsOverlays().clear();
        pointWater = pointSiphon = -1;
    }

    private void initializeMap() {
//        final ArcGISMap map = new ArcGISMap(BasemapStyle.ARCGIS_IMAGERY);
//        binding.mapView.getMap().getBasemap().getBaseLayers().add(new OpenStreetMapLayer());
//        binding.mapView.getMap().getBasemap().getBaseLayers().add(new GoogleMapLayer().createLayer(MapType.VECTOR));
        binding.mapView.setMap(new ArcGISMap());
        binding.mapView.getMap().getBasemap().getBaseLayers().add(new OsmMapLayer().createLayer());

        binding.mapView.setMagnifierEnabled(true);
        binding.mapView.setCanMagnifierPanMap(true);

        AsyncTask.execute(() -> {
            while (getLocationTracker(requireActivity()).getLocation() == null)
                binding.progressBar.setVisibility(View.VISIBLE);
            binding.mapView.setViewpoint(new Viewpoint(getLocationTracker(requireActivity()).getLatitude()
                    , getLocationTracker(requireActivity()).getLongitude(), 3600));
            requireActivity().runOnUiThread(() -> binding.progressBar.setVisibility(View.GONE));
        });
        initializeOverlays();
        onMapClickListener();
    }

    private void initializeOverlays() {
        try {
            binding.mapView.getGraphicsOverlays().add(graphicsOverlay);
            path.setSymbol(new SimpleLineSymbol(SimpleLineSymbol.Style.DASH_DOT, Color.YELLOW, 5));
            graphicsOverlay.getGraphics().add(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (formActivity.getCalculationUserInput().x3 != 0)
            addPoint(new Point(formActivity.getCalculationUserInput().x3,
                    formActivity.getCalculationUserInput().y3, SpatialReferences.getWgs84()));
    }

    @SuppressLint("ClickableViewAccessibility")
    private void onMapClickListener() {
        binding.mapView.setOnTouchListener(new DefaultMapViewOnTouchListener(requireContext(), binding.mapView) {
            @Override
            public void onLongPress(MotionEvent event) {
                if (pointSiphon > 0) {
                    binding.mapView.getGraphicsOverlays().remove(pointWater);
                    binding.mapView.getGraphicsOverlays().remove(pointSiphon);
                    pointWater = pointSiphon = -1;
                }
                addPoint(mMapView.screenToLocation(new android.graphics.Point((int) event.getX(),
                        (int) event.getY())));
                super.onLongPress(event);
            }

            @Override
            public boolean onSingleTapConfirmed(MotionEvent event) {
                android.graphics.Point clickLocation = new android.graphics.Point(Math.round(event.getX()),
                        Math.round(event.getY()));
                addPolygon(mMapView.screenToLocation(clickLocation));

                // calculate the path distance
//                double distance = GeometryEngine.lengthGeodetic(pathGeometry, mUnitOfMeasurement, GeodeticCurveType.GEODESIC);

//                // create a textview for the callout
//                TextView calloutContent = new TextView(requireContext());
//                calloutContent.setTextColor(Color.BLACK);
//                calloutContent.setSingleLine();
//                // format coordinates to 2 decimal places
//                calloutContent.setText("Distance: " + String.format("%.2f", distance) + mUnits);
//                final Callout callout = mMapView.getCallout();
//                callout.setLocation(mapPoint);
//                callout.setContent(calloutContent);
//                callout.show();

                return super.onSingleTapConfirmed(event);
            }
        });
    }

    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    private void addPolygon(Point mapPoint) {
        final Point destination = (Point) GeometryEngine.project(mapPoint, SpatialReferences.getWgs84());
        points.add(destination);
        final Polyline polyline = new Polyline(points);
        path.setGeometry(GeometryEngine.densifyGeodetic(polyline, 1,
                new LinearUnit(LinearUnitId.KILOMETERS), GeodeticCurveType.GEODESIC));
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private void addPoint(Point graphicPoint) {
        final GraphicsOverlay graphicsOverlay = new GraphicsOverlay();
        binding.mapView.getGraphicsOverlays().add(graphicsOverlay);
//        final SimpleMarkerSymbol symbol = new SimpleMarkerSymbol(SimpleMarkerSymbol.Style.CIRCLE,
//                pointWater < 0 ? Color.BLUE : Color.RED, 12);
        final BitmapDrawable drawable = (BitmapDrawable) ContextCompat.getDrawable(requireContext(),
                pointWater < 0 ? R.drawable.map_water_drop_point : R.drawable.map_siphon_drop_point);
        final PictureMarkerSymbol symbol = new PictureMarkerSymbol(drawable);
        final Graphic graphic = new Graphic(graphicPoint, symbol);
        graphicsOverlay.getGraphics().add(graphic);
        if (pointWater == -1) {
            pointWater = binding.mapView.getGraphicsOverlays().size() - 1;
            formActivity.setWaterLocation(((Point) GeometryEngine.project(graphicPoint, SpatialReferences.getWgs84())));
        } else pointSiphon = binding.mapView.getGraphicsOverlays().size() - 2;
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

        void setMapDescription(String description);

        void setWaterLocation(Point point);

        CalculationUserInput getCalculationUserInput();
    }
}