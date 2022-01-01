package com.leon.estimate_new.fragments.forms;

import static com.leon.estimate_new.helpers.Constants.SERVICES_FRAGMENT;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.leon.estimate_new.R;
import com.leon.estimate_new.adapters.SpinnerCustomAdapter;
import com.leon.estimate_new.databinding.FragmentBaseInfoBinding;
import com.leon.estimate_new.fragments.dialog.ShowFragmentDialog;
import com.leon.estimate_new.fragments.dialog.TejarihaSayerFragment;
import com.leon.estimate_new.fragments.dialog.ValueFragment;
import com.leon.estimate_new.tables.Arzeshdaraei;
import com.leon.estimate_new.tables.CalculationUserInput;
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

public class BaseInfoFragment extends Fragment implements ValueFragment.Callback, TejarihaSayerFragment.Callback {
    //    private final ArrayList<Tejariha> tejariha = new ArrayList<>();
    private FragmentBaseInfoBinding binding;
    private ExaminerDuties examinerDuties;
    private Arzeshdaraei arzeshdaraei;
    private Callback formActivity;
    private int saier, tejari;
    private final View.OnClickListener onClickListener = v ->
            ShowFragmentDialog.ShowFragmentDialogOnce(requireContext(), "TEJARI_SAYER_FRAGMENT",
                    TejarihaSayerFragment.newInstance(this));
    private final TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            if (s.length() > 0) {
                if (s == binding.editTextTedadSaier.getEditableText())
                    saier = Integer.parseInt(s.toString());
                else if (s == binding.editTextTedadTejari.getEditableText())
                    tejari = Integer.parseInt(s.toString());
            } else {
                if (s == binding.editTextTedadSaier.getEditableText())
                    saier = 0;
                else if (s == binding.editTextTedadTejari.getEditableText())
                    tejari = 0;
            }
            binding.textViewTedadSaier.setEnabled(saier > 0 || tejari > 0);
            binding.textViewTedadTejari.setEnabled(saier > 0 || tejari > 0);
        }
    };

    public BaseInfoFragment() {
    }

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
        examinerDuties = formActivity.getExaminerDuty();
        arzeshdaraei = formActivity.getArzeshdaraei();
//        tejariha.addAll(formActivity.getTejariha());
        initializeSpinner();
        initializeField();
        setOnButtonsClickListener();
        setOnEditTextSodurDateClickListener();
        setOnTextViewArzeshDaraeiClickListener();
        setOnEditTextSayerTejariChangeListener();
        setOnTextViewSayerTejariClickListener();
    }

    private void setOnButtonsClickListener() {
        binding.buttonPre.setOnClickListener(v -> formActivity.setOnPreClickListener(SERVICES_FRAGMENT));
        binding.buttonSubmit.setOnClickListener(v -> {
        });
    }

    private void setOnEditTextSodurDateClickListener() {
        binding.editTextSodurDate.setOnClickListener(v -> {
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
        });
    }

    private void setOnEditTextSayerTejariChangeListener() {
        binding.editTextTedadSaier.addTextChangedListener(textWatcher);
        binding.editTextTedadTejari.addTextChangedListener(textWatcher);
    }

    private void setOnTextViewSayerTejariClickListener() {
        binding.textViewTedadTejari.setOnClickListener(onClickListener);
        binding.textViewTedadSaier.setOnClickListener(onClickListener);
    }

    private void setOnTextViewArzeshDaraeiClickListener() {
        binding.textViewArzeshMelk.setOnClickListener(v -> {
            if (arzeshdaraei != null
                    && arzeshdaraei.blocks != null
                    && arzeshdaraei.blocks.size() > 0
                    && arzeshdaraei.formulas != null
                    && arzeshdaraei.formulas.size() > 0
                    && arzeshdaraei.zaribs != null
                    && arzeshdaraei.zaribs.size() > 0) {
                ShowFragmentDialog.ShowFragmentDialogOnce(requireContext(), "VALUE_FRAGMENT",
                        ValueFragment.newInstance(this));
            } else {
                new GetArzeshdaraei(requireContext(), this, examinerDuties.zoneId).execute(requireActivity());
            }
        });
    }

    private void initializeField() {
        binding.editTextSifoon100.setText(String.valueOf(examinerDuties.sifoon100));
        binding.editTextSifoon125.setText(String.valueOf(examinerDuties.sifoon125));
        binding.editTextSifoon150.setText(String.valueOf(examinerDuties.sifoon150));
        binding.editTextSifoon200.setText(String.valueOf(examinerDuties.sifoon150));
        binding.editTextArse.setText(String.valueOf(examinerDuties.arse));
        binding.editTextAianKol.setText(String.valueOf(examinerDuties.aianKol));
        binding.editTextAianMaskooni.setText(String.valueOf(examinerDuties.aianMaskooni));
        binding.editTextAianNonMaskooni.setText(String.valueOf(examinerDuties.aianNonMaskooni));
        binding.editTextTedadMaskooni.setText(String.valueOf(examinerDuties.tedadMaskooni));
        binding.editTextTedadTejari.setText(String.valueOf(examinerDuties.tedadTejari));
        binding.editTextTedadSaier.setText(String.valueOf(examinerDuties.tedadSaier));
        binding.textViewArzeshMelk.setText(String.valueOf(examinerDuties.arzeshMelk));
        binding.editTextTedadTakhfif.setText(String.valueOf(examinerDuties.tedadTaxfif));
        binding.editTextZarfiatQaradadi.setText(String.valueOf(examinerDuties.zarfiatQarardadi));
        binding.editTextPariNumber.setText(examinerDuties.parNumber);
        binding.editTextSodurDate.setText(examinerDuties.getExaminationDay());
        binding.editTextPelak.setText(String.valueOf(examinerDuties.pelak));

        binding.checkbox1.setChecked(examinerDuties.isEnsheabQeirDaem);
        binding.checkbox2.setChecked(examinerDuties.motaqazi);
        binding.checkbox3.setChecked(examinerDuties.estelamShahrdari);
        binding.checkbox4.setChecked(examinerDuties.parvane);
        binding.checkbox5.setChecked(examinerDuties.sanad);

    }

    private void initializeSpinner() {
        initializeNoeVagozariSpinner();
        initializeKarbariSpinner();
        initializeQotrEnsheabSpinner();
        initializeTaxfifSpinner();
    }

    private void initializeKarbariSpinner() {
        final ArrayList<String> arrayListSpinner = new ArrayList<>();
        int selected = 0, counter = 0;
        for (KarbariDictionary karbariDictionary : formActivity.getKarbariDictionary()) {
            arrayListSpinner.add(karbariDictionary.title);
            if (karbariDictionary.id == examinerDuties.karbariId) {
                selected = counter;
            }
            counter = counter + 1;
        }
        binding.spinner1.setAdapter(new SpinnerCustomAdapter(requireContext(), arrayListSpinner));
        binding.spinner1.setSelection(selected);
    }


    private void initializeNoeVagozariSpinner() {
        final ArrayList<String> arrayListSpinner = new ArrayList<>();
        for (NoeVagozariDictionary noeVagozariDictionary : formActivity.getNoeVagozariDictionaries()) {
            arrayListSpinner.add(noeVagozariDictionary.title);
        }
        binding.spinner2.setAdapter(new SpinnerCustomAdapter(requireContext(), arrayListSpinner));
        binding.spinner2.setSelection(examinerDuties.noeVagozariId);
    }

    private void initializeQotrEnsheabSpinner() {
        final ArrayList<String> arrayListSpinner = new ArrayList<>();
        int counter = 0, selected = 0;
        for (QotrEnsheabDictionary qotrEnsheabDictionary : formActivity.getQotrEnsheabDictionary()) {
            arrayListSpinner.add(qotrEnsheabDictionary.title);
            if (examinerDuties.qotrEnsheabId == qotrEnsheabDictionary.id) {
                selected = counter;
            }
            counter = counter + 1;
        }
        binding.spinner3.setAdapter(new SpinnerCustomAdapter(requireContext(), arrayListSpinner));
        binding.spinner3.setSelection(selected);
    }

    private void initializeTaxfifSpinner() {
        final ArrayList<String> arrayListSpinner = new ArrayList<>();
        int selected = 0, counter = 0;
        for (TaxfifDictionary taxfifDictionary : formActivity.getTaxfifDictionary()) {
            arrayListSpinner.add(taxfifDictionary.title);
            if (taxfifDictionary.id == examinerDuties.taxfifId) {
                selected = counter;
            }
            counter = counter + 1;
        }
        binding.spinner4.setAdapter(new SpinnerCustomAdapter(requireContext(), arrayListSpinner));
        binding.spinner4.setSelection(selected);
    }

    public void setArzeshdaraei(Arzeshdaraei arzeshdaraei) {
        this.arzeshdaraei = arzeshdaraei;
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

    @Override
    public void setValue(ArrayList<Integer> values, int value) {
        binding.textViewArzeshMelk.setText(String.valueOf(value));
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
        return formActivity.getTejariha();
    }

    @Override
    public ExaminerDuties getExaminerDuty() {
        return examinerDuties;
    }

    public interface Callback {
        void setOnPreClickListener(int position);

        void setTitle(String title, boolean showMenu);

        void setBaseInfo(CalculationUserInput calculationUserInput);

        ExaminerDuties getExaminerDuty();

        ArrayList<NoeVagozariDictionary> getNoeVagozariDictionaries();

        ArrayList<QotrEnsheabDictionary> getQotrEnsheabDictionary();

        ArrayList<KarbariDictionary> getKarbariDictionary();

        ArrayList<TaxfifDictionary> getTaxfifDictionary();

        ArrayList<Tejariha> getTejariha();

        Arzeshdaraei getArzeshdaraei();

        void setValues(ArrayList<Integer> values);

        ArrayList<Integer> getValues();
    }
}