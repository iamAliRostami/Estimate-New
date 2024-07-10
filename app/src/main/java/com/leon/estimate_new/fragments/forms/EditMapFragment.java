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
        binding.buttonPre.setOnClickListener(this);
        binding.buttonSubmit.setOnClickListener(this);
        binding.imageViewRefresh.setOnClickListener(this);
        binding.imageViewColorRed.setOnClickListener(this);
        binding.imageViewColorBlue.setOnClickListener(this);
        binding.imageViewColorYellow.setOnClickListener(this);
        binding.signatureView.setPenColor(YELLOW);

        if (MAP_SELECTED != null) {
            bitmapTemp = MAP_SELECTED.copy(MAP_SELECTED.getConfig(), true);
            binding.signatureView.setBitmap(bitmapTemp.copy(bitmapTemp.getConfig(), true));
        }
    }

    @Override
    public void onClick(View v) {
        final int id = v.getId();
        if (id == R.id.image_view_refresh) {
            binding.signatureView.clearCanvas();
            if (bitmapTemp != null) {
                binding.signatureView.setBitmap(bitmapTemp.copy(bitmapTemp.getConfig(), true));
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