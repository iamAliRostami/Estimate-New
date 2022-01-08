package com.leon.estimate_new.fragments.forms;

import static com.leon.estimate_new.helpers.Constants.BASE_FRAGMENT;
import static com.leon.estimate_new.helpers.Constants.SECOND_FRAGMENT;
import static com.leon.estimate_new.helpers.MyApplication.getLocationTracker;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.esri.arcgisruntime.layers.OpenStreetMapLayer;
import com.esri.arcgisruntime.mapping.ArcGISMap;
import com.esri.arcgisruntime.mapping.Viewpoint;
import com.leon.estimate_new.R;
import com.leon.estimate_new.databinding.FragmentMapDescriptionBinding;
import com.leon.estimate_new.enums.MapType;
import com.leon.estimate_new.tables.ExaminerDuties;
import com.leon.estimate_new.utils.gis.GoogleMapLayer;
import com.leon.estimate_new.utils.gis.OsmMapLayer;

public class MapDescriptionFragment extends Fragment {
    private FragmentMapDescriptionBinding binding;
    private Callback formActivity;

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

        });
    }

    private void initializeMap() {
        final ArcGISMap map = new ArcGISMap();
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
            requireActivity().runOnUiThread(() -> binding.progressBar.setVisibility(View.GONE));
        });
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
    }
}