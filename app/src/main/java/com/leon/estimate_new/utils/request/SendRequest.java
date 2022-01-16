package com.leon.estimate_new.utils.request;

import static com.leon.estimate_new.enums.SharedReferenceKeys.TOKEN;
import static com.leon.estimate_new.helpers.MyApplication.getApplicationComponent;
import static com.leon.estimate_new.helpers.MyApplication.getContext;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import com.leon.estimate_new.R;
import com.leon.estimate_new.base_items.BaseAsync;
import com.leon.estimate_new.di.view_model.CustomDialogModel;
import com.leon.estimate_new.di.view_model.HttpClientWrapper;
import com.leon.estimate_new.enums.DialogType;
import com.leon.estimate_new.enums.ProgressType;
import com.leon.estimate_new.fragments.main_items.SendRequestFragment;
import com.leon.estimate_new.infrastructure.IAbfaService;
import com.leon.estimate_new.infrastructure.ICallback;
import com.leon.estimate_new.infrastructure.ICallbackError;
import com.leon.estimate_new.infrastructure.ICallbackIncomplete;
import com.leon.estimate_new.tables.Request;
import com.leon.estimate_new.utils.CustomErrorHandling;
import com.leon.estimate_new.utils.CustomToast;
import com.leon.estimate_new.utils.SimpleMessage;
import com.leon.estimate_new.utils.download.DownloadData;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SendRequest extends BaseAsync {
    private final Object object;
    private final Request request;

    public SendRequest(Context context, Object o, String neighbourBillId, String mobile) {
        super(context, o);
        object = o;
        final ArrayList<Integer> selectedServices = new ArrayList<>(Arrays.asList(1, 2));
        request = new Request(selectedServices, neighbourBillId, mobile);
    }

    public SendRequest(Context context, Object o, String neighbourBillId, String mobile,
                       String firstName, String sureName, String nationalId, String address) {
        super(context, o);
        object = o;
        final ArrayList<Integer> selectedServices = new ArrayList<>(7);
        request = new Request(selectedServices, neighbourBillId, mobile, firstName, sureName,
                nationalId, address);
    }

    @Override
    public void postTask(Object o) {
        ((SendRequestFragment) object).getButton().setEnabled(true);
    }

    @Override
    public void preTask(Object o) {
        ((SendRequestFragment) object).getButton().setEnabled(false);
    }

    @Override
    public void backgroundTask(Activity activity) {
        final Retrofit retrofit = getApplicationComponent().NetworkHelperModel()
                .getInstance(getApplicationComponent().SharedPreferenceModel()
                        .getStringData(TOKEN.getValue()));
        final IAbfaService sendRequest = retrofit.create(IAbfaService.class);
        final Call<SimpleMessage> call;
        if (request.selectedServices.size() > 1) call = sendRequest.sendRequestAfterSale(request);
        else call = sendRequest.sendRequestNew(request);
        HttpClientWrapper.callHttpAsync(call, ProgressType.NOT_SHOW.getValue(), activity,
                new RequestSuccess(activity, object), new RequestIncomplete(activity), new GetError());
    }
}

class RequestSuccess implements ICallback<SimpleMessage> {
    private final Context context;
    private final Object object;

    public RequestSuccess(Context context, Object object) {
        this.context = context;
        this.object = object;
    }

    @Override
    public void execute(Response<SimpleMessage> response) {
        ((SendRequestFragment) object).afterRequest();
        new CustomDialogModel(DialogType.Green, context, response.message(),
                context.getString(R.string.dear_user), context.getString(R.string.request),
                context.getString(R.string.accepted));
        new DownloadData(context, ((SendRequestFragment) object).getButton()).execute();
    }
}

class RequestIncomplete implements ICallbackIncomplete<SimpleMessage> {

    private final Context context;

    public RequestIncomplete(Context context) {
        this.context = context;
    }

    @Override
    public void executeIncomplete(Response<SimpleMessage> response) {
        String message = "";
        if (response.code() == 400) {
            if (response.errorBody() != null) {
                try {
                    final JSONObject jObjError = new JSONObject(response.errorBody().string());
                    message = jObjError.getString("message");
                } catch (Exception e) {
                    message = e.getMessage();
                }
            }
        } else {
            final CustomErrorHandling customErrorHandling = new CustomErrorHandling(context);
            message = customErrorHandling.getErrorMessageDefault(response);
        }
        new CustomDialogModel(DialogType.Yellow, context, message,
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