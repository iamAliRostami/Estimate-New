package com.leon.estimate_new.utils.list;

import static com.leon.estimate_new.helpers.MyApplication.getApplicationComponent;

import android.content.Context;
import android.os.AsyncTask;

import com.leon.estimate_new.activities.ListActivity;
import com.leon.estimate_new.di.view_model.CustomProgressModel;
import com.leon.estimate_new.tables.ExaminerDuties;

import java.util.ArrayList;

public class PrepareListData extends AsyncTask<ListActivity, Void, Void> {
    private final CustomProgressModel progress;

    public PrepareListData(Context context) {
        super();
        progress = getApplicationComponent().CustomProgressModel();
        progress.show(context);
    }

    @Override
    protected Void doInBackground(ListActivity... listActivities) {
        listActivities[0].initializeRecyclerView((ArrayList<ExaminerDuties>)
                getApplicationComponent().MyDatabase().examinerDutiesDao().ExaminerDuties());
        return null;
    }

    @Override
    protected void onPostExecute(Void unused) {
        super.onPostExecute(unused);
        progress.cancelDialog();
    }
}
