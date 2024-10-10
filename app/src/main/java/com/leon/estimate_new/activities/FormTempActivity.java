package com.leon.estimate_new.activities;

import static com.leon.estimate_new.enums.BundleEnum.BILL_ID;
import static com.leon.estimate_new.enums.BundleEnum.EXAMINER_DUTY;
import static com.leon.estimate_new.enums.BundleEnum.NEW_ENSHEAB;
import static com.leon.estimate_new.enums.BundleEnum.TRACK_NUMBER;
import static com.leon.estimate_new.enums.FragmentEnum.Bill;
import static com.leon.estimate_new.enums.FragmentEnum.ShowDocument;
import static com.leon.estimate_new.fragments.dialog.ShowFragmentDialog.ShowFragmentDialogOnce;
import static com.leon.estimate_new.helpers.Constants.BASE_FRAGMENT;
import static com.leon.estimate_new.helpers.Constants.EDIT_MAP_FRAGMENT;
import static com.leon.estimate_new.helpers.Constants.MAP_DESCRIPTION_FRAGMENT;
import static com.leon.estimate_new.helpers.Constants.PERSONAL_FRAGMENT;
import static com.leon.estimate_new.helpers.Constants.SERVICES_FRAGMENT;
import static com.leon.estimate_new.helpers.Constants.TECHNICAL_INFO_FRAGMENT;
import static com.leon.estimate_new.helpers.MyApplication.getApplicationComponent;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.window.OnBackInvokedDispatcher;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.leon.estimate_new.R;
import com.leon.estimate_new.adapters.ViewPagerStateAdapter;
import com.leon.estimate_new.databinding.ActivityFormTempBinding;
import com.leon.estimate_new.fragments.dialog.EnterBillFragment;
import com.leon.estimate_new.fragments.dialog.ShowDocumentFragment;
import com.leon.estimate_new.fragments.forms.BaseInfoFragment;
import com.leon.estimate_new.fragments.forms.BaseInfoViewModel;
import com.leon.estimate_new.fragments.forms.EditMapFragment;
import com.leon.estimate_new.fragments.forms.MapDescriptionFragment;
import com.leon.estimate_new.fragments.forms.PersonalFragment;
import com.leon.estimate_new.fragments.forms.PersonalViewModel;
import com.leon.estimate_new.fragments.forms.ServicesFragment;
import com.leon.estimate_new.fragments.forms.TechnicalInfoFragment;
import com.leon.estimate_new.fragments.forms.TechnicalInfoViewModel;
import com.leon.estimate_new.tables.Arzeshdaraei;
import com.leon.estimate_new.tables.CalculationUserInput;
import com.leon.estimate_new.tables.ExaminerDuties;
import com.leon.estimate_new.tables.KarbariDictionary;
import com.leon.estimate_new.tables.NoeVagozariDictionary;
import com.leon.estimate_new.tables.QotrEnsheabDictionary;
import com.leon.estimate_new.tables.RequestDictionary;
import com.leon.estimate_new.tables.TaxfifDictionary;
import com.leon.estimate_new.tables.Tejariha;
import com.leon.estimate_new.utils.estimating.GetDBDataTemp;
import com.leon.estimate_new.utils.mapper.CustomMapper;

import org.jetbrains.annotations.NotNull;
import org.osmdroid.util.GeoPoint;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class FormTempActivity extends AppCompatActivity implements PersonalFragment.Callback,
        ServicesFragment.Callback, BaseInfoFragment.Callback, TechnicalInfoFragment.Callback,
        MapDescriptionFragment.Callback, EditMapFragment.Callback, GetDBDataTemp.ICallback {
    private ActivityFormTempBinding binding;
    private final HashMap<Integer, Fragment> fragmentCache = new HashMap<>();
    private final CalculationUserInput calculationUserInput = new CalculationUserInput();
    private final ArrayList<Integer> values = new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0));
    private final ArrayList<RequestDictionary> requestDictionaries = new ArrayList<>();
    private final ArrayList<NoeVagozariDictionary> noeVagozariDictionaries = new ArrayList<>();
    private final ArrayList<QotrEnsheabDictionary> qotrEnsheabDictionaries = new ArrayList<>();
    private final ArrayList<KarbariDictionary> karbariDictionaries = new ArrayList<>();
    private final ArrayList<TaxfifDictionary> taxfifDictionaries = new ArrayList<>();
    private final ArrayList<Tejariha> tejarihas = new ArrayList<>();

    private final HashMap<Integer, Integer> fragmentPosition = new HashMap<>();
    private final HashMap<Integer, String> fragmentTitle = new HashMap<>();


    private Arzeshdaraei arzeshdaraei;
    private ExaminerDuties examinerDuty;

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);

        binding = ActivityFormTempBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

//        setActivityComponent(this);
        initialize();
        addOnBackPressed();
    }

    private void initialize() {
        if (getIntent().getExtras() != null) {
            String json = getIntent().getExtras().getString(EXAMINER_DUTY.getValue());
            examinerDuty = new Gson().fromJson(json, ExaminerDuties.class);
        }
        fragmentPosition.put(PERSONAL_FRAGMENT, 0);
        fragmentPosition.put(SERVICES_FRAGMENT, 1);
        fragmentPosition.put(BASE_FRAGMENT, 2);
        fragmentPosition.put(TECHNICAL_INFO_FRAGMENT, 3);
        fragmentPosition.put(MAP_DESCRIPTION_FRAGMENT, 4);
        fragmentPosition.put(EDIT_MAP_FRAGMENT, 5);

        fragmentTitle.put(PERSONAL_FRAGMENT, getString(R.string.app_name).concat(" / ").concat("صفحه نخست"));
        fragmentTitle.put(SERVICES_FRAGMENT, getString(R.string.app_name).concat(" / ").concat("صفحه دوم"));
        fragmentTitle.put(BASE_FRAGMENT, getString(R.string.app_name).concat(" / ").concat("صفحه سوم"));
        fragmentTitle.put(TECHNICAL_INFO_FRAGMENT, getString(R.string.app_name).concat(" / ").concat("صفحه چهارم"));
        fragmentTitle.put(MAP_DESCRIPTION_FRAGMENT, getString(R.string.app_name).concat(" / ").concat("صفحه پنجم"));
        fragmentTitle.put(EDIT_MAP_FRAGMENT, getString(R.string.app_name).concat(" / ").concat("صفحه ششم"));


        if (getSupportActionBar() != null)
            getSupportActionBar().setTitle(fragmentTitle.get(PERSONAL_FRAGMENT));

        new GetDBDataTemp(this, examinerDuty.zoneId, examinerDuty.trackNumber, this, this).execute(this);
    }

    @Override
    public void setData(Arzeshdaraei arzeshdaraei,
                        ArrayList<NoeVagozariDictionary> noeVagozariDictionaries,
                        ArrayList<QotrEnsheabDictionary> qotrEnsheabDictionaries,
                        ArrayList<KarbariDictionary> karbariDictionaries,
                        ArrayList<TaxfifDictionary> taxfifDictionaries,
                        ArrayList<Tejariha> tejariha) {
        calculationUserInput.updateConstField(examinerDuty);
        requestDictionaries.addAll(Arrays.asList(new GsonBuilder().create()
                .fromJson(examinerDuty.requestDictionaryString, RequestDictionary[].class)));
        this.noeVagozariDictionaries.addAll(noeVagozariDictionaries);
        this.qotrEnsheabDictionaries.addAll(qotrEnsheabDictionaries);
        this.karbariDictionaries.addAll(karbariDictionaries);
        this.taxfifDictionaries.addAll(taxfifDictionaries);
        this.tejarihas.addAll(tejariha);
        this.arzeshdaraei = arzeshdaraei;

        ViewPagerStateAdapter adapter = new ViewPagerStateAdapter(this);
        binding.viewPager.setAdapter(adapter);
        binding.viewPager.setUserInputEnabled(false);
        binding.viewPager.setOffscreenPageLimit(6);
    }

    private void addOnBackPressed() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            getOnBackInvokedDispatcher().registerOnBackInvokedCallback(
                    OnBackInvokedDispatcher.PRIORITY_DEFAULT, () -> {

                    });
        } else {
            getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
                @Override
                public void handleOnBackPressed() {
                }
            });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        getMenuInflater().inflate(R.menu.document_menu, menu);
        if (!examinerDuty.isNewEnsheab) {
            menu.getItem(1).setVisible(false);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NotNull MenuItem item) {
        if (item.getItemId() == R.id.menu_document) {
            ShowFragmentDialogOnce(this, ShowDocument.getValue(),
                    ShowDocumentFragment.newInstance(examinerDuty.isNewEnsheab ?
                                    examinerDuty.trackNumber : examinerDuty.billId, false,
                            examinerDuty.isNewEnsheab, examinerDuty.trackNumber));
        } else if (item.getItemId() == R.id.menu_neighbour_document) {
            ShowFragmentDialogOnce(this, ShowDocument.getValue(),
                    ShowDocumentFragment.newInstance(examinerDuty.neighbourBillId, true,
                            examinerDuty.isNewEnsheab, examinerDuty.trackNumber));
        } else if (item.getItemId() == R.id.menu_other_document) {
            ShowFragmentDialogOnce(this, Bill.getValue(), EnterBillFragment.newInstance());
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setOnPreClickListener(int position) {
        displayView(position);
    }

    @Override
    public void setTitle(String title, boolean showMenu) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
            getSupportActionBar().setDisplayHomeAsUpEnabled(showMenu);
            getSupportActionBar().setDisplayShowHomeEnabled(false);
            getSupportActionBar().setIcon(R.mipmap.ic_launcher);
        }
    }

    @Override
    public ExaminerDuties getExaminerDuty() {
        return examinerDuty;
    }

    @Override
    public void setPersonalInfo(PersonalViewModel personalViewModel) {
        CustomMapper.INSTANCE.updateExaminerDutyPersonalViewModel(personalViewModel, examinerDuty);
        CustomMapper.INSTANCE.updateToCalculationUserInputFromPersonalViewModel(personalViewModel, calculationUserInput);

        prepareToSend();
        displayView(SERVICES_FRAGMENT);
    }

    @Override
    public void setServices(ArrayList<RequestDictionary> requestDictionaries) {
        examinerDuty.requestDictionary = new ArrayList<>(requestDictionaries);
        examinerDuty.requestDictionaryString = new GsonBuilder().create().toJson(examinerDuty.requestDictionary);

        calculationUserInput.selectedServicesObject = new ArrayList<>(examinerDuty.requestDictionary);
        calculationUserInput.selectedServicesString = examinerDuty.requestDictionaryString;

        prepareToSend();
        displayView(BASE_FRAGMENT);
    }


    @Override
    public void setBaseInfo(BaseInfoViewModel baseInfoViewModel) {
        CustomMapper.INSTANCE.updateExaminerDutyBaseInfoViewModel(baseInfoViewModel, examinerDuty);
        CustomMapper.INSTANCE.updateCalculationUserInputBaseInfoViewModel(baseInfoViewModel, calculationUserInput);
        prepareToSend();
        displayView(TECHNICAL_INFO_FRAGMENT);
    }

    @Override
    public void setTechnicalForm(TechnicalInfoViewModel technicalInfoViewModel) {
        //TODO
        CustomMapper.INSTANCE.updateExaminerDutyTechnicalInfoViewModel(technicalInfoViewModel, examinerDuty);
        prepareToSend();
        displayView(MAP_DESCRIPTION_FRAGMENT);
    }

    @Override
    public void setMapDescription() {
        prepareToSend();
        displayView(EDIT_MAP_FRAGMENT);
    }

    //TODO
    private void displayView(int key) {
        Integer position = fragmentPosition.get(key);
        if (getSupportActionBar() != null)
            getSupportActionBar().setTitle(fragmentTitle.get(key));
        if (position != null)
            binding.viewPager.setCurrentItem(position);
    }

    @Override
    public void setEditMap() {
        final Intent intent = new Intent(getApplicationContext(), DocumentActivity.class);
        intent.putExtra(TRACK_NUMBER.getValue(), examinerDuty.trackNumber);
        intent.putExtra(BILL_ID.getValue(), examinerDuty.billId != null ?
                examinerDuty.billId : examinerDuty.neighbourBillId);
        intent.putExtra(NEW_ENSHEAB.getValue(), examinerDuty.isNewEnsheab);
        finish();
        startActivity(intent);
    }

    private void prepareToSend() {
        getApplicationComponent().MyDatabase().calculationUserInputDao().insert(calculationUserInput);
        getApplicationComponent().MyDatabase().examinerDutiesDao().insert(examinerDuty);
    }

    @Override
    public void setWaterLocation(GeoPoint point) {
        examinerDuty.x1 = calculationUserInput.x1 = point.getLongitude();
        examinerDuty.y1 = calculationUserInput.y1 = point.getLatitude();
    }

    @Override
    public void setCurrentLocation(GeoPoint point) {
        examinerDuty.x2 = calculationUserInput.x2 = point.getLongitude();
        examinerDuty.y2 = calculationUserInput.y2 = point.getLatitude();
    }

    @Override
    public CalculationUserInput getCalculationUserInput() {
        return calculationUserInput;
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
    public ArrayList<Tejariha> getTejarihas() {
        return tejarihas;
    }

    @Override
    public void setTejarihas(final ArrayList<Tejariha> tejarihas) {
        this.tejarihas.clear();
        this.tejarihas.addAll(tejarihas);
    }

    @Override
    public Arzeshdaraei getArzeshdaraei() {
        return arzeshdaraei;
    }

    @Override
    public void setArzeshdaraei(Arzeshdaraei arzeshdaraei) {
        this.arzeshdaraei = arzeshdaraei;
    }

    @Override
    public ArrayList<Integer> getValues() {
        return values;
    }

    @Override
    public void setValues(final ArrayList<Integer> values) {
        this.values.clear();
        this.values.addAll(values);
    }

    @Override
    public ArrayList<RequestDictionary> getServiceDictionaries() {
        return requestDictionaries;
    }

}