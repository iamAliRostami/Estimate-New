package com.leon.estimate_new.fragments.dialog;

import static com.leon.estimate_new.fragments.dialog.ShowFragmentDialog.ShowFragmentDialogOnce;
import static com.leon.estimate_new.utils.Validator.billIdValidation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.leon.estimate_new.databinding.FragmentEnterBillBinding;

public class EnterBillFragment extends DialogFragment {
    private FragmentEnterBillBinding binding;

    public EnterBillFragment() {
    }

    public static EnterBillFragment newInstance() {
        return new EnterBillFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentEnterBillBinding.inflate(inflater, container, false);
        initialize();
        return binding.getRoot();
    }

    private void initialize() {
        binding.buttonSearch.setOnClickListener(v -> {
            if (billIdValidation(binding.editTextSearch, requireContext())) {
                String s = binding.editTextSearch.getText().toString();
                dismiss();
                ShowFragmentDialogOnce(requireContext(), "DOCUMENT_FRAGMENT",
                        ShowDocumentFragment.newInstance(s, true, true));
            }

        });
    }

    @Override
    public void onResume() {
        if (getDialog() != null) {
            WindowManager.LayoutParams params = getDialog().getWindow().getAttributes();
            params.width = ViewGroup.LayoutParams.MATCH_PARENT;
            params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
            getDialog().getWindow().setAttributes(params);
        }
        super.onResume();
    }
}