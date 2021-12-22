package com.leon.estimate_new.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.leon.estimate_new.databinding.FragmentPersonalBinding;
import com.leon.estimate_new.tables.ExaminerDuties;

public class PersonalFragment extends Fragment {
    private FragmentPersonalBinding binding;
    private Callback mainActivity;
    private ExaminerDuties examinerDuties;

    public PersonalFragment() {
    }

    public static PersonalFragment newInstance() {
        return new PersonalFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPersonalBinding.inflate(inflater, container, false);
        initialize();
        return binding.getRoot();
    }

    private void initialize() {
        examinerDuties = mainActivity.getExaminerDuty();
        binding.editTextName.setText(examinerDuties.firstName);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof Activity) {
            mainActivity = (Callback) context;
        }

    }

    public interface Callback {
        public void setOnNextClickListener();

        public void setTitle(String title, boolean showMenu);

        public void setExaminerDuty(ExaminerDuties examinerDuties);

        public ExaminerDuties getExaminerDuty();
    }
}