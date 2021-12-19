package com.leon.estimate_new.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.leon.estimate_new.databinding.FragmentUploadBinding;

public class UploadFragment extends Fragment {
    private FragmentUploadBinding binding;

    public UploadFragment() {
    }

    public static UploadFragment newInstance() {
        return new UploadFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentUploadBinding.inflate(inflater, container, false);
        initialize();
        return binding.getRoot();
    }

    private void initialize() {

    }
}