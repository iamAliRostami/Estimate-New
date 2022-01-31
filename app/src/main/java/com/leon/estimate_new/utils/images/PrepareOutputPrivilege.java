package com.leon.estimate_new.utils.images;

import static com.leon.estimate_new.utils.PDFUtility.PDF_ADDRESS;
import static com.leon.estimate_new.utils.PDFUtility.createPdfPrivilegeForm;
import static com.leon.estimate_new.utils.PDFUtility.getImagesFromPDF;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;

import com.leon.estimate_new.activities.FinalReportActivity;
import com.leon.estimate_new.base_items.BaseAsync;
import com.leon.estimate_new.tables.ExaminerDuties;
import com.leon.estimate_new.utils.CustomToast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PrepareOutputPrivilege extends BaseAsync {
    private Bitmap[] bitmaps;
    private Bitmap licenceBitmap;
    private List<String[]> licenceRows;
    private final ExaminerDuties examinerDuty;

    public PrepareOutputPrivilege(Context context, ExaminerDuties examinerDuty, Object... view) {
        super(context, view);
//        super(false);
        this.examinerDuty = examinerDuty;
        if (view.length == 2) {
            bitmaps = new Bitmap[1];
            bitmaps[0] = (Bitmap) view[1];
        }
    }

    public PrepareOutputPrivilege(Context context, ExaminerDuties examinerDuty,
                                  List<String[]> licenceRows, Object... view) {
        super(context, view);
//        super(false);
        this.examinerDuty = examinerDuty;
        this.licenceRows = licenceRows;
        if (view.length == 2) {
            bitmaps = new Bitmap[1];
            bitmaps[0] = (Bitmap) view[1];
        }
    }

    private List<String[]> getLicenceData() {
        if (licenceRows == null) {
            licenceRows = new ArrayList<>();
            String[] rowString = new String[]{examinerDuty.examinationDay};
            licenceRows.add(rowString);

            rowString = new String[]{examinerDuty.zoneTitle};
            licenceRows.add(rowString);

            rowString = new String[]{examinerDuty.zoneTitle, examinerDuty.billId != null ?
                    examinerDuty.billId : examinerDuty.neighbourBillId, examinerDuty.trackNumber};
            licenceRows.add(rowString);

            rowString = new String[]{examinerDuty.nameAndFamily, examinerDuty.fatherName,
                    examinerDuty.nationalId, examinerDuty.mobile != null ? examinerDuty.mobile :
                    examinerDuty.moshtarakMobile, "تست", examinerDuty.parNumber, examinerDuty.sodurDate};
            licenceRows.add(rowString);

            rowString = new String[]{examinerDuty.zoneTitle, examinerDuty.address, String.valueOf(examinerDuty.pelak)};
            licenceRows.add(rowString);


            rowString = new String[]{String.valueOf(examinerDuty.faseleAsphaltA +
                    examinerDuty.faseleKhakiA + examinerDuty.faseleSangA), String.valueOf(examinerDuty.faseleAsphaltA),
                    String.valueOf(examinerDuty.faseleKhakiA), String.valueOf(examinerDuty.faseleSangA)};
            licenceRows.add(rowString);

            rowString = new String[]{String.valueOf(examinerDuty.faseleAsphaltF +
                    examinerDuty.faseleKhakiF + examinerDuty.faseleSangF), String.valueOf(examinerDuty.faseleAsphaltF),
                    String.valueOf(examinerDuty.faseleKhakiF), String.valueOf(examinerDuty.faseleSangF)};
            licenceRows.add(rowString);

            rowString = new String[]{String.valueOf(examinerDuty.faseleAsphaltF +
                    examinerDuty.faseleKhakiF + examinerDuty.faseleSangF + examinerDuty.faseleAsphaltF +
                    examinerDuty.faseleKhakiF + examinerDuty.faseleSangF)};
            licenceRows.add(rowString);

            rowString = new String[]{examinerDuty.examinerName};

            licenceRows.add(rowString);
        }
        return licenceRows;
    }

    @Override
    public void postTask(Object o) {
        ((FinalReportActivity) o).setLicenceImageView(licenceBitmap, licenceRows);
    }

    @Override
    public void preTask(Object o) {

    }

    @Override
    public void backgroundTask(Activity activity) {
        try {
            if (bitmaps != null && bitmaps.length > 0)
                createPdfPrivilegeForm(activity, null, getLicenceData(), true, bitmaps);
            else
                createPdfPrivilegeForm(activity, null, getLicenceData(), true);
            licenceBitmap = getImagesFromPDF(new File(PDF_ADDRESS), activity);
        } catch (Exception e) {
            e.printStackTrace();
            new CustomToast().error(e.getMessage());
        }
    }

    @Override
    public void backgroundTask(Context context) {

    }
}
