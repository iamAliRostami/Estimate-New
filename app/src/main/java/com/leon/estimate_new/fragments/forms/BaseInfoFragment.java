package com.leon.estimate_new.fragments.forms;

import static com.leon.estimate_new.fragments.dialog.ShowFragmentDialog.ShowFragmentDialogOnce;
import static com.leon.estimate_new.helpers.Constants.SERVICES_FRAGMENT;

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
import com.leon.estimate_new.databinding.FragmentBaseInfoBinding;
import com.leon.estimate_new.fragments.dialog.TejarihaSayerFragment;
import com.leon.estimate_new.fragments.dialog.ValueFragment;
import com.leon.estimate_new.tables.Arzeshdaraei;
import com.leon.estimate_new.tables.ExaminerDuties;
import com.leon.estimate_new.tables.KarbariDictionary;
import com.leon.estimate_new.tables.NoeVagozariDictionary;
import com.leon.estimate_new.tables.QotrEnsheabDictionary;
import com.leon.estimate_new.tables.TaxfifDictionary;
import com.leon.estimate_new.tables.Tejariha;
import com.leon.estimate_new.utils.estimating.GetArzeshdaraei;
import com.sardari.daterangepicker.customviews.DateRangeCalendarView;
import com.sardari.daterangepicker.dialog.DatePickerDialog;

import java.util.ArrayList;
import java.util.Arrays;

public class BaseInfoFragment extends Fragment implements ValueFragment.Callback,
        TejarihaSayerFragment.Callback, View.OnClickListener, View.OnFocusChangeListener {
    private FragmentBaseInfoBinding binding;
    private ExaminerDuties examinerDuty;
    private Arzeshdaraei arzeshdaraei;
    private Callback formActivity;
    private String block, arz;

    public static BaseInfoFragment newInstance() {
        return new BaseInfoFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        formActivity.setTitle(getString(R.string.app_name).concat(" / ").concat("صفحه سوم"), false);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentBaseInfoBinding.inflate(inflater, container, false);
        initialize();
        return binding.getRoot();
    }

    private void initialize() {
        examinerDuty = formActivity.getExaminerDuty();
        arzeshdaraei = formActivity.getArzeshdaraei();
        block = examinerDuty.block != null ? examinerDuty.block : "-";
        arz = examinerDuty.arz != null ? examinerDuty.arz : "-";
        initializeSpinner();
        initializeField();
        setOnClickListener();
        setOnFocusChangeListener();
    }

    private void setOnFocusChangeListener() {
        binding.editTextTedadSaier.setOnFocusChangeListener(this);
        binding.editTextTedadTejari.setOnFocusChangeListener(this);
    }

    private void setOnClickListener() {
        binding.editTextSodurDate.setOnClickListener(this);
        binding.textViewArzeshMelk.setOnClickListener(this);

        binding.buttonSubmit.setOnClickListener(this);
        binding.buttonPre.setOnClickListener(this);
        binding.textViewTedadTejari.setOnClickListener(this);
        binding.textViewTedadSaier.setOnClickListener(this);

        if ((examinerDuty.tedadSaierNew != null && examinerDuty.tedadSaierNew > 0)
                || (examinerDuty.tedadTejariNew != null && examinerDuty.tedadTejariNew > 0)) {
            binding.textViewTedadSaier.setEnabled(true);
            binding.textViewTedadTejari.setEnabled(true);
        } else {
            binding.textViewTedadSaier.setEnabled(false);
            binding.textViewTedadTejari.setEnabled(false);
        }
    }

    private void initializeField() {
        binding.editTextSifoon100.setText(String.valueOf(examinerDuty.sifoon100));
        binding.editTextSifoon125.setText(String.valueOf(examinerDuty.sifoon125));
        binding.editTextSifoon150.setText(String.valueOf(examinerDuty.sifoon150));
        binding.editTextSifoon200.setText(String.valueOf(examinerDuty.sifoon150));
        binding.editTextArse.setText(examinerDuty.arseNew != null ?
                String.valueOf(examinerDuty.arseNew) : String.valueOf(examinerDuty.arse));
        binding.editTextAianKol.setText(examinerDuty.aianKolNew != null ?
                String.valueOf(examinerDuty.aianKolNew) : String.valueOf(examinerDuty.aianKol));
        binding.editTextAianMaskooni.setText(examinerDuty.aianMaskooniNew != null ?
                String.valueOf(examinerDuty.aianMaskooniNew) : String.valueOf(examinerDuty.aianMaskooni));
        binding.editTextAianNonMaskooni.setText(examinerDuty.aianNonMaskooniNew != null ?
                String.valueOf(examinerDuty.aianNonMaskooniNew) : String.valueOf(examinerDuty.aianNonMaskooni));
        binding.editTextTedadMaskooni.setText(examinerDuty.tedadMaskooniNew != null ?
                String.valueOf(examinerDuty.tedadMaskooniNew) : String.valueOf(examinerDuty.tedadMaskooni));
        binding.editTextTedadTejari.setText(examinerDuty.tedadTejariNew != null ?
                String.valueOf(examinerDuty.tedadTejariNew) : String.valueOf(examinerDuty.tedadTejari));
        binding.editTextTedadSaier.setText(examinerDuty.tedadSaierNew != null ?
                String.valueOf(examinerDuty.tedadSaierNew) : String.valueOf(examinerDuty.tedadSaier));
        binding.editTextTedadTakhfif.setText(String.valueOf(examinerDuty.tedadTaxfif));
        binding.editTextZarfiatQaradadi.setText(examinerDuty.zarfiatQarardadiNew != null ?
                String.valueOf(examinerDuty.zarfiatQarardadiNew) : String.valueOf(examinerDuty.zarfiatQarardadi));
        binding.editTextLicenceNumber.setText(examinerDuty.licenceNumber);
        /*binding.editTextCodeKaf.setText(examinerDuty.codeKaf);*/
        binding.editTextSanadNumber.setText(String.valueOf(examinerDuty.sanadNumber));
        binding.editTextQarardad.setText(examinerDuty.qaradadNumber);
//        binding.editTextSodurDate.setText(examinerDuty.getExaminationDay());
        binding.editTextPelak.setText(String.valueOf(examinerDuty.pelak));

        binding.textViewArzeshMelk.setText(String.valueOf(examinerDuty.arzeshMelk));

        binding.checkbox1.setChecked(examinerDuty.isEnsheabQeirDaem);
        binding.checkbox6.setChecked(examinerDuty.adamLicence);
        binding.checkbox7.setChecked(examinerDuty.qaradad);
    }

    private void initializeSpinner() {
        initializeKarbariSpinner();
        initializeNoeVagozariSpinner();
        initializeQotrEnsheabSpinner();
        initializeQotrFazelabSpinner();
        initializeNoeEnsheabSpinner();
        initializeTaxfifSpinner();
    }


    private void initializeKarbariSpinner() {
        final ArrayList<String> spinnerArrayList = new ArrayList<>();
        int selected = 0, counter = 0;
        for (KarbariDictionary karbariDictionary : formActivity.getKarbariDictionary()) {
            spinnerArrayList.add(karbariDictionary.title);
            if (karbariDictionary.id == examinerDuty.karbariId) {
                selected = counter;
            }
            counter = counter + 1;
        }
        binding.spinner1.setAdapter(new SpinnerCustomAdapter(requireContext(), spinnerArrayList));
        binding.spinner1.setSelection(selected);
    }

    private void initializeNoeVagozariSpinner() {
        final ArrayList<String> spinnerArrayList = new ArrayList<>();
        for (NoeVagozariDictionary noeVagozariDictionary : formActivity.getNoeVagozariDictionaries()) {
            spinnerArrayList.add(noeVagozariDictionary.title);
        }
        binding.spinner2.setAdapter(new SpinnerCustomAdapter(requireContext(), spinnerArrayList));
        binding.spinner2.setSelection(examinerDuty.noeVagozariId);
    }

    private void initializeQotrEnsheabSpinner() {
        final ArrayList<String> spinnerArrayList = new ArrayList<>();
        int counter = 0, selectedA = 0;
        for (QotrEnsheabDictionary qotrEnsheabDictionary : formActivity.getQotrEnsheabDictionary()) {
            spinnerArrayList.add(qotrEnsheabDictionary.title);
            if (examinerDuty.qotrEnsheabId == qotrEnsheabDictionary.id) selectedA = counter;
            counter = counter + 1;
        }
        binding.spinner3.setAdapter(new SpinnerCustomAdapter(requireContext(), spinnerArrayList));
        binding.spinner3.setSelection(selectedA);
    }

    private void initializeQotrFazelabSpinner() {
        final ArrayList<String> spinnerArrayList = new ArrayList<>(Arrays.asList("0", "100", "125", "150", "200"));
        binding.spinner4.setAdapter(new SpinnerCustomAdapter(requireContext(), spinnerArrayList));
        for (int i = 0; i < spinnerArrayList.size(); i++) {
            if (examinerDuty.qotrEnsheabFS != null &&
                    examinerDuty.qotrEnsheabFS.equals(spinnerArrayList.get(i))) {
                binding.spinner4.setSelection(i);
            }
        }
    }

    private void initializeNoeEnsheabSpinner() {
        ArrayList<String> spinnerArrayList = new ArrayList<>();
        spinnerArrayList.add("مشخص نشده");
        spinnerArrayList.add("انشعاب خاص");
        spinnerArrayList.add("انشعاب عادی");
        binding.spinner5.setAdapter(new SpinnerCustomAdapter(requireContext(), spinnerArrayList));
    }

    private void initializeTaxfifSpinner() {
        final ArrayList<String> spinnerArrayList = new ArrayList<>();
        int selected = 0, counter = 0;
        for (TaxfifDictionary taxfifDictionary : formActivity.getTaxfifDictionary()) {
            spinnerArrayList.add(taxfifDictionary.title);
            if (taxfifDictionary.id == examinerDuty.taxfifId) {
                selected = counter;
            }
            counter = counter + 1;
        }
        binding.spinner6.setAdapter(new SpinnerCustomAdapter(requireContext(), spinnerArrayList));
        binding.spinner6.setSelection(selected);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.text_view_arzesh_melk) {
            if (arzeshdaraei != null && !arzeshdaraei.blocks.isEmpty() && !arzeshdaraei.formulas.isEmpty() && !arzeshdaraei.zaribs.isEmpty()) {
                ShowFragmentDialogOnce(requireContext(), "VALUE_FRAGMENT", ValueFragment.newInstance(this));
            } else {
                new GetArzeshdaraei(requireContext(), examinerDuty.zoneId, this).execute(requireActivity());
            }
        } else if (id == R.id.edit_text_sodur_date) {
            DatePickerDialog datePickerDialog = new DatePickerDialog(requireContext());
            datePickerDialog.setSelectionMode(DateRangeCalendarView.SelectionMode.Single);
            datePickerDialog.setDisableDaysAgo(false);
            datePickerDialog.setTextSizeTitle(10.0f);
            datePickerDialog.setTextSizeWeek(12.0f);
            datePickerDialog.setTextSizeDate(14.0f);
            datePickerDialog.setCanceledOnTouchOutside(true);
            datePickerDialog.setOnSingleDateSelectedListener(date ->
                    binding.editTextSodurDate.setText(date.getPersianShortDate()));
            datePickerDialog.showDialog();
        } else if (id == R.id.text_view_tedad_tejari || id == R.id.text_view_tedad_saier) {
            showTejariha();
        } else if (id == R.id.button_pre) {
            formActivity.setOnPreClickListener(SERVICES_FRAGMENT);
        } else if (id == R.id.button_submit) {
            if (checkForm())
                formActivity.setBaseInfo(prepareOutput());
        }
    }

    @Override
    public void onFocusChange(View view, boolean b) {
        if (!b) {
            int saier, tejari;
            saier = !binding.editTextTedadSaier.getText().toString().isEmpty() ?
                    Integer.parseInt(binding.editTextTedadSaier.getText().toString()) : 0;
            tejari = !binding.editTextTedadTejari.getText().toString().isEmpty() ?
                    Integer.parseInt(binding.editTextTedadTejari.getText().toString()) : 0;
            binding.textViewTedadSaier.setEnabled(saier > 0 || tejari > 0);
            binding.textViewTedadTejari.setEnabled(saier > 0 || tejari > 0);
            if (saier > 0 || tejari > 0)
                showTejariha();
        }
    }

    private void showTejariha() {
        ShowFragmentDialogOnce(requireContext(), "TEJARI_SAYER_FRAGMENT",
                TejarihaSayerFragment.newInstance(this));
    }

    private boolean checkAian() {
        examinerDuty.aianMaskooniNew = binding.editTextAianMaskooni.getText().toString().isEmpty() ? 0 :
                Integer.parseInt(binding.editTextAianMaskooni.getText().toString());
        examinerDuty.aianNonMaskooniNew = binding.editTextAianNonMaskooni.getText().toString().isEmpty() ? 0 :
                Integer.parseInt(binding.editTextAianNonMaskooni.getText().toString());
        examinerDuty.aianKolNew = binding.editTextAianKol.getText().toString().isEmpty() ? 0 :
                Integer.parseInt(binding.editTextAianKol.getText().toString());
        if (examinerDuty.aianKolNew < examinerDuty.aianMaskooniNew + examinerDuty.aianNonMaskooniNew) {
            binding.editTextAianKol.setError("اعیان کل از مجموع اعیان مسکونی و تجاری کوچک تر است");
            binding.editTextAianKol.requestFocus();
            return false;
        }
        return true;
    }

    private boolean checkForm() {
        return checkAian()
                && checkIsNoEmpty(binding.editTextSifoon100)
                && checkIsNoEmpty(binding.editTextSifoon125)
                && checkIsNoEmpty(binding.editTextSifoon150)
                && checkIsNoEmpty(binding.editTextSifoon200)
                && checkIsNoEmpty(binding.editTextArse)
                && checkIsNoEmpty(binding.editTextAianKol)
                && checkIsNoEmpty(binding.editTextAianMaskooni)
                && checkIsNoEmpty(binding.editTextAianNonMaskooni)
                && checkIsNoEmpty(binding.editTextTedadMaskooni)
                && checkIsNoEmpty(binding.editTextTedadTejari)
                && checkIsNoEmpty(binding.editTextTedadSaier)
                && checkIsNoEmpty(binding.editTextTedadTakhfif)
                && checkIsNoEmpty(binding.editTextZarfiatQaradadi)
                && checkIsNoEmpty(binding.textViewArzeshMelk);
    }

    private boolean checkIsNoEmpty(EditText editText) {
        if (editText.getText().toString().isEmpty()) {
            editText.setError(getString(R.string.error_empty));
            editText.requestFocus();
            return false;
        }
        return true;
    }

    private ExaminerDuties prepareOutput() {
        examinerDuty.block = block;
        examinerDuty.arz = arz;
        examinerDuty.sifoon100 = Integer.parseInt(binding.editTextSifoon100.getText().toString());
        examinerDuty.sifoon125 = Integer.parseInt(binding.editTextSifoon125.getText().toString());
        examinerDuty.sifoon150 = Integer.parseInt(binding.editTextSifoon150.getText().toString());
        examinerDuty.sifoon200 = Integer.parseInt(binding.editTextSifoon200.getText().toString());
        examinerDuty.arseNew = binding.editTextArse.getText().toString().isEmpty() ? 0 :
                Integer.parseInt(binding.editTextArse.getText().toString());
        examinerDuty.tedadMaskooniNew = binding.editTextTedadMaskooni.getText().toString().isEmpty() ? 0 :
                Integer.parseInt(binding.editTextTedadMaskooni.getText().toString());
        examinerDuty.tedadTejariNew = binding.editTextTedadTejari.getText().toString().isEmpty() ? 0 :
                Integer.parseInt(binding.editTextTedadTejari.getText().toString());
        examinerDuty.tedadSaierNew = binding.editTextTedadSaier.getText().toString().isEmpty() ? 0 :
                Integer.parseInt(binding.editTextTedadSaier.getText().toString());

        examinerDuty.arzeshMelk = Integer.parseInt(binding.textViewArzeshMelk.getText().toString());
        examinerDuty.tedadTaxfif = Integer.parseInt(binding.editTextTedadTakhfif.getText().toString());
        examinerDuty.zarfiatQarardadiNew = binding.editTextZarfiatQaradadi.getText().toString().isEmpty() ? 0 :
                Integer.parseInt(binding.editTextZarfiatQaradadi.getText().toString());
        examinerDuty.licenceNumber = binding.editTextLicenceNumber.getText().toString();

        examinerDuty.karbariId = formActivity.getKarbariDictionary()
                .get(binding.spinner1.getSelectedItemPosition()).id;
        examinerDuty.karbariS = binding.spinner1.getSelectedItem().toString();

        examinerDuty.noeVagozariId = formActivity.getNoeVagozariDictionaries()
                .get(binding.spinner2.getSelectedItemPosition()).id;
        examinerDuty.noeVagozariS = binding.spinner2.getSelectedItem().toString();

        examinerDuty.qotrEnsheabId = formActivity.getQotrEnsheabDictionary()
                .get(binding.spinner3.getSelectedItemPosition()).id;
        examinerDuty.qotrEnsheabS = binding.spinner3.getSelectedItem().toString();

        examinerDuty.qotrEnsheabFS = binding.spinner4.getSelectedItem().toString();

        examinerDuty.ensheabType = binding.spinner5.getSelectedItem().toString();

        examinerDuty.taxfifId = formActivity.getTaxfifDictionary()
                .get(binding.spinner6.getSelectedItemPosition()).id;
        examinerDuty.taxfifS = binding.spinner6.getSelectedItem().toString();

        examinerDuty.isEnsheabQeirDaem = binding.checkbox1.isChecked();

        examinerDuty.adamLicence = binding.checkbox6.isChecked();
        examinerDuty.qaradad = binding.checkbox7.isChecked();
        examinerDuty.qaradadNumber = binding.editTextQarardad.getText().toString();
        examinerDuty.pelak = binding.editTextPelak.getText().toString().isEmpty() ? 0 :
                Integer.parseInt(binding.editTextPelak.getText().toString());
        examinerDuty.sanadNumber = Integer.parseInt(binding.editTextSanadNumber.getText().toString());
        examinerDuty.sodurDate = binding.editTextSodurDate.getText().toString();
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
    public Arzeshdaraei getArzeshdaraei() {
        return arzeshdaraei;
    }

    public void setArzeshDaraei(Arzeshdaraei arzeshdaraei) {
        this.arzeshdaraei = arzeshdaraei;
    }

    @Override
    public void setValue(ArrayList<Integer> values, int value, String block, String arz) {
        binding.textViewArzeshMelk.setText(String.valueOf(value));
        this.arz = arz;
        this.block = block;
        formActivity.setValues(values);
    }

    @Override
    public ArrayList<Integer> getValue() {
        return formActivity.getValues();
    }

    @Override
    public ArrayList<KarbariDictionary> getKarbariDictionary() {
        return formActivity.getKarbariDictionary();
    }

    @Override
    public ArrayList<Tejariha> getTejariha() {
        return formActivity.getTejarihas();
    }

    @Override
    public void setTejariha(ArrayList<Tejariha> tejarihas) {
        formActivity.setTejarihas(tejarihas);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public ExaminerDuties getExaminerDuty() {
        return examinerDuty;
    }

    public interface Callback {
        void setOnPreClickListener(int position);

        void setTitle(String title, boolean showMenu);

        void setBaseInfo(ExaminerDuties examinerDuties);

        ExaminerDuties getExaminerDuty();

        ArrayList<NoeVagozariDictionary> getNoeVagozariDictionaries();

        ArrayList<QotrEnsheabDictionary> getQotrEnsheabDictionary();

        ArrayList<KarbariDictionary> getKarbariDictionary();

        ArrayList<TaxfifDictionary> getTaxfifDictionary();

        ArrayList<Tejariha> getTejarihas();

        void setTejarihas(ArrayList<Tejariha> tejarihas);

        ArrayList<Integer> getValues();

        void setValues(ArrayList<Integer> values);

        Arzeshdaraei getArzeshdaraei();
    }
}