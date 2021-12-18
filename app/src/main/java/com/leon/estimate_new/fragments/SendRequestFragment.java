package com.leon.estimate_new.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.leon.estimate_new.databinding.FragmentSendRequestBinding;

import org.jetbrains.annotations.NotNull;

public class SendRequestFragment extends Fragment {
    private FragmentSendRequestBinding binding;

    public SendRequestFragment() {
    }

    public static SendRequestFragment newInstance() {
        return new SendRequestFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSendRequestBinding.inflate(inflater, container, false);
        initialize();
        return binding.getRoot();
    }

    private void initialize() {
    }
}