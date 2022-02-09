package com.leon.estimate_new.fragments.dialog;

import static com.leon.estimate_new.enums.BundleEnum.BILL_ID;
import static com.leon.estimate_new.enums.BundleEnum.IS_NEIGHBOUR;
import static com.leon.estimate_new.enums.BundleEnum.NEW_ENSHEAB;
import static com.leon.estimate_new.enums.BundleEnum.TRACK_NUMBER;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.leon.estimate_new.databinding.FragmentShowDocumentBinding;

public class ShowDocumentFragment extends DialogFragment {
    private FragmentShowDocumentBinding binding;
    private String billId, trackNumber;
    private boolean isNew, isNeighbour;

    public ShowDocumentFragment() {
    }

    public static ShowDocumentFragment newInstance(String billId, boolean isNew, boolean isNeighbour
            , String... trackNumber) {
        final ShowDocumentFragment fragment = new ShowDocumentFragment();
        Bundle args = new Bundle();
        if (trackNumber.length > 0)
            args.putString(TRACK_NUMBER.getValue(), trackNumber[0]);
        args.putString(BILL_ID.getValue(), billId);
        args.putBoolean(NEW_ENSHEAB.getValue(), isNew);
        args.putBoolean(IS_NEIGHBOUR.getValue(), isNeighbour);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            trackNumber = getArguments().getString(TRACK_NUMBER.getValue());
            billId = getArguments().getString(BILL_ID.getValue());
            isNew = getArguments().getBoolean(NEW_ENSHEAB.getValue());
            isNeighbour = getArguments().getBoolean(IS_NEIGHBOUR.getValue());
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentShowDocumentBinding.inflate(inflater, container, false);
        initialize();
        return binding.getRoot();
    }

    private void initialize() {

    }

    @Override
    public void onResume() {
        if (getDialog() != null) {
            WindowManager.LayoutParams params = getDialog().getWindow().getAttributes();
            params.width = ViewGroup.LayoutParams.MATCH_PARENT;
            params.height = ViewGroup.LayoutParams.MATCH_PARENT;
            getDialog().getWindow().setAttributes(params);
        }
        super.onResume();
    }
}