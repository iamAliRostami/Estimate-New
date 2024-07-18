package com.leon.estimate_new.utils.document;

import static com.leon.estimate_new.enums.DialogType.Yellow;
import static com.leon.estimate_new.enums.ProgressType.SHOW_CANCELABLE;
import static com.leon.estimate_new.enums.SharedReferenceKeys.TOKEN_FOR_FILE;
import static com.leon.estimate_new.helpers.MyApplication.getApplicationComponent;
import static com.leon.estimate_new.utils.CustomFile.bitmapToFile;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.leon.estimate_new.R;
import com.leon.estimate_new.base_items.BaseAsync;
import com.leon.estimate_new.di.view_model.CustomDialogModel;
import com.leon.estimate_new.di.view_model.HttpClientWrapper;
import com.leon.estimate_new.fragments.documents.TakePhotoFragment;
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
    private final Fragment fragment;
    private final String key;
    private final int id;
    private final boolean isNew;

    public UploadImages(Fragment fragment, String key, int id, boolean isNew) {
        super(false);
        this.fragment = fragment;
        this.id = id;
        this.key = key;
        this.isNew = isNew;
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
        final IAbfaService abfaService = retrofit.create(IAbfaService.class);
        final MultipartBody.Part body = bitmapToFile(((TakePhotoFragment) fragment)
                .documentActivity.getBitmap(), activity);
        final Call<UploadImage> call = getCall(abfaService, body);
        HttpClientWrapper.callHttpAsync(call, SHOW_CANCELABLE.getValue(), activity,
                new UploadImageDoc(fragment), new UploadImageIncomplete(fragment),
                new UploadImageError(fragment));
    }

    private Call<UploadImage> getCall(IAbfaService abfaService, MultipartBody.Part body) {
        String token = getApplicationComponent().SharedPreferenceModel()
                .getStringData(TOKEN_FOR_FILE.getValue());
        if (isNew)
            return abfaService.uploadDocNew(token, body, id, key);
        return abfaService.uploadDoc(token, body, id, key);
    }

    @Override
    public void backgroundTask(Context context) {

    }
}

class UploadImageDoc implements ICallback<UploadImage> {
    private final Fragment fragment;

    UploadImageDoc(Fragment fragment) {
        this.fragment = fragment;
    }

    @Override
    public void execute(Response<UploadImage> response) {
        ((TakePhotoFragment) fragment).addUploadedImage();
        if (response.body() != null && response.body().success) {
            new CustomToast().success(fragment.requireContext().getString(R.string.upload_success), Toast.LENGTH_LONG);
        } else {
            new CustomDialogModel(Yellow, fragment.requireContext(), fragment.requireContext()
                    .getString(R.string.error_upload).concat("\n").concat(response.body().error),
                    fragment.requireContext().getString(R.string.dear_user),
                    fragment.requireContext().getString(R.string.upload_image),
                    fragment.requireContext().getString(R.string.accepted));
            ((TakePhotoFragment) fragment).saveBitmap();
        }
    }
}

class UploadImageIncomplete implements ICallbackIncomplete<UploadImage> {
    private final Fragment fragment;

    UploadImageIncomplete(Fragment fragment) {
        this.fragment = fragment;
    }

    @Override
    public void executeIncomplete(Response<UploadImage> response) {
        final CustomErrorHandling errorHandling = new CustomErrorHandling(fragment.requireContext());
        final String error = errorHandling.getErrorMessageDefault(response);
        new CustomDialogModel(Yellow, fragment.requireContext(), error,
                fragment.requireContext().getString(R.string.dear_user),
                fragment.requireContext().getString(R.string.upload_image),
                fragment.requireContext().getString(R.string.accepted));
        ((TakePhotoFragment) fragment).addUploadedImage();
        ((TakePhotoFragment) fragment).saveBitmap();
    }
}

class UploadImageError implements ICallbackError {
    private final Fragment fragment;

    public UploadImageError(Fragment fragment) {
        this.fragment = fragment;
    }

    @Override
    public void executeError(Throwable t) {
        final CustomErrorHandling errorHandling = new CustomErrorHandling(fragment.requireContext());
        final String error = errorHandling.getErrorMessageTotal(t);
        new CustomToast().error(error, Toast.LENGTH_LONG);
        ((TakePhotoFragment) fragment).addUploadedImage();
        ((TakePhotoFragment) fragment).saveBitmap();
    }
}