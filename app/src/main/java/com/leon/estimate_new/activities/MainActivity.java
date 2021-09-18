package com.leon.estimate_new.activities;

import static com.leon.estimate_new.utils.PermissionManager.isNetworkAvailable;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.esri.arcgisruntime.data.ShapefileFeatureTable;
import com.esri.arcgisruntime.geometry.Point;
import com.esri.arcgisruntime.geometry.Polygon;
import com.esri.arcgisruntime.layers.FeatureLayer;
import com.esri.arcgisruntime.loadable.LoadStatus;
import com.esri.arcgisruntime.mapping.ArcGISMap;
import com.esri.arcgisruntime.mapping.BasemapStyle;
import com.esri.arcgisruntime.mapping.Viewpoint;
import com.esri.arcgisruntime.mapping.view.DefaultMapViewOnTouchListener;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
import com.leon.estimate_new.R;
import com.leon.estimate_new.databinding.ActivityMainBinding;
import com.leon.estimate_new.helpers.Constants;
import com.leon.estimate_new.helpers.MyApplication;
import com.leon.estimate_new.utils.CustomToast;
import com.leon.estimate_new.utils.PermissionManager;
import com.leon.estimate_new.utils.gis.CustomImageTiledLayer;
import com.leon.estimate_new.utils.gis.GoogleMapLayer;
import com.leon.estimate_new.utils.gis.LayerInfo;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private Activity activity;
    private ArcGISMap map;

    private MenuItem mStreetsMenuItem = null;
    private MenuItem mTopologyMenuItem = null;
    private MenuItem mGrayMenuItem = null;
    private MenuItem mOceansMenuItem = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        activity = this;
        if (isNetworkAvailable(activity))
            checkPermissions();
        else PermissionManager.enableNetwork(activity);
    }

    void initialize() {
        initializeMap();
    }

    void initializeMap() {
        map = new ArcGISMap();
        binding.mapView.setMap(map);
        binding.mapView.setViewpoint(new Viewpoint(MyApplication.getLocationTracker(activity).getLatitude(),
                MyApplication.getLocationTracker(activity).getLongitude(), 7200));

        LayerInfo info = new LayerInfo();
        CustomImageTiledLayer baseLayer = new CustomImageTiledLayer(info.getTianDiTuMLayerInfo(), info.getMFullExtent());
        baseLayer.setMainURL(getString(R.string.local_base_map));

        binding.mapView.getMap().getBasemap().getBaseLayers().add(GoogleMapLayer.CreateGoogleLayer(GoogleMapLayer.MapType.IMAGE));
        binding.mapView.getMap().getBasemap().getBaseLayers().add(baseLayer);

        binding.mapView.setMagnifierEnabled(true);
        binding.mapView.setCanMagnifierPanMap(true);

        loadShapeFile();

        onMapTouchListener();
    }

    void loadLocalMap() {

    }

    void loadShapeFile() {
        ShapefileFeatureTable shapefileFeatureTable = new ShapefileFeatureTable(
                Environment.getExternalStorageDirectory() + "/Pictures/Aurora_CO_shp.zip");

        // create a feature layer to display the shapefile
        FeatureLayer shapefileFeatureLayer = new FeatureLayer(shapefileFeatureTable);
        // add the feature layer to the map
        binding.mapView.getMap().getOperationalLayers().add(shapefileFeatureLayer);
        shapefileFeatureTable.addDoneLoadingListener(() -> {
            if (shapefileFeatureTable.getLoadStatus() == LoadStatus.LOADED) {
                // zoom the map to the extent of the shapefile
                binding.mapView.setViewpointAsync(new Viewpoint(shapefileFeatureLayer.getFullExtent()));
            } else {
                Log.e("ReadShape", shapefileFeatureTable.getLoadError().toString());
            }
        });
    }

    @SuppressLint("ClickableViewAccessibility")
    private void onMapTouchListener() {
        try {
            binding.mapView.setOnTouchListener(new DefaultMapViewOnTouchListener(getApplicationContext(), binding.mapView) {
                @Override
                public boolean onSingleTapConfirmed(MotionEvent e) {
                    Point screenCoordinate = new Point(Math.round(e.getX()), Math.round(e.getY()));
                    Log.e("point", String.valueOf(screenCoordinate.getX()));
                    return super.onSingleTapConfirmed(e);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Save the current extent of the map before changing the map.
        Polygon mCurrentMapExtent = binding.mapView.getVisibleArea();
        switch (item.getItemId()) {
            case R.id.World_Street_Map:
//                map = new ArcGISMap(BasemapStyle.ARCGIS_IMAGERY);
                mStreetsMenuItem.setChecked(true);
                return true;
            case R.id.World_Topo:
//                map = new ArcGISMap(BasemapStyle.ARCGIS_IMAGERY_STANDARD);
                mTopologyMenuItem.setChecked(true);
                return true;
            case R.id.Gray:
//                map = new ArcGISMap(BasemapStyle.ARCGIS_IMAGERY_LABELS);
                mGrayMenuItem.setChecked(true);
                return true;
            case R.id.Ocean_Basemap:
//                map = new ArcGISMap(BasemapStyle.ARCGIS_LIGHT_GRAY);
                mOceansMenuItem.setChecked(true);
                return true;
        }
        binding.mapView.setMap(map);
        binding.mapView.setViewpoint(new Viewpoint(32.7030911, 51.7135289, 72000.0));
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.map_menu, menu);

        mStreetsMenuItem = menu.getItem(0);
        mTopologyMenuItem = menu.getItem(1);
        mGrayMenuItem = menu.getItem(2);
        mOceansMenuItem = menu.getItem(3);

        mStreetsMenuItem.setChecked(true);

        return true;
    }

    void checkPermissions() {
        if (PermissionManager.gpsEnabled(this))
            if (PermissionManager.checkLocationPermission(getApplicationContext())) {
                askLocationPermission();
            } else if (PermissionManager.checkCameraPermission(getApplicationContext())) {
                askStoragePermission();
            } else {
                initialize();
            }
    }

    void askStoragePermission() {
        PermissionListener permissionlistener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                new CustomToast().info(getString(R.string.access_granted));
                checkPermissions();
            }

            @Override
            public void onPermissionDenied(ArrayList<String> deniedPermissions) {
                PermissionManager.forceClose(activity);
            }
        };
        new TedPermission(this)
                .setPermissionListener(permissionlistener)
                .setRationaleMessage(getString(R.string.confirm_permission))
                .setRationaleConfirmText(getString(R.string.allow_permission))
                .setDeniedMessage(getString(R.string.if_reject_permission))
                .setDeniedCloseButtonText(getString(R.string.close))
                .setGotoSettingButtonText(getString(R.string.allow_permission))
                .setPermissions(
                        Manifest.permission.CAMERA,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                ).check();
    }

    void askLocationPermission() {
        PermissionListener permissionlistener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                new CustomToast().info(getString(R.string.access_granted));
                checkPermissions();
            }

            @Override
            public void onPermissionDenied(ArrayList<String> deniedPermissions) {

                PermissionManager.forceClose(activity);
            }
        };
        new TedPermission(this)
                .setPermissionListener(permissionlistener)
                .setRationaleMessage(getString(R.string.confirm_permission))
                .setRationaleConfirmText(getString(R.string.allow_permission))
                .setDeniedMessage(getString(R.string.if_reject_permission))
                .setDeniedCloseButtonText(getString(R.string.close))
                .setGotoSettingButtonText(getString(R.string.allow_permission))
                .setPermissions(
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                ).check();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == PackageManager.PERMISSION_GRANTED) {
            if (requestCode == Constants.GPS_CODE)
                checkPermissions();
            if (requestCode == Constants.REQUEST_NETWORK_CODE) {
                if (isNetworkAvailable(getApplicationContext()))
                    checkPermissions();
                else PermissionManager.setMobileWifiEnabled(this);
            }
            if (requestCode == Constants.REQUEST_WIFI_CODE) {
                if (isNetworkAvailable(getApplicationContext()))
                    checkPermissions();
                else PermissionManager.enableNetwork(this);
            }
        }
    }

    @Override
    protected void onPause() {
        binding.mapView.pause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        binding.mapView.resume();
    }

    @Override
    protected void onDestroy() {
        binding.mapView.dispose();
        super.onDestroy();
    }
}