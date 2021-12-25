package com.leon.estimate_new.base_items;

import static com.leon.estimate_new.helpers.MyApplication.getApplicationComponent;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;

import com.leon.estimate_new.di.view_model.CustomProgressModel;

public abstract class BaseAsync extends AsyncTask<Activity, Void, Void> {
    private final CustomProgressModel progress;
    private final Object view;
    private final boolean aBoolean;

    public BaseAsync(Context context, Object... view) {
        super();
        progress = getApplicationComponent().CustomProgressModel();
        progress.show(context);
        this.view = view[0];
        if (view.length > 1)
            this.aBoolean = (Boolean) view[1];
        else this.aBoolean = true;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        preTask(view);
    }

    @Override
    protected Void doInBackground(Activity... activities) {
        backgroundTask(activities[0]);
        return null;
    }

    @Override
    protected void onPostExecute(Void unused) {
        super.onPostExecute(unused);
        postTask(view);
        if (aBoolean)
            progress.cancelDialog();
    }

    public abstract void postTask(Object o);

    public abstract void preTask(Object o);

    public abstract void backgroundTask(Activity activity);
}
