package com.leon.estimate_new.fragments.forms;

import static com.leon.estimate_new.enums.MapLayerType.GIS_PARCEL;
import static com.leon.estimate_new.enums.MapLayerType.GIS_SANITATION_TRANSFER;
import static com.leon.estimate_new.enums.MapLayerType.GIS_WATER_PIPE;
import static com.leon.estimate_new.enums.MapLayerType.GIS_WATER_TRANSFER;
import static com.leon.estimate_new.enums.SharedReferenceKeys.MAP_TYPE;
import static com.leon.estimate_new.enums.SharedReferenceKeys.TOKEN_FOR_GIS;
import static com.leon.estimate_new.helpers.Constants.MAP_SELECTED;
import static com.leon.estimate_new.helpers.Constants.TECHNICAL_INFO_FRAGMENT;
import static com.leon.estimate_new.helpers.MyApplication.getApplicationComponent;
import static com.leon.estimate_new.helpers.MyApplication.getLocationTracker;
import static com.leon.estimate_new.utils.DifferentCompanyManager.getActiveCompanyName;
import static com.leon.estimate_new.utils.DifferentCompanyManager.getMapUrl;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.view.MenuProvider;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;

import com.leon.estimate_new.BuildConfig;
import com.leon.estimate_new.R;
import com.leon.estimate_new.databinding.FragmentMapDescriptionBinding;
import com.leon.estimate_new.enums.MapLayerType;
import com.leon.estimate_new.tables.CalculationUserInput;
import com.leon.estimate_new.tables.ExaminerDuties;
import com.leon.estimate_new.tables.Place;
import com.leon.estimate_new.utils.gis.GetGisPoint;
import com.leon.estimate_new.utils.gis.GetGisToken;
import com.leon.estimate_new.utils.gis.GetGisXY;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.events.MapEventsReceiver;
import org.osmdroid.tileprovider.tilesource.OnlineTileSourceBase;
import org.osmdroid.tileprovider.tilesource.XYTileSource;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.util.MapTileIndex;
import org.osmdroid.views.CustomZoomButtonsController;
import org.osmdroid.views.overlay.MapEventsOverlay;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.Polyline;
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;

import java.io.File;
import java.util.ArrayList;

public class MapDescriptionFragment extends Fragment implements View.OnClickListener {
    private final MapLayerType[] layerTypes = {GIS_WATER_PIPE, GIS_WATER_TRANSFER,
            GIS_SANITATION_TRANSFER, GIS_PARCEL};
    private final ArrayList<GeoPoint> polygonPoint = new ArrayList<>();
    private final double[] latLong = new double[2];
    private int pointWater, pointSiphon, layerCounter = 0, polygonIndex;
    private Callback formActivity;
    private FragmentMapDescriptionBinding binding;

    public MapDescriptionFragment() {
    }

    public static MapDescriptionFragment newInstance() {
        return new MapDescriptionFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMapDescriptionBinding.inflate(inflater, container, false);
        initialize();
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        formActivity.setTitle(getString(R.string.app_name).concat(" / ").concat("صفحه پنجم"), false);
        requireActivity().addMenuProvider(new MenuProvider() {
            @Override
            public void onCreateMenu(@NonNull Menu menu, @NonNull MenuInflater menuInflater) {
                menuInflater.inflate(R.menu.map_menu, menu);
                menu.getItem(getApplicationComponent().SharedPreferenceModel()
                        .getIntData(MAP_TYPE.getValue())).setChecked(true);
            }

            @Override
            public boolean onMenuItemSelected(@NonNull MenuItem menuItem) {
                menuItem.setChecked(!menuItem.isChecked());
                final int itemId = menuItem.getItemId();
                if (itemId == R.id.vector) {
                    initializeBaseMap();
                    return true;
                } else if (itemId == R.id.roads) {
                    initializeBaseMap();
                    return true;
                } else if (itemId == R.id.satellite) {
                    initializeBaseMap();
                    return true;
                } else if (itemId == R.id.osm) {
                    initializeBaseMap();
                    return true;
                }
                return false;
            }
        }, getViewLifecycleOwner(), Lifecycle.State.RESUMED);
    }

    private void initialize() {
        initializeMap();
        setOnClickListener();
    }

    private void initializeMap() {
        Configuration.getInstance().load(requireContext(), PreferenceManager.getDefaultSharedPreferences(requireContext()));
        //TODO
        Configuration.getInstance().setUserAgentValue(BuildConfig.APPLICATION_ID);
        // Set the tile cache path (where map data will be stored)
        File file = new File(Environment.getExternalStorageDirectory(), "osmdroid/tiles");
        Configuration.getInstance().setOsmdroidTileCache(file);
//
        binding.mapView.setTileSource(new XYTileSource("Mapnik", 0, 18, 256, ".png", new String[]{}));
//        binding.mapView.setUseDataConnection(false);
        //TODO
        initializeBaseMap();
        initializeOverlays();
        onMapTouchListener();
    }

    private void setOnClickListener() {
        binding.imageViewMyLocation.setOnClickListener(this);
        binding.imageViewShowLayer.setOnClickListener(this);
        binding.imageViewRefresh.setOnClickListener(this);
        binding.buttonEditCrooki.setOnClickListener(this);
        binding.buttonPre.setOnClickListener(this);
    }

    @SuppressLint("ClickableViewAccessibility")
    private void onMapTouchListener() {
        binding.mapView.getOverlays().add(new MapEventsOverlay(new MapEventsReceiver() {
            @Override
            public boolean singleTapConfirmedHelper(GeoPoint p) {
                addPolygon(p);
                return false;
            }

            @Override
            public boolean longPressHelper(GeoPoint p) {
                addPoint(p);
                return false;
            }
        }));
    }

    private void addPoint(GeoPoint geoPoint) {
        final Marker startMarker = new Marker(binding.mapView);
        startMarker.setPosition(geoPoint);
        startMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        if (pointSiphon == 0 && pointWater != 0) {
            startMarker.setIcon(AppCompatResources.getDrawable(requireContext(), R.drawable.map_siphon_drop_point_1));
            binding.mapView.getOverlays().add(startMarker);
            pointSiphon = binding.mapView.getOverlays().size() - 2;
        } else if (pointSiphon != 0) {
            binding.mapView.getOverlays().remove(pointWater);
            binding.mapView.getOverlays().remove(pointSiphon);
            pointSiphon = 0;
            pointWater = 0;
        }
        if (pointWater == 0) {
            formActivity.setWaterLocation(geoPoint);
            startMarker.setIcon(AppCompatResources.getDrawable(requireContext(), R.drawable.map_water_drop_point));
            binding.mapView.getOverlays().add(startMarker);
            pointWater = binding.mapView.getOverlays().size() - 1;
        }
    }

    private void clearMap() {
        addPolygon(new GeoPoint(0, (double) 0));
        binding.mapView.getOverlays().clear();
        polygonIndex = pointWater = pointSiphon = 0;
        polygonPoint.clear();
    }

    private void captureScreenshotAsync() {
        binding.mapView.destroyDrawingCache();
        binding.mapView.setDrawingCacheEnabled(true);
        MAP_SELECTED = binding.mapView.getDrawingCache(true);
    }

    private void initializeBaseMap() {
        Configuration.getInstance().load(requireContext(), PreferenceManager.getDefaultSharedPreferences(requireContext()));
//        binding.mapView.setTileSource(new CustomOnlineTileSource());
        final OnlineTileSourceBase custom = new OnlineTileSourceBase("custom",
                0, 19, 256, ".png", new String[]{
                getMapUrl(getActiveCompanyName())}) {
            @Override
            public String getTileURLString(long aTile) {
                return getBaseUrl() + MapTileIndex.getZoom(aTile) + "/" + MapTileIndex.getX(aTile)
                        + "/" + MapTileIndex.getY(aTile) + mImageFilenameEnding;
            }
        };
        binding.mapView.setTileSource(custom);
//        binding.mapView.setBuiltInZoomControls(true);
        binding.mapView.getZoomController().
                setVisibility(CustomZoomButtonsController.Visibility.SHOW_AND_FADEOUT);
        binding.mapView.setMultiTouchControls(true);
        showCurrentLocation();
        requireActivity().runOnUiThread(() -> binding.progressBar.setVisibility(View.GONE));
    }

    private void showCurrentLocation() {
        IMapController mapController = binding.mapView.getController();
        double latitude;
        double longitude;
        GeoPoint startPoint;
        if (getLocationTracker(requireActivity()).getLocation() != null) {
            mapController.setZoom(19.5);
            latitude = getLocationTracker(requireActivity()).getLatitude();
            longitude = getLocationTracker(requireActivity()).getLongitude();
            startPoint = new GeoPoint(latitude, longitude);
            formActivity.setCurrentLocation(startPoint);
        } else {
            mapController.setZoom(8.5);
            latitude = 32.65;
            longitude = 51.66;
            startPoint = new GeoPoint(latitude, longitude);
        }
        mapController.setCenter(startPoint);

        MyLocationNewOverlay locationOverlay =
                new MyLocationNewOverlay(new GpsMyLocationProvider(requireContext()), binding.mapView);
        Bitmap icon = BitmapFactory.decodeResource(getResources(), R.drawable.my_location);
        locationOverlay.setPersonIcon(icon);
        locationOverlay.enableMyLocation();
        binding.mapView.getOverlays().add(locationOverlay);

    }

    private void initializeOverlays() {
        if (formActivity.getCalculationUserInput().x3 != 0)
            addPoint(new GeoPoint(formActivity.getCalculationUserInput().x3,
                    formActivity.getCalculationUserInput().y3));
    }

    private void addPolygon(GeoPoint geoPoint) {
        final Polyline line = new Polyline(binding.mapView);
        if (polygonIndex != 0) {
            binding.mapView.getOverlays().remove(polygonIndex);
        }
        binding.mapView.getOverlays().add(line);
        polygonPoint.add(geoPoint);
        polygonPoint.add(polygonPoint.get(0));
        line.setPoints(polygonPoint);
        polygonPoint.remove(polygonPoint.size() - 1);
        polygonIndex = binding.mapView.getOverlays().size() - 1;
    }


    private void getXY() {/*"5549607416311" "1127239616317"*/
        new GetGisXY(requireContext(), formActivity.getExaminerDuty().billId != null ?
                formActivity.getExaminerDuty().billId : formActivity.getExaminerDuty().neighbourBillId,
                this).execute(requireActivity());
    }

    public void setXY(Place place) {
        if (place.X != 0 && place.Y != 0) {
//            final String utm = "39 S ".concat(String.valueOf(place.X)).concat(" ")
//                    .concat(String.valueOf(place.Y));
//            final CoordinateConversion conversion = new CoordinateConversion();
//            latLong = conversion.utm2LatLon(utm);
//            latLong [0] = place.Y;
//            latLong [1] = place.X;
            //TODO utm or LatLang
            latLong[0] = formActivity.getCalculationUserInput().y3 = place.Y;
            latLong[1] = formActivity.getCalculationUserInput().x3 = place.X;
            addUserPlace();
            getGisToken();
        }
    }

    private void addUserPlace() {
        final GeoPoint startPoint = new GeoPoint(formActivity.getCalculationUserInput().y3,
                formActivity.getCalculationUserInput().x3);
        final Marker startMarker = new Marker(binding.mapView);
        startMarker.setPosition(startPoint);
        startMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        final IMapController mapController = binding.mapView.getController();
        mapController.setCenter(startPoint);
        binding.mapView.getOverlayManager().add(startMarker);
    }

    private void getGisToken() {
        new GetGisToken(requireContext(), this).execute(requireActivity());
    }

    public void setGisToken(String token) {
        getApplicationComponent().SharedPreferenceModel().putData(TOKEN_FOR_GIS.getValue(), token);
        new GetGisPoint(requireContext(), latLong, formActivity.getCalculationUserInput().billId, this,
                layerTypes[layerCounter], layerTypes[layerCounter + 1]).execute(requireActivity());
    }

    public void setGisLayer() {
        layerCounter++;
        if (layerCounter < layerTypes.length)
            new GetGisPoint(requireContext(), latLong, formActivity.getCalculationUserInput().billId,
                    this, layerTypes[layerCounter], layerCounter + 1 < layerTypes.length ?
                    layerTypes[layerCounter + 1] : null).execute(requireActivity());
    }

    @Override
    public void onClick(View v) {
        final int id = v.getId();
        if (id == R.id.image_view_my_location) {
            showCurrentLocation();
        } else if (id == R.id.image_view_refresh) {
            clearMap();
            initializeMap();
        } else if (id == R.id.image_view_show_layer) {
            getXY();
        } else if (id == R.id.button_pre) {
            formActivity.setOnPreClickListener(TECHNICAL_INFO_FRAGMENT);
        } else if (id == R.id.button_edit_crooki) {
            captureScreenshotAsync();
            clearMap();
            formActivity.setMapDescription();
        }
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
        binding.mapView.onResume();
        super.onResume();
    }

    @Override
    public void onPause() {
        binding.mapView.onPause();
        super.onPause();
    }


    public interface Callback {
        ExaminerDuties getExaminerDuty();

        CalculationUserInput getCalculationUserInput();

        void setOnPreClickListener(int position);

        void setTitle(String title, boolean showMenu);

        void setWaterLocation(GeoPoint point);

        void setCurrentLocation(GeoPoint point);

        void setMapDescription();
    }
}