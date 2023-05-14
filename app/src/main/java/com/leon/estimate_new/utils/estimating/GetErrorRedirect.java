package com.leon.estimate_new.utils.estimating;

import static com.leon.estimate_new.helpers.MyApplication.getContext;

import android.widget.Toast;

import com.leon.estimate_new.fragments.dialog.ShowDocumentFragment;
import com.leon.estimate_new.infrastructure.ICallbackError;
import com.leon.estimate_new.utils.CustomErrorHandling;
import com.leon.estimate_new.utils.CustomToast;

class GetErrorRedirect implements ICallbackError {
    private final Object object;

    GetErrorRedirect(Object object) {
        this.object = object;
    }

    @Override
    public void executeError(Throwable t) {
        final CustomErrorHandling errorHandling = new CustomErrorHandling(getContext());
        final String error = errorHandling.getErrorMessageTotal(t);
        new CustomToast().error(error, Toast.LENGTH_LONG);
        ((ShowDocumentFragment) object).dismiss();
    }
}