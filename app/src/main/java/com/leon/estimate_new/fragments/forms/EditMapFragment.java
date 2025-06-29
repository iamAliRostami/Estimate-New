package com.leon.estimate_new.fragments.forms;

import static android.graphics.Color.BLUE;
import static android.graphics.Color.RED;
import static android.graphics.Color.YELLOW;
import static com.leon.estimate_new.helpers.Constants.MAP_DESCRIPTION_FRAGMENT;
import static com.leon.estimate_new.helpers.Constants.MAP_SELECTED;
import static com.leon.estimate_new.utils.ImageUtils.createImage;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.leon.estimate_new.R;
import com.leon.estimate_new.databinding.FragmentEditMapBinding;
import com.leon.estimate_new.tables.ExaminerDuties;


public class EditMapFragment extends Fragment implements View.OnClickListener {
    private FragmentEditMapBinding binding;
    private Callback formActivity;
    private Bitmap bitmapTemp;

    public EditMapFragment() {
    }

    public static EditMapFragment newInstance() {
        return new EditMapFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentEditMapBinding.inflate(inflater, container, false);
        initialize();
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        formActivity.setTitle(getString(R.string.app_name).concat(" / ").concat("صفحه ششم"), false);
    }

    private void initialize() {
        binding.buttonPre.setOnClickListener(this);
        binding.buttonSubmit.setOnClickListener(this);
        binding.imageViewRefresh.setOnClickListener(this);
        binding.imageViewColorRed.setOnClickListener(this);
        binding.imageViewColorBlue.setOnClickListener(this);
        binding.imageViewColorYellow.setOnClickListener(this);
        binding.signatureView.setPenColor(YELLOW);
    }

    @Override
    public void onResume() {
        super.onResume();
        Bitmap.Config conf = MAP_SELECTED.getConfig();
        if (MAP_SELECTED != null && conf != null) {
            bitmapTemp = MAP_SELECTED.copy(conf, true);
//            binding.signatureView.setBitmap(bitmapTemp.copy(bitmapTemp.getConfig(), true));
            binding.signatureView.setSignatureBitmap(bitmapTemp.copy(conf, true));
        }
    }

    @Override
    public void onClick(View v) {
        final int id = v.getId();
        if (id == R.id.image_view_refresh) {
            Bitmap.Config conf = bitmapTemp.getConfig();
            binding.signatureView.clear();
            if (bitmapTemp != null && conf != null) {
                binding.signatureView.setSignatureBitmap(bitmapTemp.copy(conf, true));
            }
        } else if (id == R.id.image_view_color_blue) {
            binding.signatureView.setPenColor(BLUE);
        } else if (id == R.id.image_view_color_red) {
            binding.signatureView.setPenColor(RED);
        } else if (id == R.id.image_view_color_yellow) {
            binding.signatureView.setPenColor(YELLOW);
        } else if (id == R.id.button_pre) {
            formActivity.setOnPreClickListener(MAP_DESCRIPTION_FRAGMENT);
        } else if (id == R.id.button_submit) {
            MAP_SELECTED = createImage(binding.signatureView.getSignatureBitmap(), true,
                    formActivity.getExaminerDuty().x1, formActivity.getExaminerDuty().y1);
            formActivity.setEditMap();
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
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


    public interface Callback {
        void setOnPreClickListener(int position);

        void setTitle(String title, boolean showMenu);

        ExaminerDuties getExaminerDuty();

        void setEditMap();
    }
}