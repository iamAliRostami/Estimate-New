package com.leon.estimate_new.fragments.main_items;

import static com.leon.estimate_new.fragments.dialog.ShowFragmentDialog.ShowFragmentDialogOnce;
import static com.leon.estimate_new.helpers.MyApplication.getLocationTracker;
import static com.leon.estimate_new.utils.DifferentCompanyManager.getActiveCompanyName;
import static com.leon.estimate_new.utils.DifferentCompanyManager.getMapUrl;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.MenuProvider;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;

import com.leon.estimate_new.BuildConfig;
import com.leon.estimate_new.databinding.FragmentHomeBinding;

import org.jetbrains.annotations.NotNull;
import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.OnlineTileSourceBase;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.util.MapTileIndex;
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;

import java.io.File;

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
        //TODO
        Configuration.getInstance().setUserAgentValue(BuildConfig.APPLICATION_ID);
        // Set the tile cache path (where map data will be stored)
        Configuration.getInstance().setOsmdroidBasePath(new File(Environment.getExternalStorageDirectory(), "osmdroid"));
        Configuration.getInstance().setOsmdroidTileCache(new File(Environment.getExternalStorageDirectory(), "osmdroid/tiles"));
        //TODO
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        initialize();
        return binding.getRoot();
    }

    private void initialize() {
//        setHasOptionsMenu(true);
        initializeMap();
    }

    private void initializeMap() {
        Configuration.getInstance().load(requireContext(), PreferenceManager.getDefaultSharedPreferences(requireContext()));
//        binding.mapView.setTileSource(new CustomOnlineTileSource());
        final OnlineTileSourceBase custom = new OnlineTileSourceBase("custom",
                0, 19, 256, ".png", new String[]{
                getMapUrl(getActiveCompanyName())}) {
            @Override
            public String getTileURLString(long aTile) {
                Log.e("url",getBaseUrl() + MapTileIndex.getZoom(aTile) + "/" + MapTileIndex.getX(aTile)
                        + "/" + MapTileIndex.getY(aTile) + mImageFilenameEnding);
                return getBaseUrl() + MapTileIndex.getZoom(aTile) + "/" + MapTileIndex.getX(aTile)
                        + "/" + MapTileIndex.getY(aTile) + mImageFilenameEnding;
            }
        };
        binding.mapView.setTileSource(custom);
        binding.mapView.setBuiltInZoomControls(true);
        binding.mapView.setMultiTouchControls(true);
        showCurrentLocation();
        requireActivity().runOnUiThread(() -> binding.progressBar.setVisibility(View.GONE));
    }

    private void showCurrentLocation() {



        final IMapController mapController = binding.mapView.getController();
        mapController.setZoom(16.5);

        AsyncTask.execute(() -> {
            while (getLocationTracker(requireActivity()).getLocation() == null||
                    getLocationTracker(requireActivity()).getLatitude() == 0)
                binding.progressBar.setVisibility(View.VISIBLE);
            double latitude = getLocationTracker(requireActivity()).getLatitude();
            double longitude = getLocationTracker(requireActivity()).getLongitude();

            GeoPoint startPoint = new GeoPoint(latitude, longitude);
            requireActivity().runOnUiThread(() -> {
                mapController.setCenter(startPoint);
                binding.progressBar.setVisibility(View.GONE);
            });
        });
        MyLocationNewOverlay locationOverlay =
                new MyLocationNewOverlay(new GpsMyLocationProvider(requireContext()), binding.mapView);
        locationOverlay.enableMyLocation();
        binding.mapView.getOverlays().add(locationOverlay);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        requireActivity().addMenuProvider(new MenuProvider() {
            @Override
            public void onCreateMenu(@NonNull Menu menu, @NonNull MenuInflater menuInflater) {
                menu.add(0, MENU_OFFLINE_MAP, Menu.NONE, "نقشه آفلاین");
            }

            @Override
            public boolean onMenuItemSelected(@NonNull MenuItem menuItem) {
                if (menuItem.getItemId() == MENU_OFFLINE_MAP) {
                    ShowFragmentDialogOnce(requireContext(), "OFFLINE_MAP", OfflineMapFragment.newInstance());
                }

                return false;
            }
        }, getViewLifecycleOwner(), Lifecycle.State.RESUMED);
    }

    private final int MENU_OFFLINE_MAP = 0;

//    @Override
//    public void onCreateOptionsMenu(Menu menu, @NonNull MenuInflater inflater) {
//        menu.add(0, MENU_OFFLINE_MAP, Menu.NONE, "نقشه آفلاین");
//        super.onCreateOptionsMenu(menu, inflater);
//    }
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        if (item.getItemId() == MENU_OFFLINE_MAP) {
////            callback.displayView(OFFLINE_MAP_FRAGMENT);
//            ShowFragmentDialogOnce(requireContext(), "OFFLINE_MAP",OfflineMapFragment.newInstance());
//        }
//        return super.onOptionsItemSelected(item);
//    }

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