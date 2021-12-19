package com.leon.estimate_new.utils.request;

import static com.leon.estimate_new.enums.SharedReferenceKeys.TOKEN;
import static com.leon.estimate_new.helpers.MyApplication.getApplicationComponent;
import static com.leon.estimate_new.helpers.MyApplication.getContext;

import android.app.Activity;
import android.content.Context;
import android.widget.Button;
import android.widget.Toast;

import com.leon.estimate_new.R;
import com.leon.estimate_new.base_items.BaseAsync;
import com.leon.estimate_new.di.view_model.CustomDialogModel;
import com.leon.estimate_new.di.view_model.HttpClientWrapper;
import com.leon.estimate_new.enums.DialogType;
import com.leon.estimate_new.enums.ProgressType;
import com.leon.estimate_new.infrastructure.IAbfaService;
import com.leon.estimate_new.infrastructure.ICallback;
import com.leon.estimate_new.infrastructure.ICallbackError;
import com.leon.estimate_new.infrastructure.ICallbackIncomplete;
import com.leon.estimate_new.utils.CustomErrorHandling;
import com.leon.estimate_new.utils.CustomToast;
import com.leon.estimate_new.utils.SimpleMessage;
import com.leon.estimate_new.utils.download.DownloadData;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SendRequest extends BaseAsync {
    private final Object object;
    private final com.leon.estimate_new.tables.Request request;

    public SendRequest(Context context, Object o, String neighbourBillId, String mobile) {
        super(context, o);
        object = o;
        final ArrayList<Integer> selectedServices = new ArrayList<>(Arrays.asList(1, 2));
        request = new com.leon.estimate_new.tables.Request(selectedServices, neighbourBillId, mobile);
    }

    public SendRequest(Context context, Object o, String neighbourBillId, String mobile,
                       String firstName, String sureName, String nationalId, String address) {
        super(context, o);
        object = o;
        final ArrayList<Integer> selectedServices = new ArrayList<>(7);
        request = new com.leon.estimate_new.tables.Request(selectedServices, neighbourBillId, mobile,
                firstName, sureName, nationalId, address);
    }

    @Override
    public void postTask(Object o) {
        ((Button) object).setEnabled(true);
    }

    @Override
    public void preTask(Object o) {
        ((Button) object).setEnabled(false);
    }

    @Override
    public void backgroundTask(Activity activity) {
        final Retrofit retrofit = getApplicationComponent().NetworkHelperModel()
                .getInstance(getApplicationComponent().SharedPreferenceModel()
                        .getStringData(TOKEN.getValue()));
        final IAbfaService sendRequest = retrofit.create(IAbfaService.class);
        final Call<SimpleMessage> call;
        if (request.address.isEmpty())
            call = sendRequest.sendRequestNew(request);
        else call = sendRequest.sendRequestAfterSale(request);
        HttpClientWrapper.callHttpAsync(call, ProgressType.NOT_SHOW.getValue(), activity,
                new Request(activity), new RequestIncomplete(activity), new GetError());
    }
}

class Request implements ICallback<SimpleMessage> {
    private final Context context;

    public Request(Context context) {
        this.context = context;
    }

    @Override
    public void execute(Response<SimpleMessage> response) {
        binding.editTextAddress.setText("");
        binding.editTextFamily.setText("");
        binding.editTextName.setText("");
        binding.editTextMobile.setText("");
        binding.editTextNationNumber.setText("");
        binding.editTextBillId.setText("");
        new CustomDialogModel(DialogType.Green, context, response.message(),
                context.getString(R.string.dear_user), context.getString(R.string.request),
                context.getString(R.string.accepted));
        new DownloadData().execute();
    }

}

class RequestIncomplete implements ICallbackIncomplete<SimpleMessage> {

    private final Context context;

    public RequestIncomplete(Context context) {
        this.context = context;
    }

    @Override
    public void executeIncomplete(Response<SimpleMessage> response) {
        new CustomDialogModel(DialogType.Yellow, context,
                context.getString(R.string.error_on_download),
                context.getString(R.string.dear_user),
                context.getString(R.string.request),
                context.getString(R.string.accepted));
    }
}

class GetError implements ICallbackError {
    @Override
    public void executeError(Throwable t) {
        final CustomErrorHandling customErrorHandlingNew = new CustomErrorHandling(getContext());
        new CustomToast().error(customErrorHandlingNew.getErrorMessageTotal(t), Toast.LENGTH_LONG);
    }
}