package com.leon.estimate_new.utils.estimating;

import android.app.Activity;
import android.content.Context;

import com.leon.estimate_new.base_items.BaseAsync;
import com.leon.estimate_new.di.view_model.HttpClientWrapper;
import com.leon.estimate_new.infrastructure.IAbfaService;
import com.leon.estimate_new.tables.CalculationUserInputSend;
import com.leon.estimate_new.utils.SimpleMessage;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Retrofit;

public class UploadNavigated extends BaseAsync {
    public UploadNavigated(Context context, boolean showProgress, Object... view) {
        super(context, showProgress, view);
    }

    @Override
    public void postTask(Object o) {

    }

    @Override
    public void preTask(Object o) {

    }

    @Override
    public void backgroundTask(Activity activity) {
//        String token = sharedPreferenceManager.getStringData(SharedReferenceKeys.TOKEN.getValue());
//        Retrofit retrofit = NetworkHelper.getInstance(token);
//        final IAbfaService abfaService = retrofit.create(IAbfaService.class);
//
//        DaoExaminerDuties daoExaminerDuties = dataBase.daoExaminerDuties();
//        daoExaminerDuties.updateExamination(true, trackNumber);
//        GPSTracker gpsTracker = new GPSTracker(this);
//        calculationUserInput.accuracy = gpsTracker.getAccuracy();
//        calculationUserInput.y2 = gpsTracker.getLatitude();
//        calculationUserInput.x2 = gpsTracker.getLongitude();
//        calculationUserInput.resultId = resultDictionaries.get(binding.spinner1.getSelectedItemPosition()).getId();
//        calculationUserInput.ready = true;
//        DaoCalculationUserInput daoCalculationUserInput = dataBase.daoCalculationUserInput();
//        daoCalculationUserInput.deleteByTrackNumber(trackNumber);
//        daoCalculationUserInput.insertCalculationUserInput(calculationUserInput);
//
//        ArrayList<CalculationUserInputSend> calculationUserInputSends = new ArrayList<>();
//        calculationUserInputSends.add(new CalculationUserInputSend(calculationUserInput, examinerDuties));
//
//        Call<SimpleMessage> call = abfaService.setExaminationInfo(calculationUserInputSends);
//        HttpClientWrapper.callHttpAsync(call, ProgressType.SHOW.getValue(), context,
//                new SendCalculation(), new SendCalculationIncomplete(), new GetError());
    }

    @Override
    public void backgroundTask(Context context) {

    }
}
