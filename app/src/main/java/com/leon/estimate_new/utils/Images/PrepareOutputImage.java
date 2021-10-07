package com.leon.estimate_new.utils.Images;

import static com.leon.estimate_new.utils.PDFUtility.PDF_ADDRESS;
import static com.leon.estimate_new.utils.PDFUtility.getImagesFromPDF;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.widget.Toast;

import com.leon.estimate_new.R;
import com.leon.estimate_new.activities.FinalReportActivity;
import com.leon.estimate_new.di.view_model.CustomProgressModel;
import com.leon.estimate_new.helpers.MyApplication;
import com.leon.estimate_new.utils.CustomToast;
import com.leon.estimate_new.utils.PDFUtility;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PrepareOutputImage extends AsyncTask<Activity, String, Activity> {
    private final CustomProgressModel progress;
    private Bitmap bitmap;
    private Bitmap[] bitmaps;

    public PrepareOutputImage(Context context, Bitmap bitmap1, Bitmap bitmap2) {
        super();
        progress = MyApplication.getApplicationComponent().CustomProgressModel();
        progress.show(context, context.getString(R.string.waiting));
        bitmaps = new Bitmap[2];
        bitmaps[0] = bitmap1;
        bitmaps[1] = bitmap2;
    }

    public PrepareOutputImage(Context context) {
        super();
        progress = MyApplication.getApplicationComponent().CustomProgressModel();
        progress.show(context, context.getString(R.string.waiting));
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Activity activity) {
        super.onPostExecute(activity);
        ((FinalReportActivity) activity).setImageView(bitmap);
        if (progress.getDialog() != null)
            try {
                progress.getDialog().dismiss();
            } catch (Exception e) {
                new CustomToast().error(e.getMessage(), Toast.LENGTH_LONG);
            }
    }

    @Override
    protected Activity doInBackground(Activity... activities) {
        try {
            if (bitmaps != null && bitmaps.length > 0)
                PDFUtility.createPdf(activities[0], null, getFormData(), true, bitmaps);
            else
                PDFUtility.createPdf(activities[0], null, getFormData(), true);
            bitmap = getImagesFromPDF(new File(PDF_ADDRESS), activities[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return activities[0];
    }

    private List<String[]> getFormData() {
        int row = 31, column = 10;
        List<String[]> temp = new ArrayList<>();
        for (int i = 0; i < row; i++) {
            String[] rowString = new String[column];
            for (int j = 0; j < column; j++)
                rowString[j] = "ستون " + j + 1 + " سطر " + i + 1;
            temp.add(rowString);
        }
        return temp;
    }
}
