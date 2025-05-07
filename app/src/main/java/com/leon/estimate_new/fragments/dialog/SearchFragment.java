package com.leon.estimate_new.fragments.dialog;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;
import com.leon.estimate_new.R;
import com.leon.estimate_new.databinding.FragmentSearchBinding;
import com.leon.estimate_new.fragments.main_items.DutiesListFragment;

import org.jetbrains.annotations.NotNull;

import ir.hamsaa.persiandatepicker.PersianDatePickerDialog;
import ir.hamsaa.persiandatepicker.api.PersianPickerDate;
import ir.hamsaa.persiandatepicker.api.PersianPickerListener;

public class SearchFragment extends DialogFragment {
    private final Callback dutiesListFragment;
    private final SearchViewModel searchVM = new SearchViewModel();
    private FragmentSearchBinding binding;

    public SearchFragment(DutiesListFragment dutiesListFragment) {
        this.dutiesListFragment = dutiesListFragment;
    }

    public static SearchFragment newInstance(Fragment dutiesListFragment) {
        return new SearchFragment((DutiesListFragment) dutiesListFragment);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSearchBinding.inflate(inflater, container, false);
        binding.setSearchVM(searchVM);
        initialize();
        return binding.getRoot();
    }

    private void initialize() {
        setOnButtonSearchClickListener();
        setOnTextViewStartDateClickListener();
    }

    private void setOnButtonSearchClickListener() {
        binding.buttonSearch.setOnClickListener(v -> {
            dutiesListFragment.filter(searchVM.getBillId(), searchVM.getTrackNumber(),
                    searchVM.getName(), searchVM.getFamily(), searchVM.getNationalId(),
                    searchVM.getMobile(), searchVM.getStartDate());
            dismiss();
        });
    }

    private void setOnTextViewStartDateClickListener() {
        binding.textViewStartDate.setOnClickListener(v ->
                new PersianDatePickerDialog(getActivity())
                        .setPositiveButtonString(getString(R.string.confirm))
                        .setNegativeButton(getString(R.string.cancel))
                        .setTodayButton(getString(R.string.today))
                        .setMinYear(1300)
                        .setTodayButtonVisible(true)
                        .setActionTextColor(Color.GRAY)
                        .setTitleType(PersianDatePickerDialog.WEEKDAY_DAY_MONTH_YEAR)
                        .setShowInBottomSheet(true)
                        .setListener(new PersianPickerListener() {
                            @Override
                            public void onDateSelected(@NotNull PersianPickerDate persianPickerDate) {
                                String month = persianPickerDate.getPersianMonth() > 10 ?
                                        String.valueOf(persianPickerDate.getPersianMonth()) :
                                        "0" + persianPickerDate.getPersianMonth();
                                String day = persianPickerDate.getPersianDay() > 10 ?
                                        String.valueOf(persianPickerDate.getPersianDay()) :
                                        "0" + persianPickerDate.getPersianDay();
                                String date = persianPickerDate.getPersianYear() + "/" + month + "/" +
                                        day;
                                ((TextInputEditText) v).setText(date);
                            }

                            @Override
                            public void onDismissed() {

                            }
                        }).show());
    }

    @Override
    public void onResume() {
        if (getDialog() != null && getDialog().getWindow() != null) {
            WindowManager.LayoutParams params = getDialog().getWindow().getAttributes();
            params.width = ViewGroup.LayoutParams.MATCH_PARENT;
            params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
            getDialog().getWindow().setAttributes(params);
        }
        super.onResume();
    }

    public interface Callback {
        void filter(String... s);
    }

}