package com.leon.estimate_new.utils.estimating;

import static com.leon.estimate_new.enums.DialogType.Yellow;
import static com.leon.estimate_new.enums.ProgressType.SHOW;
import static com.leon.estimate_new.enums.SharedReferenceKeys.TOKEN_FOR_FILE;
import static com.leon.estimate_new.helpers.MyApplication.getApplicationComponent;
import static com.leon.estimate_new.utils.CustomFile.bitmapToFile;
import static com.leon.estimate_new.utils.CustomFile.saveTempBitmap;
import static com.leon.estimate_new.utils.DifferentCompanyManager.getActiveCompanyName;
import static com.leon.estimate_new.utils.DifferentCompanyManager.getDocumentUrl;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import com.leon.estimate_new.R;
import com.leon.estimate_new.activities.FinalReportActivity;
import com.leon.estimate_new.base_items.BaseAsync;
import com.leon.estimate_new.di.view_model.CustomDialogModel;
import com.leon.estimate_new.di.view_model.HttpClientWrapper;
import com.leon.estimate_new.infrastructure.IAbfaService;
import com.leon.estimate_new.infrastructure.ICallback;
import com.leon.estimate_new.infrastructure.ICallbackError;
import com.leon.estimate_new.infrastructure.ICallbackIncomplete;
import com.leon.estimate_new.tables.UploadImage;
import com.leon.estimate_new.utils.CustomErrorHandling;
import com.leon.estimate_new.utils.CustomToast;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

public class UploadImages extends BaseAsync {
    private final String trackNumber, billId;
    private final boolean isNew;
    private final Object object;
    private final int docId;

    public UploadImages(int docId, String trackNumber, String billId, boolean isNew, Object... view) {
        super(false);
        this.docId = docId;
        this.trackNumber = trackNumber;
        this.billId = billId;
        this.isNew = isNew;
        this.object = view[0];
    }

    @Override
    public void postTask(Object o) {
    }

    @Override
    public void preTask(Object o) {
    }

    @Override
    public void backgroundTask(Activity activity) {
        final Retrofit retrofit = getApplicationComponent().NetworkHelperModel()
                .getInstance(getDocumentUrl(getActiveCompanyName()), 1);
        final IAbfaService abfaService = retrofit.create(IAbfaService.class);
        final MultipartBody.Part body = bitmapToFile(((FinalReportActivity) object).getBitmap(), activity);
        final Call<UploadImage> call;
        if (isNew)
            call = abfaService.uploadDocNew(getApplicationComponent().SharedPreferenceModel()
                    .getStringData(TOKEN_FOR_FILE.getValue()), body, docId, trackNumber);
        else
            call = abfaService.uploadDoc(getApplicationComponent().SharedPreferenceModel()
                    .getStringData(TOKEN_FOR_FILE.getValue()), body, docId, billId);
        HttpClientWrapper.callHttpAsync(call, SHOW.getValue(), activity,
                new UploadImageDoc(activity, object, docId, trackNumber, billId, isNew),
                new UploadImageIncomplete(activity, object, docId, trackNumber, billId, isNew),
                new UploadImageError(activity, object, docId, trackNumber, billId, isNew));
    }

    @Override
    public void backgroundTask(Context context) {

    }
}

class UploadImageDoc implements ICallback<UploadImage> {
    private final int docId;
    private final Object object;
    private final boolean isNew;
    private final Activity activity;
    private final String trackNumber, billId;

    UploadImageDoc(Activity activity, Object object, int docId, String trackNumber, String billId,
                   boolean isNew) {
        this.object = object;
        this.activity = activity;
        this.docId = docId;
        this.trackNumber = trackNumber;
        this.billId = billId;
        this.isNew = isNew;
    }

    @Override
    public void execute(Response<UploadImage> response) {
        if (response.body() != null && response.body().success) {
            new CustomToast().success(activity.getString(R.string.upload_success), Toast.LENGTH_LONG);
        } else {
            activity.runOnUiThread(() ->
                    new CustomDialogModel(Yellow, activity,
                            activity.getString(R.string.error_upload)
                            .concat("\n").concat(response.body().error),
                            activity.getString(R.string.dear_user),
                            activity.getString(R.string.upload_image),
                            activity.getString(R.string.accepted)));

            saveTempBitmap(((FinalReportActivity) object).getBitmap(),
                    activity, billId, trackNumber, docId, "فرم ارزیابی", isNew);
        }
        ((FinalReportActivity) object).sendImages();
    }
}

class UploadImageIncomplete implements ICallbackIncomplete<UploadImage> {
    private final int docId;
    private final Object object;
    private final boolean isNew;
    private final Activity activity;
    private final String trackNumber, billId;

    UploadImageIncomplete(Activity activity, Object object, int docId, String trackNumber,
                          String billId, boolean isNew) {
        this.object = object;
        this.activity = activity;
        this.docId = docId;
        this.trackNumber = trackNumber;
        this.billId = billId;
        this.isNew = isNew;
    }

    @Override
    public void executeIncomplete(Response<UploadImage> response) {
        final CustomErrorHandling errorHandling = new CustomErrorHandling(activity);
        final String error = errorHandling.getErrorMessageDefault(response);
        activity.runOnUiThread(() ->
                new CustomDialogModel(Yellow, activity, error, activity.getString(R.string.dear_user),
                        activity.getString(R.string.upload_image), activity.getString(R.string.accepted)));
        saveTempBitmap(((FinalReportActivity) object).getBitmap(), activity, billId, trackNumber,
                docId, "فرم ارزیابی", isNew);
        ((FinalReportActivity) object).setSent(false);
        ((FinalReportActivity) object).sendImages();
    }
}

class UploadImageError implements ICallbackError {
    private final int docId;
    private final Object object;
    private final boolean isNew;
    private final Activity activity;
    private final String trackNumber, billId;

    public UploadImageError(Activity activity, Object object, int docId, String trackNumber,
                            String billId, boolean isNew) {
        this.activity = activity;
        this.object = object;
        this.docId = docId;
        this.trackNumber = trackNumber;
        this.billId = billId;
        this.isNew = isNew;
    }

    @Override
    public void executeError(Throwable t) {
        final CustomErrorHandling errorHandling = new CustomErrorHandling(activity);
        final String error = errorHandling.getErrorMessageTotal(t);
        activity.runOnUiThread(() -> new CustomToast().error(error, Toast.LENGTH_LONG));
        saveTempBitmap(((FinalReportActivity) object).getBitmap(), activity, billId,
                trackNumber, docId, "فرم ارزیابی", isNew);
        ((FinalReportActivity) object).setSent(false);
        ((FinalReportActivity) object).sendImages();
    }
}