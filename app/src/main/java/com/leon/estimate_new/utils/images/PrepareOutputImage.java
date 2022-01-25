package com.leon.estimate_new.utils.images;

import static com.leon.estimate_new.utils.PDFUtility.PDF_ADDRESS;
import static com.leon.estimate_new.utils.PDFUtility.getImagesFromPDF;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;

import com.leon.estimate_new.activities.FinalReportActivity;
import com.leon.estimate_new.base_items.BaseAsync;
import com.leon.estimate_new.utils.CustomToast;
import com.leon.estimate_new.utils.PDFUtility;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PrepareOutputImage extends BaseAsync {
    private Bitmap[] bitmaps;
    private Bitmap bitmap;

    public PrepareOutputImage(Context context, Object... view) {
        super(context, view);
        if (view.length == 3) {
            bitmaps = new Bitmap[2];
            bitmaps[0] = (Bitmap) view[1];
            bitmaps[1] = (Bitmap) view[2];
        }
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

    @Override
    public void postTask(Object o) {
        ((FinalReportActivity) o).setImageView(bitmap);
    }

    @Override
    public void preTask(Object o) {

    }

    @Override
    public void backgroundTask(Activity activity) {
        try {
            if (bitmaps != null && bitmaps.length > 0)
                PDFUtility.createPdfOriginalForm(activity, null, getFormData(), true, bitmaps);
            else
                PDFUtility.createPdfOriginalForm(activity, null, getFormData(), true);
            bitmap = getImagesFromPDF(new File(PDF_ADDRESS), activity);
        } catch (Exception e) {
            e.printStackTrace();
            new CustomToast().error(e.getMessage());
        }
    }

    @Override
    public void backgroundTask(Context context) {

    }
}
