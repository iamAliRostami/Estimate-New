package com.leon.estimate_new.fragments.main_items;

import static com.leon.estimate_new.helpers.MyApplication.getLocationTracker;

import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.leon.estimate_new.databinding.FragmentHomeBinding;

import org.jetbrains.annotations.NotNull;
import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;

public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;

    public HomeFragment() {
    }

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        initialize();
        return binding.getRoot();
    }

    private void initialize() {
        initializeMap();
    }

    private void initializeMap() {
        Configuration.getInstance().load(requireContext(), PreferenceManager.getDefaultSharedPreferences(requireContext()));
        binding.mapView.setBuiltInZoomControls(true);
        binding.mapView.setMultiTouchControls(true);
        showCurrentLocation();
        requireActivity().runOnUiThread(() -> binding.progressBar.setVisibility(View.GONE));
    }

    private void showCurrentLocation() {
        final IMapController mapController = binding.mapView.getController();
        mapController.setZoom(19.5);

        AsyncTask.execute(() -> {
            while (getLocationTracker(requireActivity()).getLocation() == null)
                binding.progressBar.setVisibility(View.VISIBLE);
            final double latitude = getLocationTracker(requireActivity()).getLatitude();
            final double longitude = getLocationTracker(requireActivity()).getLongitude();

            final GeoPoint startPoint = new GeoPoint(latitude, longitude);
            requireActivity().runOnUiThread(() -> {
                mapController.setCenter(startPoint);
                binding.progressBar.setVisibility(View.GONE);
            });
        });
        final MyLocationNewOverlay locationOverlay =
                new MyLocationNewOverlay(new GpsMyLocationProvider(requireContext()), binding.mapView);
        locationOverlay.enableMyLocation();
        binding.mapView.getOverlays().add(locationOverlay);

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


    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}