package com.leon.estimate_new.utils.document;

import android.content.Context;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.leon.estimate_new.infrastructure.ICallbackError;
import com.leon.estimate_new.utils.CustomErrorHandling;
import com.leon.estimate_new.utils.CustomToast;

public class GetError implements ICallbackError {
    private final Context context;
    private final ProgressBar progressBar;

    public GetError(Context context, ProgressBar progressBar) {
        this.context = context;
        this.progressBar = progressBar;
    }

    @Override
    public void executeError(Throwable t) {
        final CustomErrorHandling errorHandling = new CustomErrorHandling(context);
        final String error = errorHandling.getErrorMessageTotal(t);
        new CustomToast().error(error, Toast.LENGTH_LONG);
        progressBar.setVisibility(View.GONE);
    }
}
