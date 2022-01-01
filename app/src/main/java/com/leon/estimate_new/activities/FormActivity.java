package com.leon.estimate_new.activities;

import static com.leon.estimate_new.helpers.Constants.BASE_FRAGMENT;
import static com.leon.estimate_new.helpers.Constants.PERSONAL_FRAGMENT;
import static com.leon.estimate_new.helpers.Constants.SERVICES_FRAGMENT;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.leon.estimate_new.R;
import com.leon.estimate_new.databinding.ActivityFormBinding;
import com.leon.estimate_new.enums.BundleEnum;
import com.leon.estimate_new.fragments.forms.BaseInfoFragment;
import com.leon.estimate_new.fragments.forms.PersonalFragment;
import com.leon.estimate_new.fragments.forms.ServicesFragment;
import com.leon.estimate_new.tables.Arzeshdaraei;
import com.leon.estimate_new.tables.CalculationUserInput;
import com.leon.estimate_new.tables.ExaminerDuties;
import com.leon.estimate_new.tables.KarbariDictionary;
import com.leon.estimate_new.tables.NoeVagozariDictionary;
import com.leon.estimate_new.tables.QotrEnsheabDictionary;
import com.leon.estimate_new.tables.RequestDictionary;
import com.leon.estimate_new.tables.TaxfifDictionary;
import com.leon.estimate_new.tables.Tejariha;
import com.leon.estimate_new.utils.estimating.GetDBData;

import java.util.ArrayList;
import java.util.Arrays;

public class FormActivity extends AppCompatActivity implements PersonalFragment.Callback,
        ServicesFragment.Callback, BaseInfoFragment.Callback {
    private ActivityFormBinding binding;
    private ExaminerDuties examinerDuties;
    private Arzeshdaraei arzeshdaraei;

    private final ArrayList<Integer> values = new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0));

    private final ArrayList<RequestDictionary> requestDictionaries = new ArrayList<>();
    private final CalculationUserInput calculationUserInput = new CalculationUserInput();

    private final ArrayList<NoeVagozariDictionary> noeVagozariDictionaries = new ArrayList<>();
    private final ArrayList<QotrEnsheabDictionary> qotrEnsheabDictionaries = new ArrayList<>();
    private final ArrayList<KarbariDictionary> karbariDictionaries = new ArrayList<>();
    private final ArrayList<TaxfifDictionary> taxfifDictionaries = new ArrayList<>();
    private final ArrayList<Tejariha> tejariha = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFormBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initialize();
    }

    private void initialize() {
        if (getIntent().getExtras() != null) {
            final String json = getIntent().getExtras().getString(BundleEnum.EXAMINER_DUTY.getValue());
            examinerDuties = new Gson().fromJson(json, ExaminerDuties.class);
            requestDictionaries.addAll(Arrays.asList(new GsonBuilder().create()
                    .fromJson(examinerDuties.requestDictionaryString, RequestDictionary[].class)));
        }
        new GetDBData(this, examinerDuties.zoneId, examinerDuties.trackNumber, this).execute(this);
        displayView(PERSONAL_FRAGMENT);
    }

    private void displayView(int position) {
        final Fragment fragment;
        switch (position) {
            case SERVICES_FRAGMENT:
                fragment = ServicesFragment.newInstance();
                break;
            case BASE_FRAGMENT:
                fragment = BaseInfoFragment.newInstance();
                break;
            case PERSONAL_FRAGMENT:
            default:
                fragment = PersonalFragment.newInstance();
                break;
        }
        final String tag = Integer.toString(position);
        if (getFragmentManager().findFragmentByTag(tag) != null && getFragmentManager().findFragmentByTag(tag).isVisible()) {
            return;
        }
        final FragmentManager fragmentManager = getSupportFragmentManager();
        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.animator.enter, R.animator.exit,
                R.animator.pop_enter, R.animator.pop_exit);
        fragmentTransaction.replace(binding.containerBody.getId(), fragment, tag);
        if (position != PERSONAL_FRAGMENT) {
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.commitAllowingStateLoss();
        getFragmentManager().executePendingTransactions();
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else super.onBackPressed();
    }

    @Override
    public void setOnPreClickListener(int position) {
        displayView(position);
    }

    @Override
    public void setTitle(String title, boolean showMenu) {
        if (getSupportActionBar() != null)
            getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(showMenu);
        // Set logo
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);
    }

    @Override
    public void setPersonalInfo(final CalculationUserInput calculationUserInputTemp) {
        examinerDuties.preparePersonal(calculationUserInputTemp);
        calculationUserInput.preparePersonal(calculationUserInputTemp, examinerDuties.zoneId);
        displayView(SERVICES_FRAGMENT);
    }

    @Override
    public void setServices(CalculationUserInput calculationUserInputTemp) {
        calculationUserInput.selectedServicesObject = new ArrayList<>(calculationUserInputTemp.selectedServicesObject);
        examinerDuties.requestDictionary = new ArrayList<>(calculationUserInputTemp.selectedServicesObject);
        calculationUserInput.selectedServicesString = new GsonBuilder().create().toJson(calculationUserInputTemp.selectedServicesObject);
        examinerDuties.requestDictionaryString = new GsonBuilder().create().toJson(calculationUserInputTemp.selectedServicesObject);
        displayView(BASE_FRAGMENT);
    }

    public void setData(Arzeshdaraei arzeshdaraei,
                        ArrayList<NoeVagozariDictionary> noeVagozariDictionaries,
                        ArrayList<QotrEnsheabDictionary> qotrEnsheabDictionaries,
                        ArrayList<KarbariDictionary> karbariDictionaries,
                        ArrayList<TaxfifDictionary> taxfifDictionaries,
                        ArrayList<Tejariha> tejariha) {
        this.noeVagozariDictionaries.addAll(noeVagozariDictionaries);
        this.qotrEnsheabDictionaries.addAll(qotrEnsheabDictionaries);
        this.karbariDictionaries.addAll(karbariDictionaries);
        this.taxfifDictionaries.addAll(taxfifDictionaries);
        this.tejariha.addAll(tejariha);
        this.arzeshdaraei = arzeshdaraei;
    }

    @Override
    public void setBaseInfo(CalculationUserInput calculationUserInput) {
        //TODO
    }

    @Override
    public ExaminerDuties getExaminerDuty() {
        return examinerDuties;
    }

    @Override
    public ArrayList<NoeVagozariDictionary> getNoeVagozariDictionaries() {
        return noeVagozariDictionaries;
    }

    @Override
    public ArrayList<QotrEnsheabDictionary> getQotrEnsheabDictionary() {
        return qotrEnsheabDictionaries;
    }

    @Override
    public ArrayList<KarbariDictionary> getKarbariDictionary() {
        return karbariDictionaries;
    }

    @Override
    public ArrayList<TaxfifDictionary> getTaxfifDictionary() {
        return taxfifDictionaries;
    }

    @Override
    public ArrayList<Tejariha> getTejariha() {
        return tejariha;
    }

    @Override
    public Arzeshdaraei getArzeshdaraei() {
        return arzeshdaraei;
    }

    @Override
    public void setValues(ArrayList<Integer> values) {
        this.values.clear();
        this.values.addAll(values);
    }

    @Override
    public ArrayList<Integer> getValues() {
        return values;
    }

    @Override
    public ArrayList<RequestDictionary> getServiceDictionaries() {
        return requestDictionaries;
    }
}