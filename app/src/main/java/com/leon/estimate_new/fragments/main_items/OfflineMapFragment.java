package com.leon.estimate_new.fragments.main_items;

import static com.leon.estimate_new.helpers.MyApplication.getLocationTracker;

import android.app.Dialog;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.leon.estimate_new.R;
import com.leon.estimate_new.databinding.FragmentOfflineMapBinding;
import com.leon.estimate_new.utils.CustomOnlineTileSource;
import com.leon.estimate_new.utils.CustomToast;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.events.MapEventsReceiver;
import org.osmdroid.tileprovider.cachemanager.CacheManager;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.overlay.MapEventsOverlay;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.Polyline;
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;

import java.util.ArrayList;

public class OfflineMapFragment extends BottomSheetDialogFragment implements View.OnClickListener {

    private FragmentOfflineMapBinding binding;
    private GeoPoint startPoint, endPoint;
    private int startPointIndex, endPointIndex;
    private Polyline lines;
    private CacheManager cacheManager;

    public OfflineMapFragment() {
    }

    public static OfflineMapFragment newInstance() {
        OfflineMapFragment fragment = new OfflineMapFragment();
        fragment.setCancelable(false);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentOfflineMapBinding.inflate(inflater, container, false);
        initialize();
        return binding.getRoot();
    }

    private void initialize() {
        binding.imageViewArrowDown.setOnClickListener(this);
        binding.imageViewRefresh.setOnClickListener(this);
        binding.imageViewMyLocation.setOnClickListener(this);
        binding.buttonDownload.setOnClickListener(this);
        initializeMap();
        final RelativeLayout.LayoutParams params = new RelativeLayout.
                LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                requireContext().getResources().getDisplayMetrics().widthPixels);
        binding.mapView.setLayoutParams(params);
    }

    private void initializeMap() {
        Configuration.getInstance().load(requireContext(), PreferenceManager.getDefaultSharedPreferences(requireContext()));
        binding.mapView.setTileSource(new CustomOnlineTileSource());
        binding.mapView.setBuiltInZoomControls(true);
        binding.mapView.setMultiTouchControls(true);
        showCurrentLocation();
        onMapTouchListener();
        cacheManager = new CacheManager(binding.mapView);
        lines = new Polyline(binding.mapView);
    }

    private void onMapTouchListener() {
        binding.mapView.getOverlays().add(new MapEventsOverlay(new MapEventsReceiver() {
            @Override
            public boolean singleTapConfirmedHelper(GeoPoint p) {
                addPoint(p);
                return false;
            }

            @Override
            public boolean longPressHelper(GeoPoint p) {
                addPoint(p);
                return false;
            }
        }));
    }


    private void showCurrentLocation() {
        IMapController mapController = binding.mapView.getController();
        mapController.setZoom(17.5);
        double latitude = getLocationTracker(requireActivity()).getLatitude();
        double longitude = getLocationTracker(requireActivity()).getLongitude();
        mapController.setCenter(new GeoPoint(latitude, longitude));
        MyLocationNewOverlay locationOverlay =
                new MyLocationNewOverlay(new GpsMyLocationProvider(requireContext()), binding.mapView);
        locationOverlay.enableMyLocation();
        binding.mapView.getOverlays().add(locationOverlay);

    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        View bottomSheet = view.findViewById(R.id.linear_layout_container);
        if (bottomSheet != null) {
            DisplayMetrics displayMetrics = requireActivity().getResources().getDisplayMetrics();
            BottomSheetBehavior.from(bottomSheet).setPeekHeight((int) (displayMetrics.heightPixels * 0.99));
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final Dialog serviceLocationDialog = super.onCreateDialog(savedInstanceState);
        serviceLocationDialog.setOnShowListener(dialog -> {
            BottomSheetDialog bottomDialog = (BottomSheetDialog) dialog;
            FrameLayout bottomSheet = bottomDialog.findViewById(com.google.android.material.R.id.design_bottom_sheet);
            if (bottomSheet != null) {
                DisplayMetrics displayMetrics = requireActivity().getResources().getDisplayMetrics();
                BottomSheetBehavior.from(bottomSheet).setPeekHeight((int) (displayMetrics.heightPixels * 0.99));
            }
        });
        return serviceLocationDialog;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.image_view_arrow_down) {
            dismiss();
        } else if (id == R.id.button_download) {
            if (cacheManager.possibleTilesInArea(lines.getBounds(), 1, 17) > 0) {
                startDownloadMap();
            } else new CustomToast().warning("ناحیه ای انتخاب نشده است");

        } else if (id == R.id.image_view_refresh) {
            clearMap();

        } else if (id == R.id.image_view_my_location) {
            showCurrentLocation();
        }
    }

    private void startDownloadMap() {
        cacheManager.downloadAreaAsync(requireContext(), lines.getBounds(), 1, 17,
                new CacheManager.CacheManagerCallback() {
                    @Override
                    public void onTaskComplete() {
                        Log.e("here", "onTaskComplete");
                    }

                    @Override
                    public void updateProgress(int progress, int currentZoomLevel, int zoomMin, int zoomMax) {
                        Log.e("here", "updateProgress " + "progress " + progress);
                        Log.e("here", "updateProgress " + "currentZoomLevel " + currentZoomLevel);
                        Log.e("here", "updateProgress " + "zoomMin " + zoomMin);
                        Log.e("here", "updateProgress " + "progress " + progress);
                    }

                    @Override
                    public void downloadStarted() {
                        Log.e("here", "downloadStarted");
                    }

                    @Override
                    public void setPossibleTilesInArea(int total) {
                        Log.e("here", "setPossibleTilesInArea " + total);
                    }

                    @Override
                    public void onTaskFailed(int errors) {
                        Log.e("here", "onTaskFailed " + errors);
                    }
                });
    }

    private void clearMap() {
        startPoint = endPoint = null;
        if (endPointIndex > 0)
            binding.mapView.getOverlays().remove(endPointIndex);
        if (startPointIndex > 0)
            binding.mapView.getOverlays().remove(startPointIndex);
        startPointIndex = endPointIndex = 0;
        if (lines != null)
            lines.setPoints(new ArrayList<>());
        binding.textViewTileNumber.setText(getString(R.string.zero));
    }

    private void addPoint(GeoPoint p) {
        if (startPoint != null && endPoint != null) {
            clearMap();
        }
        addMaker(p);
        if (startPoint == null) {
            startPoint = p;
            startPointIndex = binding.mapView.getOverlays().size() - 1;
        } else if (endPoint == null) {
            endPoint = p;
            endPointIndex = binding.mapView.getOverlays().size() - 1;
            addPolyline();
            binding.textViewTileNumber.setText(String.valueOf(cacheManager.possibleTilesInArea(lines.getBounds(),
                    1, 17)));
        }
    }

    private void addMaker(GeoPoint p) {
        Marker marker = new Marker(binding.mapView);
        marker.setPosition(p);
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        binding.mapView.getOverlays().add(marker);
    }

    private void addPolyline() {
        ArrayList<GeoPoint> polygonPoint = new ArrayList<>();

        polygonPoint.add(startPoint);
        polygonPoint.add(new GeoPoint(startPoint.getLatitude(), endPoint.getLongitude()));
        polygonPoint.add(endPoint);
        polygonPoint.add(new GeoPoint(endPoint.getLatitude(), startPoint.getLongitude()));
        polygonPoint.add(startPoint);

        lines.setPoints(polygonPoint);
        binding.mapView.getOverlays().add(lines);
    }
}