package com.leon.estimate_new.utils.downloading;

import static com.leon.estimate_new.enums.DialogType.Green;
import static com.leon.estimate_new.enums.DialogType.Yellow;
import static com.leon.estimate_new.enums.ProgressType.NOT_SHOW;
import static com.leon.estimate_new.helpers.MyApplication.getApplicationComponent;
import static com.leon.estimate_new.helpers.MyApplication.getContext;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import com.google.gson.Gson;
import com.leon.estimate_new.BuildConfig;
import com.leon.estimate_new.R;
import com.leon.estimate_new.base_items.BaseAsync;
import com.leon.estimate_new.di.view_model.CustomDialogModel;
import com.leon.estimate_new.di.view_model.HttpClientWrapper;
import com.leon.estimate_new.infrastructure.IAbfaService;
import com.leon.estimate_new.infrastructure.ICallback;
import com.leon.estimate_new.infrastructure.ICallbackError;
import com.leon.estimate_new.infrastructure.ICallbackIncomplete;
import com.leon.estimate_new.tables.ExaminerDuties;
import com.leon.estimate_new.tables.Input;
import com.leon.estimate_new.utils.CustomErrorHandling;
import com.leon.estimate_new.utils.CustomToast;

import java.text.MessageFormat;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DownloadData extends BaseAsync {
    private final boolean toast;

    public DownloadData(Context context, boolean toast) {
        super(context, false, null);
        this.toast = toast;
    }

    @Override
    public void postTask(Object o) {
    }

    @Override
    public void preTask(Object o) {
    }

    @Override
    public void backgroundTask(Activity activity) {
        final Retrofit retrofit = getApplicationComponent().Retrofit();
        final IAbfaService getKardex = retrofit.create(IAbfaService.class);
        final Call<Input> call = getKardex.getMyWorks(BuildConfig.VERSION_CODE);
        HttpClientWrapper.callHttpAsync(call, NOT_SHOW.getValue(), activity, new Download(activity, toast),
                new DownloadIncomplete(activity), new GetError());
    }

    @Override
    public void backgroundTask(Context context) {

    }
}

class Download implements ICallback<Input> {
    private final Activity activity;
    private final boolean toast;

    public Download(Activity activity, boolean toast) {
        this.activity = activity;
        this.toast = toast;
    }

    @Override
    public void execute(Response<Input> response) {
        final Input input = response.body();
        if (input != null) {
            getApplicationComponent().MyDatabase().noeVagozariDictionaryDao().insertAll(input.noeVagozariDictionary);
            getApplicationComponent().MyDatabase().qotrEnsheabDictionaryDao().insertAll(input.qotrEnsheabDictionary);
            getApplicationComponent().MyDatabase().serviceDictionaryDao().insertAll(input.serviceDictionary);
            getApplicationComponent().MyDatabase().taxfifDictionaryDao().insertAll(input.taxfifDictionary);
            getApplicationComponent().MyDatabase().karbariDictionaryDao().insertAll(input.karbariDictionary);
            getApplicationComponent().MyDatabase().resultDictionaryDao().insertAll(input.resultDictionary);
            final String message = MessageFormat.format("تعداد {0} مسیر بارگیری شد.",
                    removeExaminerDuties(prepareExaminerDuties(input.examinerDuties)));
            if (toast)
                new CustomToast().success(message, Toast.LENGTH_LONG);
            else
                activity.runOnUiThread(() ->
                        new CustomDialogModel(Green, activity, message,
                                activity.getString(R.string.dear_user),
                                activity.getString(R.string.receive),
                                activity.getString(R.string.accepted)));
        } else {
            new CustomToast().warning(activity.getString(R.string.empty_download), Toast.LENGTH_LONG);
        }
    }

    private int removeExaminerDuties(List<ExaminerDuties> examinerDutiesList) {
        final List<ExaminerDuties> examinerDutiesListTemp = getApplicationComponent().MyDatabase()
                .examinerDutiesDao().getExaminerDuties();
        for (int i = 0; i < examinerDutiesList.size(); i++) {
            examinerDutiesList.get(i).trackNumber = examinerDutiesList.get(i).trackNumber
                    .replace(".0", "");
            examinerDutiesList.get(i).radif = examinerDutiesList.get(i).radif.replace(".0", "");
            final ExaminerDuties examinerDuties = examinerDutiesList.get(i);
            for (int j = 0; j < examinerDutiesListTemp.size(); j++) {
                final ExaminerDuties examinerDutiesTemp = examinerDutiesListTemp.get(j);
                if (examinerDuties.trackNumber.equals(examinerDutiesTemp.trackNumber)
                        || examinerDuties.zoneId == null || examinerDuties.zoneId.equals("0")) {
                    examinerDutiesList.remove(i);
                    j = examinerDutiesListTemp.size();
                    i--;
                }
            }
        }
        getApplicationComponent().MyDatabase().examinerDutiesDao().insertAll(examinerDutiesList);
        return examinerDutiesList.size();
    }

    private List<ExaminerDuties> prepareExaminerDuties(List<ExaminerDuties> examinerDutiesList) {
        final Gson gson = new Gson();
        for (int i = 0; i < examinerDutiesList.size(); i++) {
            examinerDutiesList.get(i).requestDictionaryString =
                    gson.toJson(examinerDutiesList.get(i).requestDictionary);
            if (examinerDutiesList.get(i).zoneId == null || examinerDutiesList.get(i).zoneId.equals("0")) {
                examinerDutiesList.remove(i);
                i--;
            }
        }
        return examinerDutiesList;
    }
}

class DownloadIncomplete implements ICallbackIncomplete<Input> {
    private final Context context;

    public DownloadIncomplete(Context context) {
        this.context = context;
    }

    @Override
    public void executeIncomplete(Response<Input> response) {
        String message;
        final CustomErrorHandling error = new CustomErrorHandling(context);
        if (response.code() == 400) {
            CustomErrorHandling.APIError apiError = error.parseError(response);
            message = apiError.message();
        } else {
            message = error.getErrorMessageDefault(response);
        }
        new CustomDialogModel(Yellow, context, message,
                context.getString(R.string.dear_user),
                context.getString(R.string.download),
                context.getString(R.string.accepted));
    }
}

class GetError implements ICallbackError {
    @Override
    public void executeError(Throwable t) {
        final CustomErrorHandling custom = new CustomErrorHandling(getContext());
        new CustomToast().error(custom.getErrorMessageTotal(t), Toast.LENGTH_LONG);
    }
}