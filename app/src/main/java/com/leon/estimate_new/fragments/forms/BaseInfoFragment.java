package com.leon.estimate_new.fragments.forms;

import static com.leon.estimate_new.fragments.dialog.ShowFragmentDialog.ShowFragmentDialogOnce;
import static com.leon.estimate_new.helpers.Constants.SERVICES_FRAGMENT;
import static com.leon.estimate_new.utils.Validator.checkEmpty;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.PopupMenu;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.leon.estimate_new.R;
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
import com.leon.estimate_new.utils.mapper.CustomMapper;
import com.sardari.daterangepicker.customviews.DateRangeCalendarView;
import com.sardari.daterangepicker.dialog.DatePickerDialog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class BaseInfoFragment extends Fragment implements ValueFragment.Callback, View.OnLongClickListener,
        TejarihaSayerFragment.Callback, View.OnClickListener, View.OnFocusChangeListener {
    private FragmentBaseInfoBinding binding;
    private Callback formActivity;
    private BaseInfoViewModel baseInfoVM;

    private final HashMap<String, Integer> karbariMap = new HashMap<>();
    private final ArrayList<String> karbariTitles = new ArrayList<>();

    private final HashMap<String, Integer> noeVagozariMap = new HashMap<>();
    private final ArrayList<String> noeVagozariTitles = new ArrayList<>();

    private final HashMap<String, Integer> qotrEnsheabMap = new HashMap<>();
    private final ArrayList<String> qotrEnsheabTitles = new ArrayList<>();

    private final HashMap<String, Integer> taxfifMap = new HashMap<>();
    private final ArrayList<String> taxfifTitles = new ArrayList<>();

    private final ArrayList<String> qotrFazelabTitles = new ArrayList<>(Arrays.asList("0", "100", "125", "150", "200"));
    private final ArrayList<String> noeEnsheabTitles = new ArrayList<>(Arrays.asList("مشخص نشده", "انشعاب خاص", "انشعاب عادی"));

    public static BaseInfoFragment newInstance() {
        return new BaseInfoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentBaseInfoBinding.inflate(inflater, container, false);
        baseInfoVM = CustomMapper.INSTANCE.examinerDutyBaseInfoViewModel(formActivity.getExaminerDuty());
        baseInfoVM.fillEmpty();
        binding.setBaseInfoVM(baseInfoVM);
        initialize();
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        formActivity.setTitle(getString(R.string.app_name).concat(" / ").concat("صفحه سوم"), false);
    }

    private void initialize() {
//        binding.checkboxQaradadAmade.setChecked(baseInfoVM.isQaradad());
        baseInfoVM.setBlock(formActivity.getExaminerDuty().block != null ? formActivity.getExaminerDuty().block : "-");
        baseInfoVM.setArz(formActivity.getExaminerDuty().arz != null ? formActivity.getExaminerDuty().arz : "-");
        initializeArrays();
        setOnClickListener();
        setOnFocusChangeListener();
    }

    private void initializeArrays() {
        initializeKarbari();
        initializeNoeVagozari();
        initializeQotrEnsheab();
        initializeTaxfif();
    }

    private void initializeKarbari() {
        ArrayList<KarbariDictionary> dictionary = formActivity.getKarbariDictionary();
        for (int i = 0, dictionarySize = dictionary.size(); i < dictionarySize; i++) {
            KarbariDictionary karbariDictionary = dictionary.get(i);
            karbariTitles.add(karbariDictionary.title);
            karbariMap.put(karbariDictionary.title, karbariDictionary.id);
            if (baseInfoVM.getKarbariS() == null || baseInfoVM.getKarbariS().isEmpty()) {
                if (baseInfoVM.karbariId == karbariDictionary.id) {
                    baseInfoVM.setKarbariS(karbariDictionary.title);
                }
            }
        }
    }

    private void initializeNoeVagozari() {
        ArrayList<NoeVagozariDictionary> noeVagozariDictionaries = formActivity.getNoeVagozariDictionaries();
        for (int i = 0; i < noeVagozariDictionaries.size(); i++) {
            NoeVagozariDictionary noeVagozariDictionary = noeVagozariDictionaries.get(i);
            noeVagozariTitles.add(noeVagozariDictionary.title);
            noeVagozariMap.put(noeVagozariDictionary.title, noeVagozariDictionary.id);
            if (baseInfoVM.getNoeVagozariS() == null || baseInfoVM.getNoeVagozariS().isEmpty()) {
                if (baseInfoVM.getNoeVagozariId() == noeVagozariDictionary.id)
                    baseInfoVM.setNoeVagozariS(noeVagozariDictionary.title);
            }
        }
    }

    private void initializeQotrEnsheab() {
        ArrayList<QotrEnsheabDictionary> qotrEnsheabDictionaries = formActivity.getQotrEnsheabDictionary();
        for (int i = 0; i < qotrEnsheabDictionaries.size(); i++) {
            QotrEnsheabDictionary qotrEnsheabDictionary = qotrEnsheabDictionaries.get(i);
            qotrEnsheabTitles.add(qotrEnsheabDictionary.title);
            qotrEnsheabMap.put(qotrEnsheabDictionary.title, qotrEnsheabDictionary.id);
            if (baseInfoVM.getQotrEnsheabS() == null || baseInfoVM.getQotrEnsheabS().isEmpty()) {
                if (baseInfoVM.getQotrEnsheabId() == qotrEnsheabDictionary.id)
                    baseInfoVM.setQotrEnsheabS(qotrEnsheabDictionary.title);
            }
        }

    }

    private void initializeTaxfif() {
        ArrayList<TaxfifDictionary> dictionary = formActivity.getTaxfifDictionary();
        for (int i = 0, dictionarySize = dictionary.size(); i < dictionarySize; i++) {
            TaxfifDictionary taxfifDictionary = dictionary.get(i);
            taxfifTitles.add(taxfifDictionary.title);
            taxfifMap.put(taxfifDictionary.title, taxfifDictionary.id);
            if (baseInfoVM.getTaxfifS() == null || baseInfoVM.getTaxfifS().isEmpty()) {
                if (taxfifDictionary.id == baseInfoVM.getTaxfifId()) {
                    baseInfoVM.setTaxfifS(taxfifDictionary.title);
                }
            }
        }
    }

    private void setOnFocusChangeListener() {
        binding.editTextTedadSaier.setOnFocusChangeListener(this);
        binding.editTextTedadTejari.setOnFocusChangeListener(this);
    }

    private void setOnClickListener() {
        binding.textViewKarbari.setOnClickListener(this);
        binding.textViewNoeVagozari.setOnClickListener(this);
        binding.textViewQotrEnsheab.setOnClickListener(this);
        binding.textViewQotrFazelab.setOnClickListener(this);
        binding.textViewNoeEnsheab.setOnClickListener(this);
        binding.textViewNoeTakhfif.setOnClickListener(this);
        binding.editTextSodurDate.setOnClickListener(this);

        binding.buttonSubmit.setOnClickListener(this);
        binding.buttonPre.setOnClickListener(this);

        binding.layoutTedadTejari.setOnLongClickListener(this);
        binding.layoutTedadSaier.setOnLongClickListener(this);
        binding.editTextTedadTejari.setOnLongClickListener(this);
        binding.editTextTedadSaier.setOnLongClickListener(this);
        binding.layoutArzeshMelk.setOnClickListener(this);
        binding.editTextArzeshMelk.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.text_view_karbari) {
            showMenu(binding.textViewKarbari, karbariTitles);
        } else if (id == R.id.text_view_noe_vagozari) {
            showMenu(binding.textViewNoeVagozari, noeVagozariTitles);
        } else if (id == R.id.text_view_qotr_ensheab) {
            showMenu(binding.textViewQotrEnsheab, qotrEnsheabTitles);
        } else if (id == R.id.text_view_qotr_fazelab) {
            showMenu(binding.textViewQotrFazelab, qotrFazelabTitles);
        } else if (id == R.id.text_view_noe_ensheab) {
            showMenu(binding.textViewNoeEnsheab, noeEnsheabTitles);
        } else if (id == R.id.text_view_noe_takhfif) {
            showMenu(binding.textViewNoeTakhfif, taxfifTitles);
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
        }
        if (id == R.id.layout_arzesh_melk || id == R.id.edit_text_arzesh_melk) {
            if (formActivity.getArzeshdaraei() != null && !formActivity.getArzeshdaraei().blocks.isEmpty()
                    && !formActivity.getArzeshdaraei().formulas.isEmpty() && !formActivity.getArzeshdaraei().zaribs.isEmpty()) {
                ShowFragmentDialogOnce(requireContext(), "VALUE_FRAGMENT", ValueFragment.newInstance(this));
            } else {
                new GetArzeshdaraei(requireContext(), getExaminerDuty().zoneId, this).execute(requireActivity());
            }
        } else if (id == R.id.button_pre) {
            formActivity.setOnPreClickListener(SERVICES_FRAGMENT);
        } else if (id == R.id.button_submit) {
            if (checkForm())
                formActivity.setBaseInfo(prepareOutput());
        }
    }


    private void showMenu(MaterialAutoCompleteTextView textView, ArrayList<String> titles) {
        final PopupMenu popup = new PopupMenu(requireActivity(), textView, Gravity.TOP);
        for (int i = 0; i < titles.size(); i++) {
            MenuItem item = popup.getMenu().add(titles.get(i));
            if (item.getIcon() != null) {
                Drawable icon = item.getIcon();
                int iconMarginPx = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                        R.dimen.small_dp, getResources().getDisplayMetrics());
                InsetDrawable insetDrawable = new InsetDrawable(icon, iconMarginPx, 0, iconMarginPx, 0);
                item.setIcon(insetDrawable);
            }
        }
        popup.setOnMenuItemClickListener(menuItem -> {
            textView.setText(menuItem.getTitle());
            return true;
        });
        popup.show();
    }

    @Override
    public boolean onLongClick(View view) {
        int id = view.getId();
        if (id == R.id.layout_tedad_tejari || id == R.id.layout_tedad_saier ||
                id == R.id.edit_text_tedad_saier || id == R.id.edit_text_tedad_tejari) {
            if (checkTejariha())
                showTejariha();
        }
        return false;
    }

    @Override
    public void onFocusChange(View view, boolean b) {
        if (!b) {
            if (checkTejariha())
                showTejariha();
        }
    }

    private boolean checkTejariha() {
        int saier, tejari;
        saier = !baseInfoVM.getTedadSaierNew().isEmpty() ?
                Integer.parseInt(baseInfoVM.getTedadSaierNew()) : 0;
        tejari = !baseInfoVM.getTedadTejariNew().isEmpty() ?
                Integer.parseInt(baseInfoVM.getTedadTejariNew()) : 0;
        return saier > 0 || tejari > 0;
    }

    private void showTejariha() {
        ShowFragmentDialogOnce(requireContext(), "TEJARI_SAYER_FRAGMENT",
                TejarihaSayerFragment.newInstance(this));
    }

    private boolean checkAian() {
        if (Integer.parseInt(baseInfoVM.getAianKolNew()) <
                Integer.parseInt(baseInfoVM.getAianMaskooniNew()) +
                        Integer.parseInt(baseInfoVM.getAianNonMaskooniNew())) {
            binding.editTextAianKol.setError("اعیان کل از مجموع اعیان مسکونی و تجاری کوچک تر است");
            binding.editTextAianKol.requestFocus();
            return false;
        }
        return true;
    }

    private boolean checkForm() {
        return checkEmpty(binding.editTextSifoon100, requireContext())
                && checkEmpty(binding.editTextSifoon125, requireContext())
                && checkEmpty(binding.editTextSifoon150, requireContext())
                && checkEmpty(binding.editTextSifoon200, requireContext())
                && checkEmpty(binding.editTextArseKol, requireContext())
                && checkEmpty(binding.editTextAianKol, requireContext())
                && checkEmpty(binding.editTextAianMaskooni, requireContext())
                && checkEmpty(binding.editTextAianNonMaskooni, requireContext())
                && checkEmpty(binding.editTextTedadMaskooni, requireContext())
                && checkEmpty(binding.editTextTedadTejari, requireContext())
                && checkEmpty(binding.editTextTedadSaier, requireContext())
                && checkEmpty(binding.editTextZarfiat, requireContext())
                && checkEmpty(binding.editTextArzeshMelk, requireContext())
                && checkEmpty(binding.textViewKarbari, requireContext())
                && checkEmpty(binding.textViewNoeVagozari, requireContext())
                && checkEmpty(binding.textViewQotrEnsheab, requireContext())
                && checkEmpty(binding.textViewQotrFazelab, requireContext())
                && checkEmpty(binding.textViewNoeEnsheab, requireContext())
                && checkEmpty(binding.editTextTedadTaxfif, requireContext())
                && checkAian();
    }

    private BaseInfoViewModel prepareOutput() {
        Integer karbari = karbariMap.get(baseInfoVM.getKarbariS());
        if (karbari != null)
            baseInfoVM.setKarbariId(karbari);

        Integer noeVagozari = noeVagozariMap.get(baseInfoVM.getNoeVagozariS());
        if (noeVagozari != null)
            baseInfoVM.setKarbariId(noeVagozari);

        Integer qotrEnsheab = qotrEnsheabMap.get(baseInfoVM.getQotrEnsheabS());
        if (qotrEnsheab != null)
            baseInfoVM.setQotrEnsheabId(qotrEnsheab);

        Integer taxfif = taxfifMap.get(baseInfoVM.getTaxfifS());
        if (taxfif != null)
            baseInfoVM.setTaxfifId(taxfif);

        return baseInfoVM;
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
        return formActivity.getArzeshdaraei();
    }

    public void setArzeshDaraei(Arzeshdaraei arzeshdaraei) {
        formActivity.setArzeshdaraei(arzeshdaraei);
    }

    @Override
    public void setValue(ArrayList<Integer> values, int value, String block, String arz) {
        baseInfoVM.setArzeshMelk(String.valueOf(value));
        baseInfoVM.setArz(arz);
        baseInfoVM.setBlock(block);
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
        return formActivity.getExaminerDuty();
    }

    public interface Callback {
        void setOnPreClickListener(int position);

        void setTitle(String title, boolean showMenu);

        void setBaseInfo(BaseInfoViewModel baseInfoViewModel);

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

        void setArzeshdaraei(Arzeshdaraei arzeshdaraei);
    }
}