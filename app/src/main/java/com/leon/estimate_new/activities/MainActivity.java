package com.leon.estimate_new.activities;

import static com.leon.estimate_new.helpers.MyApplication.getLocationTracker;
import static com.leon.estimate_new.utils.PermissionManager.isNetworkAvailable;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.esri.arcgisruntime.concurrent.Job;
import com.esri.arcgisruntime.concurrent.ListenableFuture;
import com.esri.arcgisruntime.data.ServiceFeatureTable;
import com.esri.arcgisruntime.data.ShapefileFeatureTable;
import com.esri.arcgisruntime.geometry.Point;
import com.esri.arcgisruntime.geometry.Polygon;
import com.esri.arcgisruntime.layers.FeatureLayer;
import com.esri.arcgisruntime.layers.OpenStreetMapLayer;
import com.esri.arcgisruntime.loadable.LoadStatus;
import com.esri.arcgisruntime.mapping.ArcGISMap;
import com.esri.arcgisruntime.mapping.BasemapStyle;
import com.esri.arcgisruntime.mapping.Viewpoint;
import com.esri.arcgisruntime.mapping.view.DefaultMapViewOnTouchListener;
import com.esri.arcgisruntime.mapping.view.Graphic;
import com.esri.arcgisruntime.mapping.view.GraphicsOverlay;
import com.esri.arcgisruntime.tasks.offlinemap.GenerateOfflineMapJob;
import com.esri.arcgisruntime.tasks.offlinemap.GenerateOfflineMapParameters;
import com.esri.arcgisruntime.tasks.offlinemap.GenerateOfflineMapResult;
import com.esri.arcgisruntime.tasks.offlinemap.OfflineMapTask;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
import com.leon.estimate_new.R;
import com.leon.estimate_new.base_items.BaseActivity;
import com.leon.estimate_new.databinding.ActivityMainBinding;
import com.leon.estimate_new.enums.MapType;
import com.leon.estimate_new.helpers.Constants;
import com.leon.estimate_new.utils.CustomToast;
import com.leon.estimate_new.utils.PermissionManager;
import com.leon.estimate_new.utils.gis.GoogleMapLayer;
import com.leon.estimate_new.utils.gis.OsmMapLayer;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class MainActivity extends BaseActivity {
    private ActivityMainBinding binding;
    private Activity activity;
    private ArcGISMap map;

    private MenuItem mStreetsMenuItem = null;
    private MenuItem mTopologyMenuItem = null;
    private MenuItem mGrayMenuItem = null;
    private MenuItem mOceansMenuItem = null;


    private GraphicsOverlay mGraphicsOverlay;
    private Graphic mDownloadArea;

    private GenerateOfflineMapJob mGenerateOfflineMapJob;
    private GenerateOfflineMapParameters mGenerateOfflineMapParameters;
    private String mLocalBasemapDirectory;
    private OfflineMapTask mOfflineMapTask;

    @Override
    protected void initialize() {
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        final View childLayout = binding.getRoot();
        final ConstraintLayout parentLayout = findViewById(R.id.base_Content);
        parentLayout.addView(childLayout);


        activity = this;

        initializeMap();

//        new DownloadData(activity, binding.imageViewDownload).execute(this);
    }

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        binding = ActivityMainBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());
//        setActivityComponent(this);
//        activity = this;
//        if (isNetworkAvailable(activity))
//            checkPermissions();
//        else PermissionManager.enableNetwork(activity);
//    }
//    private void initialize() {
//        initializeMap();
//
//        new DownloadData(activity,binding.imageViewDownload).execute(this);
////        binding.buttonSave.setOnClickListener(view -> generateOfflineMap());
////        binding.buttonOffline.setOnClickListener(view -> initializeMapOffline());
//    }

    private void initializeMap() {
//         ArcGISMap map = new ArcGISMap(new Basemap(new OsmMapLayer().createLayer()));
        ArcGISMap map = new ArcGISMap();
        binding.mapView.setMap(map);

//        LayerInfo info = new LayerInfo();
//        CustomImageTiledLayer baseLayer = new CustomImageTiledLayer(info.getTianDiTuMLayerInfo(), info.getMFullExtent());
//        baseLayer.setMainURL(getString(R.string.local_base_map));
//        binding.mapView.getMap().getBasemap().getBaseLayers().add(baseLayer);
        binding.mapView.getMap().getBasemap().getBaseLayers().add(new GoogleMapLayer().createLayer(MapType.VECTOR));
        binding.mapView.getMap().getBasemap().getBaseLayers().add(new OsmMapLayer().createLayer());
        binding.mapView.getMap().getBasemap().getBaseLayers().add(new OpenStreetMapLayer());


        binding.mapView.setMagnifierEnabled(true);
        binding.mapView.setCanMagnifierPanMap(true);

        AsyncTask.execute(() -> {
            while (getLocationTracker(activity).getLocation() == null)
                binding.progressBar.setVisibility(View.VISIBLE);
            binding.mapView.setViewpoint(new Viewpoint(getLocationTracker(activity).getLatitude()
                    , getLocationTracker(activity).getLongitude(), 3600));
            runOnUiThread(() -> binding.progressBar.setVisibility(View.GONE));
        });
//        loadShapeFile();
    }

    private void loadShapeFile() {
//        ShapefileFeatureTable shapefileFeatureTable = new ShapefileFeatureTable(
//                Environment.getExternalStorageDirectory() + "/Pictures/Aurora_CO_shp.zip");
//        ShapefileFeatureTable shapefileFeatureTable = new ShapefileFeatureTable(
//                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/Aurora_CO.shp");
        ShapefileFeatureTable shapefileFeatureTable = new ShapefileFeatureTable(
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/block_sarmast.shp");
        // create a feature layer to display the shapefile
        Log.e("path", shapefileFeatureTable.getPath());
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

    private void initializeMap1() {
        ArcGISMap map = new ArcGISMap(BasemapStyle.OSM_STREETS);

        // create feature layer with its service feature table
        // create the service feature table
        ServiceFeatureTable serviceFeatureTable = new ServiceFeatureTable(
                getResources().getString(R.string.local_base_map));

        //explicitly set the mode to on interaction cache (which is also the default mode for service feature tables)
        serviceFeatureTable.setFeatureRequestMode(ServiceFeatureTable.FeatureRequestMode.ON_INTERACTION_CACHE);

        // create the feature layer using the service feature table
        FeatureLayer featureLayer = new FeatureLayer(serviceFeatureTable);

        // add the layer to the map
        map.getOperationalLayers().add(featureLayer);

        // set the map to be displayed in the mapview
        binding.mapView.setMap(map);

        // set an initial viewpoint
    }

    private void initializeMapOffline() {
        // specify the extent, min scale, and max scale as parameters
        double minScale = binding.mapView.getMapScale();
        double maxScale = binding.mapView.getMap().getMaxScale();
        // minScale must always be larger than maxScale
        if (minScale <= maxScale) {
            minScale = maxScale + 1;
        }

        // create an offline map task with the map
        mOfflineMapTask = new OfflineMapTask(binding.mapView.getMap());

        // create default generate offline map parameters
        ListenableFuture<GenerateOfflineMapParameters> generateOfflineMapParametersFuture = mOfflineMapTask
                .createDefaultGenerateOfflineMapParametersAsync(mDownloadArea.getGeometry(), minScale, maxScale);
        generateOfflineMapParametersFuture.addDoneListener(() -> {
            try {
                mGenerateOfflineMapParameters = generateOfflineMapParametersFuture.get();
                // name of local basemap file as supplied by the map's author
                String localBasemapFileName = mGenerateOfflineMapParameters.getReferenceBasemapFilename();
                // check if the offline map parameters include reference to a basemap file
                if (!localBasemapFileName.isEmpty()) {
                    // search for the given file name in the app's scoped storage
                    File localBasemapFile = searchForFile(getExternalFilesDir(null), localBasemapFileName);
                    // if a file of the given name was found
                    if (localBasemapFile != null) {
                        // get the file's directory
                        mLocalBasemapDirectory = localBasemapFile.getParent();
//                        showLocalBasemapAlertDialog(localBasemapFileName);
                        if (mLocalBasemapDirectory != null)
                            mGenerateOfflineMapParameters.setReferenceBasemapDirectory(mLocalBasemapDirectory);
                        // call generate offline
                    } else {
                        String error = "Local basemap file " + localBasemapFileName + " not found!";
                        Toast.makeText(this, error, Toast.LENGTH_LONG).show();
                    }
                } else {
                    String message = "The map's author has not specified a local basemap";
                    Toast.makeText(this, message, Toast.LENGTH_LONG).show();
                }
            } catch (ExecutionException | InterruptedException e) {
                String error = "Error creating generate offline map parameters: " + e.getMessage();
                Toast.makeText(this, error, Toast.LENGTH_LONG).show();
            }
        });
    }

    private File searchForFile(File file, String fileName) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File f : files) {
                File found = searchForFile(f, fileName);
                if (found != null)
                    return found;
            }
        } else {
            if (file.getName().equals(fileName)) {
                return file;
            }
        }
        return null;
    }

    /**
     * Use the generate offline map job to generate an offline map.
     */
    private void generateOfflineMap() {
        // cancel previous job request
        if (mGenerateOfflineMapJob != null) {
            mGenerateOfflineMapJob.cancel();
        }
        String tempDirectoryPath = getCacheDir() + File.separator + getString(R.string.map_folder);
        // create an offline map job with the download directory path and parameters and start the job
        mGenerateOfflineMapJob = mOfflineMapTask.generateOfflineMap(mGenerateOfflineMapParameters, tempDirectoryPath);

        // replace the current map with the result offline map when the job finishes
        mGenerateOfflineMapJob.addJobDoneListener(() -> {
            if (mGenerateOfflineMapJob.getStatus() == Job.Status.SUCCEEDED) {
                GenerateOfflineMapResult result = mGenerateOfflineMapJob.getResult();
                binding.mapView.setMap(result.getOfflineMap());
                mGraphicsOverlay.getGraphics().clear();
                Toast.makeText(this, "Now displaying offline map.", Toast.LENGTH_LONG).show();
            } else {
                Log.e("Error in generate offline map job", mGenerateOfflineMapJob.getError().getAdditionalMessage());
            }
        });
        mGenerateOfflineMapJob.start();
    }

    private void loadLocalMap() {

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

    @Override
    protected void onPause() {
        if (binding != null)
            binding.mapView.pause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (binding != null)
            binding.mapView.resume();
    }

    @Override
    protected void onDestroy() {
        binding.mapView.dispose();
        super.onDestroy();
    }
}