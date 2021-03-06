package com.leon.estimate_new.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.fragment.app.DialogFragment;

import com.leon.estimate_new.activities.ListActivity;
import com.leon.estimate_new.databinding.FragmentSearchBinding;

import org.jetbrains.annotations.NotNull;

public class SearchFragment extends DialogFragment {
    private FragmentSearchBinding binding;
    private ListActivity listActivity;

    public SearchFragment() {
    }

    public SearchFragment(ListActivity listActivity) {
        this.listActivity = listActivity;
    }

    public static SearchFragment newInstance(ListActivity listActivity) {
        return new SearchFragment(listActivity);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSearchBinding.inflate(inflater,container,false);
        initialize();
        return binding.getRoot();
    }

    private void initialize() {
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