package com.leon.estimate_new.fragments.forms;

import static com.leon.estimate_new.utils.Validator.checkEmpty;
import static com.leon.estimate_new.utils.Validator.mobileValidation;
import static com.leon.estimate_new.utils.Validator.nationalIdValidation;
import static com.leon.estimate_new.utils.Validator.phoneValidation;
import static com.leon.estimate_new.utils.Validator.postalCodeValidation;

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
import com.leon.estimate_new.databinding.FragmentPersonalBinding;
import com.leon.estimate_new.tables.ExaminerDuties;
import com.leon.estimate_new.utils.mapper.CustomMapper;

public class PersonalFragment extends Fragment implements View.OnClickListener {
    private FragmentPersonalBinding binding;
    private PersonalViewModel personalVM;
    private Callback formActivity;

    public PersonalFragment() {
    }

    public static PersonalFragment newInstance() {
        return new PersonalFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        formActivity.setTitle(getString(R.string.app_name).concat(" / ").concat("صفحه اول"), false);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPersonalBinding.inflate(inflater, container, false);
        personalVM = CustomMapper.INSTANCE.examinerDutyToPersonalViewModel(formActivity.getExaminerDuty());
        binding.setPersonalVM(personalVM);
        initialize();
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void initialize() {
        initializeTextViews();
        binding.buttonClose.setOnClickListener(this);
        binding.buttonSubmit.setOnClickListener(this);
    }

    private void initializeTextViews() {
        if (formActivity.getExaminerDuty().isNewEnsheab) {
            binding.textViewBillIdTitle.setText(getString(R.string.neighbour_bill_id));
        }
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.button_close) {
            requireActivity().finish();
        } else if (id == R.id.button_submit) {
            if (checkForm()) {
                personalVM.setFirstName(binding.editTextName.getText().toString());
                personalVM.setSureName(binding.editTextFamily.getText().toString());
                personalVM.setFatherName(binding.editTextFatherName.getText().toString());
                personalVM.setAddress(binding.editTextAddress.getText().toString());
                personalVM.setDescription(binding.editTextDescription.getText().toString());
                formActivity.setPersonalInfo(personalVM);
            }
        }
    }

    private boolean checkForm() {
        return checkEmpty(binding.editTextName, requireContext())
                && checkEmpty(binding.editTextFamily, requireContext())
                && nationalIdValidation(binding.editTextNationalId, requireContext())
                && postalCodeValidation(binding.editTextPostalCode, requireContext())
                && mobileValidation(binding.editTextMobile, requireContext())
                && phoneValidation(binding.editTextPhone, requireContext())
                && checkEmpty(binding.editTextAddress, requireContext());
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