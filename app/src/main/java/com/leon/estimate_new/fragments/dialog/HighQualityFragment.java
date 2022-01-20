package com.leon.estimate_new.fragments.dialog;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.leon.estimate_new.R;
import com.leon.estimate_new.databinding.FragmentHighQualityBinding;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class HighQualityFragment extends DialogFragment {
    private FragmentHighQualityBinding binding;
    private final Bitmap bitmap;

    public HighQualityFragment(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public static HighQualityFragment newInstance(Bitmap bitmap) {
        return new HighQualityFragment(bitmap);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHighQualityBinding.inflate(inflater, container, false);
        initialize();
        return binding.getRoot();
    }

    void initialize() {
        binding.photoView.setImageBitmap(bitmap);
    }

    @Override
    public void onResume() {
        if (getDialog() != null) {
            WindowManager.LayoutParams params = getDialog().getWindow().getAttributes();
            params.width = ViewGroup.LayoutParams.WRAP_CONTENT;
            params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
            getDialog().getWindow().setAttributes(params);
        }
        super.onResume();
    }
}