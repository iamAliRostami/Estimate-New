package com.leon.estimate_new.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.leon.estimate_new.R;
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
        initializeEditText();
        setOnButtonSubmitClickListener();
        initializeTextViews();
    }

    private void initializeTextViews() {
        binding.textViewZone.setText(examinerDuties.zoneTitle.trim());
        if (examinerDuties.billId != null && examinerDuties.billId.length() > 0)
            binding.textViewBillId.setText(examinerDuties.billId.trim());
        else {
            binding.textViewBillId.setText(examinerDuties.neighbourBillId.trim());
            binding.textViewBillIdTitle.setText(getString(R.string.neighbour_bill_id));
        }
        binding.textViewTrackNumber.setText(examinerDuties.trackNumber.trim());
    }

    private void initializeEditText() {
        binding.editTextName.setText(examinerDuties.firstName.trim());
        binding.editTextFamily.setText(examinerDuties.sureName.trim());

        binding.editTextFatherName.setText(examinerDuties.fatherName.trim());
        binding.editTextShenasname.setText(examinerDuties.shenasname);
        if (examinerDuties.nationalId.trim().length() == 10)
            binding.editTextNationNumber.setText(examinerDuties.nationalId.trim());
        if (examinerDuties.postalCode.trim().length() == 10)
            binding.editTextPostalCode.setText(examinerDuties.postalCode.trim());

        binding.editTextRadif.setText(examinerDuties.radif.trim());
        binding.editTextEshterak.setText(examinerDuties.eshterak.trim());

        binding.editTextMobile.setText(examinerDuties.mobile.trim());
        binding.editTextPhone.setText(examinerDuties.phoneNumber.trim());

        binding.editTextAddress.setText(examinerDuties.address.trim());
        binding.editTextDescription.setText(examinerDuties.description.trim());
    }

    private void setOnButtonSubmitClickListener() {
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