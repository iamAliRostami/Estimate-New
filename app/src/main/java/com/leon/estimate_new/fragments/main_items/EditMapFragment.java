package com.leon.estimate_new.fragments.main_items;

import static com.leon.estimate_new.helpers.Constants.MAP_DESCRIPTION_FRAGMENT;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.leon.estimate_new.R;
import com.leon.estimate_new.databinding.FragmentEditMapBinding;
import com.leon.estimate_new.helpers.Constants;
import com.leon.estimate_new.tables.ExaminerDuties;


public class EditMapFragment extends Fragment {
    private FragmentEditMapBinding binding;
    private Callback formActivity;

    public EditMapFragment() {
    }

    public static EditMapFragment newInstance() {
        return new EditMapFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        formActivity.setTitle(getString(R.string.app_name).concat(" / ").concat("صفحه ششم"), false);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentEditMapBinding.inflate(inflater, container, false);
        initialize();
        return binding.getRoot();
    }

    private void initialize() {
        setOnButtonClickListener();
    }

    private void setOnButtonClickListener() {
        binding.buttonPre.setOnClickListener(v -> formActivity.setOnPreClickListener(MAP_DESCRIPTION_FRAGMENT));
        binding.buttonSubmit.setOnClickListener(v -> {
            Constants.BITMAP_SELECTED = binding.signatureView.getSignatureBitmap();
            formActivity.setEditMap();
        });
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof Activity) {
            formActivity = (Callback) context;
        }
    }

    public interface Callback {
        void setOnPreClickListener(int position);

        void setTitle(String title, boolean showMenu);

        ExaminerDuties getExaminerDuty();

        void setEditMap();
    }
}