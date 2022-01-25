package com.leon.estimate_new.utils.images;

import static com.leon.estimate_new.utils.PDFUtility.PDF_ADDRESS;
import static com.leon.estimate_new.utils.PDFUtility.getImagesFromPDF;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;

import com.leon.estimate_new.activities.FinalReportActivity;
import com.leon.estimate_new.base_items.BaseAsync;
import com.leon.estimate_new.tables.ExaminerDuties;
import com.leon.estimate_new.utils.CustomToast;
import com.leon.estimate_new.utils.PDFUtility;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PrepareOutputPrivilege extends BaseAsync {
    private Bitmap[] bitmaps;
    private Bitmap bitmap;
    private final ExaminerDuties examinerDuty;

    public PrepareOutputPrivilege(Context context, ExaminerDuties examinerDuty, Object... view) {
        super(context, view);
        this.examinerDuty = examinerDuty;
        if (view.length == 3) {
            bitmaps = new Bitmap[2];
            bitmaps[0] = (Bitmap) view[1];
            bitmaps[1] = (Bitmap) view[2];
        }
    }

    private List<String[]> getFormData() {
        final List<String[]> temp = new ArrayList<>();

        String[] rowString = new String[]{examinerDuty.examinationDay};
        temp.add(rowString);

        rowString = new String[]{examinerDuty.zoneTitle};
        temp.add(rowString);

        rowString = new String[]{examinerDuty.zoneTitle, examinerDuty.billId != null ?
                examinerDuty.billId : examinerDuty.neighbourBillId, examinerDuty.trackNumber};
        temp.add(rowString);

        rowString = new String[]{examinerDuty.nameAndFamily, examinerDuty.fatherName,
                examinerDuty.nationalId, examinerDuty.mobile != null ? examinerDuty.mobile :
                examinerDuty.moshtarakMobile, "تست", examinerDuty.parNumber, examinerDuty.sodurDate};
        temp.add(rowString);

        rowString = new String[]{examinerDuty.zoneTitle, examinerDuty.address, String.valueOf(examinerDuty.pelak)};
        temp.add(rowString);


        rowString = new String[]{String.valueOf(examinerDuty.faseleAsphaltA +
                examinerDuty.faseleKhakiA + examinerDuty.faseleSangA), String.valueOf(examinerDuty.faseleAsphaltA),
                String.valueOf(examinerDuty.faseleKhakiA), String.valueOf(examinerDuty.faseleSangA)};
        temp.add(rowString);

        rowString = new String[]{String.valueOf(examinerDuty.faseleAsphaltF +
                examinerDuty.faseleKhakiF + examinerDuty.faseleSangF), String.valueOf(examinerDuty.faseleAsphaltF),
                String.valueOf(examinerDuty.faseleKhakiF), String.valueOf(examinerDuty.faseleSangF)};
        temp.add(rowString);

        rowString = new String[]{String.valueOf(examinerDuty.faseleAsphaltF +
                examinerDuty.faseleKhakiF + examinerDuty.faseleSangF + examinerDuty.faseleAsphaltF +
                examinerDuty.faseleKhakiF + examinerDuty.faseleSangF)};
        temp.add(rowString);

        temp.add(rowString);


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
                PDFUtility.createPdfPrivilegeForm(activity, null, getFormData(), true, bitmaps);
            else
                PDFUtility.createPdfPrivilegeForm(activity, null, getFormData(), true);
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
