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
import static com.leon.estimate_new.helpers.Constants.SECOND_FRAGMENT;
import static com.leon.estimate_new.helpers.Constants.SERVICES_FRAGMENT;
import static com.leon.estimate_new.helpers.MyApplication.getApplicationComponent;
import static com.leon.estimate_new.helpers.MyApplication.setActivityComponent;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.window.OnBackInvokedDispatcher;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.leon.estimate_new.R;
import com.leon.estimate_new.databinding.ActivityFormBinding;
import com.leon.estimate_new.fragments.dialog.EnterBillFragment;
import com.leon.estimate_new.fragments.dialog.ShowDocumentFragment;
import com.leon.estimate_new.fragments.forms.BaseInfoFragment;
import com.leon.estimate_new.fragments.forms.EditMapFragment;
import com.leon.estimate_new.fragments.forms.MapDescriptionFragment;
import com.leon.estimate_new.fragments.forms.PersonalFragment;
import com.leon.estimate_new.fragments.forms.PersonalViewModel;
import com.leon.estimate_new.fragments.forms.SecondFormFragment;
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
import com.leon.estimate_new.utils.mapper.CustomMapper;

import org.jetbrains.annotations.NotNull;
import org.osmdroid.util.GeoPoint;

import java.util.ArrayList;
import java.util.Arrays;

public class FormActivity extends AppCompatActivity implements PersonalFragment.Callback,
        ServicesFragment.Callback, BaseInfoFragment.Callback, SecondFormFragment.Callback,
        MapDescriptionFragment.Callback, EditMapFragment.Callback {
    private final CalculationUserInput calculationUserInput = new CalculationUserInput();
    private final ArrayList<Integer> values = new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0));
    private final ArrayList<RequestDictionary> requestDictionaries = new ArrayList<>();
    private final ArrayList<NoeVagozariDictionary> noeVagozariDictionaries = new ArrayList<>();
    private final ArrayList<QotrEnsheabDictionary> qotrEnsheabDictionaries = new ArrayList<>();
    private final ArrayList<KarbariDictionary> karbariDictionaries = new ArrayList<>();
    private final ArrayList<TaxfifDictionary> taxfifDictionaries = new ArrayList<>();
    private final ArrayList<Tejariha> tejarihas = new ArrayList<>();
    private ActivityFormBinding binding;
    private Arzeshdaraei arzeshdaraei;
    private ExaminerDuties examinerDuty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFormBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setActivityComponent(this);
        initialize();
        addOnBackPressed();
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

    private void initialize() {
        if (getIntent().getExtras() != null) {
            final String json = getIntent().getExtras().getString(EXAMINER_DUTY.getValue());
            examinerDuty = new Gson().fromJson(json, ExaminerDuties.class);
        }
        new GetDBData(this, examinerDuty.zoneId, examinerDuty.trackNumber, this).execute(this);
        displayView(PERSONAL_FRAGMENT/*MAP_DESCRIPTION_FRAGMENT*/);
    }

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
    }

    private void displayView(int position) {
        final String tag = Integer.toString(position);
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag(tag);
        if (fragment != null && fragment.isVisible()) {
            return;
        }
        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.animator.enter, R.animator.exit,
                R.animator.pop_enter, R.animator.pop_exit);
        fragmentTransaction.replace(binding.containerBody.getId(), getFragment(position), tag);
        if (position != 0) {
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.commitAllowingStateLoss();
//TODO        fragmentManager.executePendingTransactions();
    }

    private Fragment getFragment(int position) {
        return switch (position) {
            case SERVICES_FRAGMENT -> ServicesFragment.newInstance();
            case BASE_FRAGMENT -> BaseInfoFragment.newInstance();
            case SECOND_FRAGMENT -> SecondFormFragment.newInstance();
            case MAP_DESCRIPTION_FRAGMENT -> MapDescriptionFragment.newInstance();
            case EDIT_MAP_FRAGMENT -> EditMapFragment.newInstance();
            default -> PersonalFragment.newInstance();
        };
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
        CustomMapper.INSTANCE.updateExaminerDutyPersonalVM(personalViewModel, examinerDuty);
        CustomMapper.INSTANCE.updateToCalculationUserInputFromPersonVM(personalViewModel, calculationUserInput);

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
    public void setBaseInfo(ExaminerDuties examinerDutyTemp) {
        examinerDuty = examinerDutyTemp;
        calculationUserInput.updateBaseInfo(examinerDuty);
        prepareToSend();
        displayView(SECOND_FRAGMENT);
    }

    @Override
    public void setSecondForm(ExaminerDuties examinerDutyTemp) {
        examinerDuty = examinerDutyTemp;
        prepareToSend();
        displayView(MAP_DESCRIPTION_FRAGMENT);
    }

    @Override
    public void setMapDescription() {
        prepareToSend();
        displayView(EDIT_MAP_FRAGMENT);
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

//        final Point currentPoint = new Point(getLocationTracker(this).getLongitude(),
//                getLocationTracker(this).getLatitude(), SpatialReferences.getWgs84());
//        final String[] s = CoordinateFormatter.toUtm(currentPoint, LATITUDE_BAND_INDICATORS,
//                true).split(" ");
//        final Point currentPoint = new Point(getLocationTracker(this).getLongitude(),
//                getLocationTracker(this).getLatitude(), SpatialReferences.getWgs84());
//        final String[] s = CoordinateFormatter.toUtm(currentPoint, LATITUDE_BAND_INDICATORS,
//                true).split(" ");
//        examinerDuty.x2 = Double.parseDouble(s[1]);
//        examinerDuty.y2 = Double.parseDouble(s[2]);
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