package com.leon.estimate_new.fragments.forms;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.leon.estimate_new.R;
import com.leon.estimate_new.databinding.FragmentPersonalBinding;
import com.leon.estimate_new.tables.ExaminerDuties;

public class PersonalFragment extends Fragment implements View.OnClickListener {
    private FragmentPersonalBinding binding;
    private Callback formActivity;
    private ExaminerDuties examinerDuties;

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
        initialize();
        return binding.getRoot();
    }

    private void initialize() {
        examinerDuties = formActivity.getExaminerDuty();
        initializeEditText();
        initializeTextViews();
        binding.buttonClose.setOnClickListener(this);
        binding.buttonSubmit.setOnClickListener(this);
    }

    private void initializeTextViews() {
        binding.textViewZone.setText(examinerDuties.zoneTitle.trim());
        if (examinerDuties.billId != null && !examinerDuties.billId.isEmpty())
            binding.textViewBillId.setText(examinerDuties.billId.trim());
        else {
            binding.textViewBillId.setText(examinerDuties.neighbourBillId.trim());
            binding.textViewBillIdTitle.setText(getString(R.string.neighbour_bill_id));
        }
        binding.textViewTrackNumber.setText(examinerDuties.trackNumber.trim());
    }

    private void initializeEditText() {
        binding.titleEditTextName.setInputText(examinerDuties.firstName.trim());

        binding.titleEditTextFamily.setInputText(examinerDuties.sureName.trim());

        Log.e("id 3", String.valueOf(binding.titleEditTextFamily.getId()));

        binding.editTextFatherName.setText(examinerDuties.fatherName.trim());
        binding.editTextShenasname.setText(examinerDuties.shenasname);
        if (examinerDuties.nationalId.trim().length() == 10)
            binding.editTextNationalCode.setText(examinerDuties.nationalId.trim());
        if (examinerDuties.postalCode.trim().length() == 10)
            binding.editTextPostalCode.setText(examinerDuties.postalCode.trim());

        binding.editTextRadif.setText(examinerDuties.radif.trim());
        binding.editTextEshterak.setText(examinerDuties.eshterak.trim());

        binding.editTextMobile.setText(examinerDuties.mobile.trim());
        binding.editTextPhone.setText(examinerDuties.phoneNumber.trim());

        binding.editTextAddress.setText(examinerDuties.address.trim());
        binding.editTextDescription.setText(examinerDuties.description.trim());
    }


    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.button_close) {
            requireActivity().finish();
        } else if (id == R.id.button_submit) {
            if (checkForm()) {
                formActivity.setPersonalInfo(prepareOutput());
            }
        }
    }

    private boolean checkForm() {
        return binding.titleEditTextName.validation()
                && binding.titleEditTextFamily.validation()
                /* && checkIsNoEmpty(binding.editTextFatherName)*/
                && checkIsNoEmpty(binding.editTextNationalCode)
                && checkIsNoEmpty(binding.editTextAddress)
                && checkOtherIsNoEmpty();
    }

    private boolean checkIsNoEmpty(EditText editText) {
        final View focusView;
        if (editText.getText().toString().isEmpty()) {
            editText.setError(getString(R.string.error_empty));
            focusView = editText;
            focusView.requestFocus();
            return false;
        }
        return true;
    }

    private boolean checkOtherIsNoEmpty() {
        final View focusView;
        if (binding.editTextNationalCode.getText().toString().length() < 10) {
            binding.editTextNationalCode.setError(getString(R.string.error_format));
            focusView = binding.editTextNationalCode;
            focusView.requestFocus();
            return false;
        } else if (!binding.editTextPostalCode.getText().toString().isEmpty() &&
                binding.editTextPostalCode.getText().toString().length() < 10) {
            binding.editTextPostalCode.setError(getString(R.string.error_format));
            focusView = binding.editTextPostalCode;
            focusView.requestFocus();
            return false;
        } else if (binding.editTextMobile.getText().toString().length() < 11) {
            binding.editTextMobile.setError(getString(R.string.error_format));
            focusView = binding.editTextMobile;
            focusView.requestFocus();
            return false;
        }
        return true;
    }

    private ExaminerDuties prepareOutput() {
        examinerDuties.firstName = binding.titleEditTextName.getInputText();
        examinerDuties.sureName = binding.titleEditTextFamily.getInputText();


        examinerDuties.nationalId = binding.editTextNationalCode.getText().toString();
        examinerDuties.fatherName = binding.editTextFatherName.getText().toString();
        examinerDuties.postalCode = binding.editTextPostalCode.getText().toString();
        examinerDuties.radif = binding.editTextRadif.getText().toString();
        examinerDuties.phoneNumber = binding.editTextPhone.getText().toString();
        examinerDuties.mobile = binding.editTextMobile.getText().toString();
        examinerDuties.address = binding.editTextAddress.getText().toString();
        examinerDuties.description = binding.editTextDescription.getText().toString();
        examinerDuties.shenasname = binding.editTextShenasname.getText().toString();
        return examinerDuties;
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

        void setPersonalInfo(ExaminerDuties examinerDuties);

        ExaminerDuties getExaminerDuty();
    }
}