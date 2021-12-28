package com.leon.estimate_new.fragments.forms;

import static com.leon.estimate_new.helpers.Constants.SERVICES_FRAGMENT;
import static com.leon.estimate_new.helpers.Constants.applicationComponent;
import static com.leon.estimate_new.helpers.MyApplication.getApplicationComponent;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.leon.estimate_new.R;
import com.leon.estimate_new.databinding.FragmentBaseInfoBinding;
import com.leon.estimate_new.fragments.dialog.ShowFragmentDialog;
import com.leon.estimate_new.fragments.dialog.ValueFragment;
import com.leon.estimate_new.tables.Arzeshdaraei;
import com.leon.estimate_new.tables.Block;
import com.leon.estimate_new.tables.CalculationUserInput;
import com.leon.estimate_new.tables.ExaminerDuties;
import com.leon.estimate_new.tables.Formula;
import com.leon.estimate_new.tables.KarbariDictionary;
import com.leon.estimate_new.tables.NoeVagozariDictionary;
import com.leon.estimate_new.tables.QotrEnsheabDictionary;
import com.leon.estimate_new.tables.TaxfifDictionary;
import com.leon.estimate_new.tables.Tejariha;
import com.leon.estimate_new.tables.Zarib;
import com.leon.estimate_new.utils.estimating.GetArzeshdaraei;
import com.sardari.daterangepicker.customviews.DateRangeCalendarView;
import com.sardari.daterangepicker.dialog.DatePickerDialog;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class BaseInfoFragment extends Fragment implements ValueFragment.Callback {
    private final ArrayList<NoeVagozariDictionary> noeVagozariDictionaries = new ArrayList<>();
    private final ArrayList<QotrEnsheabDictionary> qotrEnsheabDictionaries = new ArrayList<>();
    private final ArrayList<KarbariDictionary> karbariDictionaries = new ArrayList<>();
    private final ArrayList<TaxfifDictionary> taxfifDictionaries = new ArrayList<>();
    private final ArrayList<Integer> value = new ArrayList<>();
    private final ArrayList<Tejariha> others = new ArrayList<>();
    private FragmentBaseInfoBinding binding;
    private ExaminerDuties examinerDuties;
    private Arzeshdaraei arzeshdaraei;
    private Callback formActivity;
    private int saier, tejari;

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
        setDBData();
        initializeSpinner();
        initializeField();
        setOnButtonsClickListener();
        setOnEditTextSodurDateClickListener();
        setOnEditTextTedadTejariTextChangeListener();
        setOnEditTextTedadSaierTextChangeListener();
        setOnTextViewArzeshDaraeiClickListener();
        setOnImageViewPlusClickListener();
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

    private void setOnEditTextTedadSaierTextChangeListener() {
        binding.editTextTedadSaier.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0)
                    saier = Integer.parseInt(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (tejari < 1) {
                    binding.linearLayoutTejari.setVisibility(View.GONE);
                }
                if (saier > 0) {
                    binding.linearLayoutTejari.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private void setOnEditTextTedadTejariTextChangeListener() {
        binding.editTextTedadTejari.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0)
                    tejari = Integer.parseInt(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (saier < 1) {
                    binding.linearLayoutTejari.setVisibility(View.GONE);
                }
                if (tejari > 0) {
                    binding.linearLayoutTejari.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private void setDBData() {
        others.clear();
        others.addAll(getApplicationComponent().MyDatabase().tejarihaDao()
                .getTejarihaByTrackNumber(examinerDuties.trackNumber));
        final ArrayList<Formula> formulas = new ArrayList<>(getApplicationComponent().MyDatabase().formulaDao()
                .getFormulaByZoneId(Integer.parseInt(examinerDuties.zoneId)));
        final ArrayList<Block> blocks = new ArrayList<>(getApplicationComponent().MyDatabase().blockDao()
                .getBlockByZoneId(Integer.parseInt(examinerDuties.zoneId)));
        final ArrayList<Zarib> zaribs = new ArrayList<>(getApplicationComponent().MyDatabase()
                .zaribDao().getZaribByZoneId(Integer.parseInt(examinerDuties.zoneId)));
        if (formulas.size() > 0 && blocks.size() > 0 && zaribs.size() > 0) {
            arzeshdaraei = new Arzeshdaraei(blocks, formulas, zaribs);
        }
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
                        ValueFragment.newInstance());
            } else {
                new GetArzeshdaraei(requireContext(), this, examinerDuties.zoneId).execute(requireActivity());
            }
        });
    }

    private void setOnImageViewPlusClickListener() {
//        binding.imageViewPlus.setOnClickListener(v -> {
//            if (checkIsNoEmpty(binding.editTextVahed) && checkIsNoEmpty(binding.editTextNoeShoql)
//                    && checkIsNoEmpty(binding.editTextVahedMohasebe)
//                    && checkIsNoEmpty(binding.editTextA2)) {
//                if (others.size() == 8) {
//                    new CustomDialogModel(Yellow, requireContext(),
//                            getString(R.string.tejari_over_flow),
//                            getString(R.string.dear_user),
//                            getString(R.string.tejari),
//                            getString(R.string.accepted));
//                    return;
//                }
//                String karbari = karbariDictionaries.get(
//                        binding.spinner5.getSelectedItemPosition()).title;
//                String noeShoql = binding.editTextNoeShoql.getText().toString();
//                int tedadVahed = Integer.parseInt(binding.editTextVahed.getText().toString());
//                String vahedMohasebe = binding.editTextVahedMohasebe.getText().toString();
//                String a = binding.editTextA2.getText().toString();
//                Tejariha tejariha = new Tejariha(karbari, noeShoql, tedadVahed, vahedMohasebe, a,
//                        examinerDuties.getTrackNumber());
//                others.add(tejariha);
//                othersAdapter.notifyDataSetChanged();
//                binding.editTextA2.setText("");
//                binding.editTextNoeShoql.setText("");
//                binding.editTextVahed.setText("");
//                binding.editTextVahedMohasebe.setText("");
//            }
//        });
    }

    private boolean checkIsNoEmpty(EditText editText) {
        View focusView;
        if (editText.getText().toString().length() < 1) {
            editText.setError(getString(R.string.error_empty));
            focusView = editText;
            focusView.requestFocus();
            return false;
        }
        return true;
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
        karbariDictionaries.clear();
        karbariDictionaries.addAll(getApplicationComponent().MyDatabase().karbariDictionaryDao().getKarbariDictionary());
        final List<String> arrayListSpinner = new ArrayList<>();
        int selected = 0, counter = 0;
        for (KarbariDictionary karbariDictionary : karbariDictionaries) {
            arrayListSpinner.add(karbariDictionary.title);
            if (karbariDictionary.id == examinerDuties.karbariId) {
                selected = counter;
            }
            counter = counter + 1;
        }
        binding.spinner1.setAdapter(createArrayAdapter(arrayListSpinner));
        binding.spinner5.setAdapter(createArrayAdapter(arrayListSpinner));
        binding.spinner1.setSelection(selected);
    }

    private void initializeTaxfifSpinner() {
        taxfifDictionaries.clear();
        taxfifDictionaries.addAll(applicationComponent.MyDatabase().taxfifDictionaryDao().getTaxfifDictionaries());
        final List<String> arrayListSpinner = new ArrayList<>();
        int selected = 0, counter = 0;
        for (TaxfifDictionary taxfifDictionary : taxfifDictionaries) {
            arrayListSpinner.add(taxfifDictionary.title);
            if (taxfifDictionary.id == examinerDuties.taxfifId) {
                selected = counter;
            }
            counter = counter + 1;
        }
        binding.spinner4.setAdapter(createArrayAdapter(arrayListSpinner));
        binding.spinner4.setSelection(selected);
    }

    private void initializeNoeVagozariSpinner() {
        noeVagozariDictionaries.clear();
        noeVagozariDictionaries.addAll(applicationComponent.MyDatabase().noeVagozariDictionaryDao().getNoeVagozariDictionaries());
        final List<String> arrayListSpinner = new ArrayList<>();
        for (NoeVagozariDictionary noeVagozariDictionary : noeVagozariDictionaries) {
            arrayListSpinner.add(noeVagozariDictionary.title);
        }
        binding.spinner2.setAdapter(createArrayAdapter(arrayListSpinner));
        binding.spinner2.setSelection(examinerDuties.noeVagozariId);
    }

    private void initializeQotrEnsheabSpinner() {
        qotrEnsheabDictionaries.clear();
        qotrEnsheabDictionaries.addAll(applicationComponent.MyDatabase().qotrEnsheabDictionaryDao().getQotrEnsheabDictionaries());
        final List<String> arrayListSpinner = new ArrayList<>();
        int counter = 0, selected = 0;
        for (QotrEnsheabDictionary qotrEnsheabDictionary : qotrEnsheabDictionaries) {
            arrayListSpinner.add(qotrEnsheabDictionary.title);
            if (examinerDuties.qotrEnsheabId == qotrEnsheabDictionary.id) {
                selected = counter;
            }
            counter = counter + 1;
        }
        binding.spinner3.setAdapter(createArrayAdapter(arrayListSpinner));
        binding.spinner3.setSelection(selected);
    }

    private ArrayAdapter<String> createArrayAdapter(List<String> arrayListSpinner) {
        return new ArrayAdapter<String>(requireContext(),
                R.layout.item_dropdown_popup, arrayListSpinner) {
            @NotNull
            @Override
            public View getView(int position, View convertView, @NotNull ViewGroup parent) {
                final View view = super.getView(position, convertView, parent);
                final CheckedTextView textView = view.findViewById(android.R.id.text1);
                textView.setChecked(true);
                textView.setTextSize(getResources().getDimension(R.dimen.text_size_small));
                textView.setTextColor(ContextCompat.getColor(requireContext(), android.R.color.black));
                return view;
            }
        };
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
    public void setValue(int value) {
        binding.textViewArzeshMelk.setText(String.valueOf(value));
    }

    @Override
    public Arzeshdaraei getArzeshdaraei() {
        return arzeshdaraei;
    }

    @Override
    public void setValue(ArrayList<Integer> value) {
        this.value.clear();
        this.value.addAll(value);
    }

    @Override
    public ArrayList<Integer> getValue() {
        return value;
    }

    public interface Callback {
        void setOnPreClickListener(int position);

        void setTitle(String title, boolean showMenu);

        void setBaseInfo(CalculationUserInput calculationUserInput);

        ExaminerDuties getExaminerDuty();
    }
}