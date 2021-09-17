package com.leon.estimate_new.activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;

import androidx.appcompat.app.AppCompatActivity;

import com.esri.arcgisruntime.data.ShapefileFeatureTable;
import com.esri.arcgisruntime.geometry.Point;
import com.esri.arcgisruntime.geometry.Polygon;
import com.esri.arcgisruntime.layers.ArcGISTiledLayer;
import com.esri.arcgisruntime.layers.FeatureLayer;
import com.esri.arcgisruntime.loadable.LoadStatus;
import com.esri.arcgisruntime.mapping.ArcGISMap;
import com.esri.arcgisruntime.mapping.Basemap;
import com.esri.arcgisruntime.mapping.Viewpoint;
import com.esri.arcgisruntime.mapping.view.DefaultMapViewOnTouchListener;
import com.esri.arcgisruntime.mapping.view.LocationDisplay;
import com.leon.estimate_new.R;
import com.leon.estimate_new.databinding.ActivityMainBinding;
import com.leon.estimate_new.utils.gis.CustomImageTiledLayer;
import com.leon.estimate_new.utils.gis.GoogleMapLayer;
import com.leon.estimate_new.utils.gis.LayerInfo;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    private Basemap basemap;
    private ArcGISTiledLayer arcGISTiledLayer;
    private ArcGISMap map;
    private LocationDisplay locationDisplay;

    private Polygon mCurrentMapExtent = null;

    private MenuItem mStreetsMenuItem = null;
    private MenuItem mTopoMenuItem = null;
    private MenuItem mGrayMenuItem = null;
    private MenuItem mOceansMenuItem = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initialize();
    }

    void initialize() {
        initializeMap();
    }

    void initializeMap() {
        map = new ArcGISMap();
        binding.mapView.setMap(map);
        binding.mapView.setViewpoint(new Viewpoint(32.7030911, 51.7135289, 7200));

        LayerInfo info = new LayerInfo();
        CustomImageTiledLayer baseLayer = new CustomImageTiledLayer(info.getTianDiTuMLayerInfo(), info.getMFullExtent());
        baseLayer.setMainURL(getString(R.string.local_base_map));
//        baseLayer.setName("baseLayer");
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
                Environment.getExternalStorageDirectory() + "/Pictures/Aurora_CO.shp");

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
            Log.e("error", e.toString());
        }
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Save the current extent of the map before changing the map.
        mCurrentMapExtent = binding.mapView.getVisibleArea();
        switch (item.getItemId()) {
            case R.id.World_Street_Map:
//                map = new ArcGISMap(BasemapStyle.ARCGIS_IMAGERY);
                mStreetsMenuItem.setChecked(true);
                return true;
            case R.id.World_Topo:
//                map = new ArcGISMap(BasemapStyle.ARCGIS_IMAGERY_STANDARD);
                mTopoMenuItem.setChecked(true);
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
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.map_menu, menu);

        // Get the basemap switching menu items.
        mStreetsMenuItem = menu.getItem(0);
        mTopoMenuItem = menu.getItem(1);
        mGrayMenuItem = menu.getItem(2);
        mOceansMenuItem = menu.getItem(3);

        // Also set the topo basemap menu item to be checked, as this is the default.
        mStreetsMenuItem.setChecked(true);

        return true;
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