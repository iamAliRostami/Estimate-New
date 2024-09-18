package com.leon.estimate_new.fragments.dialog;

import static com.leon.estimate_new.helpers.MyApplication.getApplicationComponent;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.leon.estimate_new.R;
import com.leon.estimate_new.databinding.FragmentAddDocumentBinding;
import com.leon.estimate_new.utils.document.AddDocumentRadif;
import com.leon.estimate_new.utils.mapper.CustomMapper;


public class AddDocumentFragment extends DialogFragment implements View.OnClickListener {
    private FragmentAddDocumentBinding binding;
    private AddDocumentViewModel addDocumentVM;

    public AddDocumentFragment() {
    }

    public AddDocumentFragment(String trackNumber) {
        addDocumentVM = CustomMapper.INSTANCE.examinerDutyToAddDocumentViewModel(
                getApplicationComponent().MyDatabase().examinerDutiesDao().
                        examinerDutiesByTrackNumber(trackNumber));
    }

    public static AddDocumentFragment newInstance(String trackNumber) {
        return new AddDocumentFragment(trackNumber);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentAddDocumentBinding.inflate(inflater, container, false);
        binding.setAddDocumentVM(addDocumentVM);
        binding.buttonAdd.setOnClickListener(this);
        return binding.getRoot();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.button_add) {
            new AddDocumentRadif(requireContext(), addDocumentVM, binding.buttonAdd).execute(requireActivity());
            dismiss();
        }
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
}