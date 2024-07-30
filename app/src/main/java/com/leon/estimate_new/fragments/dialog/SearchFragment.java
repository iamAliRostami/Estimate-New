package com.leon.estimate_new.fragments.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.leon.estimate_new.databinding.FragmentSearchBinding;
import com.leon.estimate_new.fragments.main_items.DutiesListFragment;
import com.sardari.daterangepicker.customviews.DateRangeCalendarView;
import com.sardari.daterangepicker.dialog.DatePickerDialog;

import org.jetbrains.annotations.NotNull;

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
        binding.textViewStartDate.setOnClickListener(v -> {
            DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity());
            datePickerDialog.setSelectionMode(DateRangeCalendarView.SelectionMode.Single);
            datePickerDialog.setDisableDaysAgo(false);
            datePickerDialog.setTextSizeTitle(10.0f);
            datePickerDialog.setTextSizeWeek(12.0f);
            datePickerDialog.setTextSizeDate(14.0f);
            datePickerDialog.setCanceledOnTouchOutside(true);
            datePickerDialog.setOnSingleDateSelectedListener(date ->
                    binding.textViewStartDate.setText(date.getPersianShortDate()));
            datePickerDialog.showDialog();
        });
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