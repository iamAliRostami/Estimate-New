package com.leon.estimate_new.fragments.forms;

import static com.leon.estimate_new.helpers.Constants.BASE_FRAGMENT;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.leon.estimate_new.R;
import com.leon.estimate_new.adapters.SpinnerCustomAdapter;
import com.leon.estimate_new.databinding.FragmentSecondFormBinding;
import com.leon.estimate_new.tables.ExaminerDuties;

import java.util.ArrayList;
import java.util.Arrays;

public class SecondFormFragment extends Fragment implements View.OnClickListener {
    private FragmentSecondFormBinding binding;
    private Callback formActivity;
    private ExaminerDuties examinerDuty;

    public SecondFormFragment() {
    }

    public static SecondFormFragment newInstance() {
        return new SecondFormFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        formActivity.setTitle(getString(R.string.app_name).concat(" / ").concat("صفحه چهارم"), false);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSecondFormBinding.inflate(inflater, container, false);
        initialize();
        return binding.getRoot();
    }

    private void initialize() {
        examinerDuty = formActivity.getExaminerDuty();
        initializeTextField();
        initializeSpinners();
        initializeCheckBoxField();
        binding.buttonPre.setOnClickListener(this);
        binding.buttonSubmit.setOnClickListener(this);
    }

    private void initializeSpinners() {
        binding.spinnerLoole.setAdapter(new SpinnerCustomAdapter(requireContext(),
                new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.menu_qotr_loole)))));
        binding.spinnerLoole.setSelection(examinerDuty.qotrLooleI);
        binding.spinnerLooleJens.setAdapter(new SpinnerCustomAdapter(requireContext(),
                new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.menu_jens_loole)))));
        binding.spinnerLooleJens.setSelection(examinerDuty.jensLooleI);
    }


    private void initializeTextField() {
        binding.editTextKhaki.setText(String.valueOf(examinerDuty.faseleKhakiA));
        binding.editTextAsphalt.setText(String.valueOf(examinerDuty.faseleAsphaltA));
        binding.editTextSang.setText(String.valueOf(examinerDuty.faseleSangA));
        binding.editTextOther.setText(String.valueOf(examinerDuty.faseleOtherA));

        binding.editTextKhakiFazelab.setText(String.valueOf(examinerDuty.faseleKhakiF));
        binding.editTextAsphaltFazelab.setText(String.valueOf(examinerDuty.faseleAsphaltF));
        binding.editTextSangFazelab.setText(String.valueOf(examinerDuty.faseleSangF));
        binding.editTextOtherFazelab.setText(String.valueOf(examinerDuty.faseleOtherF));

        binding.editTextOmqZirzamin.setText(String.valueOf(examinerDuty.omqeZirzamin));

        binding.editTextEshterak.setText(examinerDuty.eshterak.trim());
        if (!examinerDuty.isNewEnsheab)
            binding.editTextEshterak.setEnabled(false);
    }


    private void initializeCheckBoxField() {
        binding.checkBoxVahedAb.setChecked(examinerDuty.ezharNazarA);
        binding.checkBoxLooleAb.setChecked(examinerDuty.looleA);
        binding.checkBoxLooleFazelab.setChecked(examinerDuty.looleF);
        binding.checkBoxChahAbBaran.setChecked(examinerDuty.chahAbBaran);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.button_pre) {
            formActivity.setOnPreClickListener(BASE_FRAGMENT);
        } else if (id == R.id.button_submit) {
            if (checkForm()) {
                formActivity.setSecondForm(prepareOutput());
            }
        }
    }

    private boolean checkForm() {
        return checkIsNoEmpty(binding.editTextKhaki) &&
                checkIsNoEmpty(binding.editTextAsphalt) &&
                checkIsNoEmpty(binding.editTextSang) &&
                checkIsNoEmpty(binding.editTextOther) &&
                checkIsNoEmpty(binding.editTextKhakiFazelab) &&
                checkIsNoEmpty(binding.editTextAsphaltFazelab) &&
                checkIsNoEmpty(binding.editTextSangFazelab) &&
                checkIsNoEmpty(binding.editTextOtherFazelab) &&
                checkIsNoEmpty(binding.editTextOmqZirzamin) &&
                (!examinerDuty.isNewEnsheab || checkIsNoEmpty(binding.editTextEshterak));
    }

    private boolean checkIsNoEmpty(EditText editText) {
        View focusView;
        if (editText.getText().toString().isEmpty()) {
            editText.setError(getString(R.string.error_empty));
            focusView = editText;
            focusView.requestFocus();
            return false;
        }
        return true;
    }

    private ExaminerDuties prepareOutput() {
        examinerDuty.qotrLooleS = getResources().getStringArray(R.array.menu_qotr_loole)[binding.spinnerLoole.getSelectedItemPosition()];
        examinerDuty.jensLooleS = getResources().getStringArray(R.array.menu_jens_loole)[binding.spinnerLooleJens.getSelectedItemPosition()];
        examinerDuty.jensLooleI = binding.spinnerLooleJens.getSelectedItemPosition();
        examinerDuty.qotrLooleI = binding.spinnerLoole.getSelectedItemPosition();

        examinerDuty.faseleKhakiA = Integer.parseInt(binding.editTextKhaki.getText().toString());
        examinerDuty.faseleKhakiF = Integer.parseInt(binding.editTextKhakiFazelab.getText().toString());
        examinerDuty.faseleAsphaltA = Integer.parseInt(binding.editTextAsphalt.getText().toString());
        examinerDuty.faseleAsphaltF = Integer.parseInt(binding.editTextAsphaltFazelab.getText().toString());

        examinerDuty.faseleSangA = Integer.parseInt(binding.editTextSang.getText().toString());
        examinerDuty.faseleSangF = Integer.parseInt(binding.editTextSangFazelab.getText().toString());
        examinerDuty.faseleOtherA = Integer.parseInt(binding.editTextOther.getText().toString());
        examinerDuty.faseleOtherF = Integer.parseInt(binding.editTextOtherFazelab.getText().toString());

        examinerDuty.omqeZirzamin = Integer.parseInt(binding.editTextOmqZirzamin.getText().toString());

        examinerDuty.chahAbBaran = binding.checkBoxChahAbBaran.isChecked();
        examinerDuty.ezharNazarA = binding.checkBoxVahedAb.isChecked();

        examinerDuty.looleA = binding.checkBoxLooleAb.isChecked();
        examinerDuty.looleF = binding.checkBoxLooleFazelab.isChecked();

        examinerDuty.eshterak = binding.editTextEshterak.getText().toString();

        return examinerDuty;
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
        void setOnPreClickListener(int position);

        void setTitle(String title, boolean showMenu);

        void setSecondForm(ExaminerDuties examinerDuty);

        ExaminerDuties getExaminerDuty();
    }
}