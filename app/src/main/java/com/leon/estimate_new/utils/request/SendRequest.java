package com.leon.estimate_new.utils.request;

import static com.leon.estimate_new.enums.DialogType.Green;
import static com.leon.estimate_new.enums.DialogType.Yellow;
import static com.leon.estimate_new.enums.ProgressType.NOT_SHOW;
import static com.leon.estimate_new.helpers.MyApplication.getApplicationComponent;
import static com.leon.estimate_new.helpers.MyApplication.getContext;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import com.leon.estimate_new.R;
import com.leon.estimate_new.base_items.BaseAsync;
import com.leon.estimate_new.di.view_model.CustomDialogModel;
import com.leon.estimate_new.di.view_model.HttpClientWrapper;
import com.leon.estimate_new.fragments.main_items.SendRequestFragment;
import com.leon.estimate_new.infrastructure.IAbfaService;
import com.leon.estimate_new.infrastructure.ICallback;
import com.leon.estimate_new.infrastructure.ICallbackError;
import com.leon.estimate_new.infrastructure.ICallbackIncomplete;
import com.leon.estimate_new.tables.RequestToSend;
import com.leon.estimate_new.utils.CustomErrorHandling;
import com.leon.estimate_new.utils.CustomToast;
import com.leon.estimate_new.utils.SimpleMessage;
import com.leon.estimate_new.utils.downloading.DownloadData;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SendRequest extends BaseAsync {
    private final Object object;
    private final RequestToSend request;

    public SendRequest(Context context, RequestToSend request, Object... o) {
        super(context, false, null);
        this.request = request;
        object = o[0];
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
        final IAbfaService sendRequest = retrofit.create(IAbfaService.class);
        final Call<SimpleMessage> call = request.selectedServices.size() > 1 ?
                sendRequest.sendRequestAfterSale(request) : sendRequest.sendRequestNew(request);
        HttpClientWrapper.callHttpAsync(call, NOT_SHOW.getValue(), activity,
                new RequestSuccess(activity, object), new RequestIncomplete(activity), new GetError());
    }

    @Override
    public void backgroundTask(Context context) {
    }
}

class RequestSuccess implements ICallback<SimpleMessage> {
    private final Activity activity;
    private final Object object;

    public RequestSuccess(Activity activity, Object object) {
        this.activity = activity;
        this.object = object;
    }

    @Override
    public void execute(Response<SimpleMessage> response) {
        ((SendRequestFragment) object).afterRequest();
        if (response.body() != null) {
            activity.runOnUiThread(() ->
                    new CustomDialogModel(Green, activity, response.body().message, activity.getString(R.string.dear_user),
                            activity.getString(R.string.request), activity.getString(R.string.accepted)));

        }
        new DownloadData(activity, true).execute(((SendRequestFragment) object).requireActivity());
    }
}

class RequestIncomplete implements ICallbackIncomplete<SimpleMessage> {

    private final Activity activity;

    public RequestIncomplete(Activity activity) {
        this.activity = activity;
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
            final CustomErrorHandling error = new CustomErrorHandling(activity);
            message = error.getErrorMessageDefault(response);
        }
        String finalMessage = message;
        activity.runOnUiThread(() ->
                new CustomDialogModel(Yellow, activity, finalMessage, activity.getString(R.string.dear_user),
                        activity.getString(R.string.request), activity.getString(R.string.accepted)));

    }
}

class GetError implements ICallbackError {
    @Override
    public void executeError(Throwable t) {
        final CustomErrorHandling error = new CustomErrorHandling(getContext());
        new CustomToast().error(error.getErrorMessageTotal(t), Toast.LENGTH_LONG);
    }
}