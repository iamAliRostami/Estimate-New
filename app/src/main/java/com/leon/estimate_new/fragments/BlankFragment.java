package com.leon.estimate_new.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.leon.estimate_new.R;
import com.leon.estimate_new.databinding.FragmentBlankBinding;
import com.leon.estimate_new.databinding.FragmentPersonalBinding;
import com.leon.estimate_new.fragments.forms.PersonalFragment;
import com.leon.estimate_new.fragments.forms.PersonalViewModel;
import com.leon.estimate_new.tables.ExaminerDuties;
import com.leon.estimate_new.utils.mapper.CustomMapper;

public class BlankFragment extends Fragment {
private FragmentBlankBinding binding;
    private PersonalViewModel personalVM;
    private Callback formActivity;

    public BlankFragment() {
    }

    public static BlankFragment newInstance() {
        return new BlankFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentBlankBinding.inflate(inflater, container, false);
        personalVM = CustomMapper.INSTANCE.examinerDutyToPersonalViewModel(formActivity.getExaminerDuty());
        binding.setPersonalVM(personalVM);
        return  binding.getRoot();
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

        void setTitle(String title, boolean showMenu);

        void setPersonalInfo(PersonalViewModel personalViewModel);

        ExaminerDuties getExaminerDuty();
    }
}