package com.leon.estimate_new.utils.document;

import static com.leon.estimate_new.utils.PDFUtility.PDF_ADDRESS;
import static com.leon.estimate_new.utils.PDFUtility.createPdfCrooki;
import static com.leon.estimate_new.utils.PDFUtility.createPdfOriginalForm;
import static com.leon.estimate_new.utils.PDFUtility.createPdfPrivilegeForm;
import static com.leon.estimate_new.utils.PDFUtility.getImagesFromPDF;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.widget.Toast;

import com.leon.estimate_new.activities.FinalReportActivity;
import com.leon.estimate_new.base_items.BaseAsync;
import com.leon.estimate_new.tables.ExaminerDuties;
import com.leon.estimate_new.utils.CustomToast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PrepareOutputImage extends BaseAsync {
    private Bitmap[] bitmaps;
    private Bitmap bitmapOutput;
    private Bitmap bitmapCrooki;
    private Bitmap licenceBitmap;
    private List<String[]> licenceRows;
    private final ExaminerDuties examinerDuty;
    private final boolean licence;

    public PrepareOutputImage(Context context, ExaminerDuties examinerDuties, boolean licence, Object... view) {
        super(context, view);
        examinerDuty = examinerDuties;
        this.licence = licence;
        if (view.length == 3) {
            bitmaps = new Bitmap[2];
            bitmaps[0] = (Bitmap) view[1];
            bitmaps[1] = (Bitmap) view[2];
        }
    }

    public PrepareOutputImage(Context context, ExaminerDuties examinerDuties,
                              boolean licence, List<String[]> licenceRows, Object... view) {
        super(context, view);
        examinerDuty = examinerDuties;
        this.licence = licence;
        this.licenceRows = licenceRows;
        if (view.length == 3) {
            bitmaps = new Bitmap[2];
            bitmaps[0] = (Bitmap) view[1];
            bitmaps[1] = (Bitmap) view[2];
        }
    }


    @Override
    public void postTask(Object o) {
        ((FinalReportActivity) o).setFormImageView(new Bitmap[]{bitmapOutput, bitmapCrooki});
        if (licence)
            ((FinalReportActivity) o).setLicenceImageView(licenceBitmap, licenceRows);
    }

    @Override
    public void preTask(Object o) {

    }

    @Override
    public void backgroundTask(Activity activity) {
        try {
            if (bitmaps != null && bitmaps.length > 0)
                createPdfOriginalForm(activity, null, getFormData(), true, bitmaps);
            else
                createPdfOriginalForm(activity, null, getFormData(), true);
            bitmapOutput = getImagesFromPDF(new File(PDF_ADDRESS), activity);
        } catch (Exception e) {
            e.printStackTrace();
            new CustomToast().error(e.getMessage(), Toast.LENGTH_LONG);
        }
        try {
            if (bitmaps != null && bitmaps.length > 0)
                createPdfCrooki(activity, null, getCrookiData(), true, bitmaps);
            else
                createPdfCrooki(activity, null, getCrookiData(), true);
            bitmapCrooki = getImagesFromPDF(new File(PDF_ADDRESS), activity);
        } catch (Exception e) {
            new CustomToast().error(e.getMessage(), Toast.LENGTH_LONG);
        }
        if (licence)
            try {
                if (bitmaps != null && bitmaps.length > 0)
                    createPdfPrivilegeForm(activity, null, getLicenceData(), true, bitmaps);
                else
                    createPdfPrivilegeForm(activity, null, getLicenceData(), true);
                licenceBitmap = getImagesFromPDF(new File(PDF_ADDRESS), activity);
            } catch (Exception e) {
                new CustomToast().error(e.getMessage(), Toast.LENGTH_LONG);
            }
    }

    private List<String[]> getFormData() {
        List<String[]> temp = new ArrayList<>();

        String[] rowString = new String[]{examinerDuty.billId, "شناسه قبض", examinerDuty.eshterak,
                "اشتراک", examinerDuty.radif, "ردیف"};
        temp.add(rowString);

        rowString = new String[]{String.valueOf(examinerDuty.sanadNumber), "شماره سند",
                examinerDuty.parNumber, "شماره پروانه", examinerDuty.trackNumber, "شماره پیگیری"};
        temp.add(rowString);

        rowString = new String[]{examinerDuty.fatherName, "نام پدر", examinerDuty.sureName,
                "نام خانوادگی", examinerDuty.firstName, "نام"};
        temp.add(rowString);

        rowString = new String[]{examinerDuty.phoneNumber, "تلفن ثابت", examinerDuty.mobile != null ?
                examinerDuty.mobile : examinerDuty.moshtarakMobile,
                "تلفن همراه", examinerDuty.nationalId, "کدملی"};
        temp.add(rowString);

        rowString = new String[]{examinerDuty.postalCode, "کد پستی", examinerDuty.address, "آدرس"};
        temp.add(rowString);

        rowString = new String[]{examinerDuty.noeVagozariS, "نوع واگذاری", examinerDuty.karbariS, "کاربری"};
        temp.add(rowString);

        rowString = new String[]{String.valueOf(examinerDuty.sifoon200), "سیفون 200:",
                String.valueOf(examinerDuty.sifoon150), "سیفون 150:",
                String.valueOf(examinerDuty.sifoon125), "سیفون 125:",
                String.valueOf(examinerDuty.sifoon100), "سیفون :100",
                String.valueOf(examinerDuty.arse), "عرصه کل:",
                String.valueOf(examinerDuty.qotrEnsheabS), "قطر انشعاب:"};
        temp.add(rowString);

        rowString = new String[]{String.valueOf(examinerDuty.zarfiatQarardadi), "ظرفیت قراردادی:",
                String.valueOf(examinerDuty.tedadTejari), "تعداد واحد تجاری",
                String.valueOf(examinerDuty.aianMaskooni), "اعیان مسکونی"};
        temp.add(rowString);

        rowString = new String[]{String.valueOf(examinerDuty.arzeshMelk), "ارزش منطقی:",
                String.valueOf(examinerDuty.tedadMaskooni), "تعداد واحد مسکونی:",
                String.valueOf(examinerDuty.aianNonMaskooni), "اعیان غیرمسکونی:"
        };
        temp.add(rowString);

        rowString = new String[]{"", "", String.valueOf(examinerDuty.aianKol), "تعدد واحد سایر:"
                , String.valueOf(examinerDuty.tedadSaier), "اعیان کل:"};
        temp.add(rowString);


        rowString = new String[]{"ظرفیت","مقدار", "واحد محاسبه", "تعداد واحد", "نوع شغل", "کاربری",};
//        temp.add(rowString);

        for (int i = 0; i < 9; i++)
            temp.add(rowString);


        rowString = new String[]{String.valueOf(examinerDuty.faseleOtherA), "دیگر:",
                String.valueOf(examinerDuty.faseleSangA), "سنگ فرش:",
                String.valueOf(examinerDuty.faseleAsphaltA), "آسفالت:",
                String.valueOf(examinerDuty.faseleKhakiA), "خاکی:", "فاصله تا شبکه آب"};
        temp.add(rowString);

        rowString = new String[]{String.valueOf(examinerDuty.faseleOtherF), "دیگر:",
                String.valueOf(examinerDuty.faseleSangF), "سنگ فرش:",
                String.valueOf(examinerDuty.faseleAsphaltF), "آسفالت:",
                String.valueOf(examinerDuty.faseleKhakiF), "خاکی:", "تا شبکه فاضلاب"};
        temp.add(rowString);

        //TODO
        rowString = new String[]{String.valueOf(examinerDuty.omqFazelab), "عمق شبکه فاضلاب:",
                String.valueOf(examinerDuty.vaziatNasbPompI), "وضعیت نصب پمپ:",
                examinerDuty.jensLooleS, "جنس لوله:", examinerDuty.qotrLooleS, "قطر لوله:"};
        temp.add(rowString);

        rowString = new String[]{examinerDuty.noeVagozariS, "نوع مصرف:",
                examinerDuty.etesalZirzamin ? "دارد" : "ندارد", "اتصال به زیرزمین:",
                String.valueOf(examinerDuty.omqeZirzamin), "عمق زیرزمین:"};
        temp.add(rowString);

        rowString = new String[]{examinerDuty.looleF ? "دارد" : "ندارد", "لوله فاضلاب:",
                examinerDuty.looleA ? "دارد" : "ندارد", "لوله آب:",
                examinerDuty.ezharNazarA ? "دارد" : "ندارد", "نظرواحد بهره برداری آب:",
                examinerDuty.ezharNazarF ? "دارد" : "ندارد", "نظرواحد بهره برداری فاضلاب:"};
        temp.add(rowString);

        rowString = new String[]{examinerDuty.chahAbBaran ? "دارد" : "ندارد", "چاه آب باران:",
                examinerDuty.estelamShahrdari ? "دارد" : "ندارد", "استعلام شهرداری:",
                examinerDuty.parvane ? "دارد" : "ندارد", "پروانه:",
                examinerDuty.motaqazi ? "دارد" : "ندارد", "اظهارات متقاضی:"};
        temp.add(rowString);

        rowString = new String[]{examinerDuty.examinerName};
        temp.add(rowString);
        return temp;
    }

    private List<String[]> getCrookiData() {
        final List<String[]> temp = new ArrayList<>();
        String[] rowString = new String[]{examinerDuty.zoneTitle};
        temp.add(rowString);

        rowString = new String[]{String.valueOf(examinerDuty.x1), String.valueOf(examinerDuty.y1)};
        temp.add(rowString);

        rowString = new String[]{examinerDuty.address};
        temp.add(rowString);

        rowString = new String[]{examinerDuty.nameAndFamily, examinerDuty.radif, examinerDuty.eshterak,
                examinerDuty.phoneNumber, examinerDuty.mobile != null ? examinerDuty.mobile :
                examinerDuty.moshtarakMobile, examinerDuty.postalCode};
        temp.add(rowString);


        rowString = new String[]{examinerDuty.examinerName};
        temp.add(rowString);

        return temp;
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
                    examinerDuty.moshtarakMobile, examinerDuty.operation, examinerDuty.parNumber,
                    examinerDuty.sodurDate};
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
    public void backgroundTask(Context context) {
    }
}
