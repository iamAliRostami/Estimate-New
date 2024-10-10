package com.leon.estimate_new.fragments.forms;

import static com.leon.estimate_new.helpers.Constants.PERSONAL_FRAGMENT;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.leon.estimate_new.R;
import com.leon.estimate_new.adapters.CheckBoxAdapter;
import com.leon.estimate_new.databinding.FragmentServicesBinding;
import com.leon.estimate_new.tables.ExaminerDuties;
import com.leon.estimate_new.tables.RequestDictionary;
import com.leon.estimate_new.utils.CustomToast;

import java.util.ArrayList;

public class ServicesFragment extends Fragment {
    private FragmentServicesBinding binding;
    private CheckBoxAdapter checkBoxAdapter;
    private Callback formActivity;

    public ServicesFragment() {
    }

    public static ServicesFragment newInstance() {
        return new ServicesFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentServicesBinding.inflate(inflater, container, false);
        initialize();
        return binding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        formActivity.setTitle(getString(R.string.app_name).concat(" / ").concat("صفحه دوم"), false);
    }

    private void initialize() {
        initializeServices();
        setOnButtonsClickListener();
    }

    private void initializeServices() {
        checkBoxAdapter = new CheckBoxAdapter(requireContext(), formActivity.getServiceDictionaries());
        binding.gridView.setAdapter(checkBoxAdapter);
    }

    private void setOnButtonsClickListener() {
        binding.buttonPre.setOnClickListener(v -> formActivity.setOnPreClickListener(PERSONAL_FRAGMENT));
        binding.buttonSubmit.setOnClickListener(v -> {
            if (checkBoxAdapter.isRequestDictionariesEmpty()) {
                formActivity.setServices(checkBoxAdapter.getRequestDictionaries());
            } else {
                new CustomToast().warning(getString(R.string.select_service));
            }
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
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public interface Callback {
        void setOnPreClickListener(int position);

        void setTitle(String title, boolean showMenu);

        void setServices(ArrayList<RequestDictionary> requestDictionaries);

        ExaminerDuties getExaminerDuty();

        ArrayList<RequestDictionary> getServiceDictionaries();
    }
}