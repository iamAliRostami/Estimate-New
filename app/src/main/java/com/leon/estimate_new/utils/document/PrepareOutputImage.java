package com.leon.estimate_new.utils.document;

import static com.leon.estimate_new.helpers.MyApplication.getApplicationComponent;
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
import com.leon.estimate_new.tables.RequestDictionary;
import com.leon.estimate_new.tables.Tejariha;
import com.leon.estimate_new.utils.CustomToast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PrepareOutputImage extends BaseAsync {
    private final ExaminerDuties examinerDuty;
    private final boolean licence;
    private Bitmap[] bitmaps;
    private Bitmap bitmapOutput;
    private Bitmap bitmapCrooki;
    private Bitmap licenceBitmap;
    private List<String[]> licenceRows;

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
        if (licence)
            ((FinalReportActivity) o).setFormImageView(new Bitmap[]{bitmapOutput, bitmapCrooki},
                    licenceBitmap, licenceRows);
        else ((FinalReportActivity) o).setFormImageView(new Bitmap[]{bitmapOutput, bitmapCrooki});

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
            e.printStackTrace();
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
                e.printStackTrace();
                new CustomToast().error(e.getMessage(), Toast.LENGTH_LONG);
            }
    }

    private List<String[]> getFormData() {
        final List<String[]> temp = new ArrayList<>();

        String[] rowString = new String[]{examinerDuty.trackNumber, "شماره پیگیری",
                (examinerDuty.eshterak == null || examinerDuty.eshterak.trim().isEmpty()) ? "-" :
                        examinerDuty.eshterak, "اشتراک", examinerDuty.radif, "ردیف"};
        temp.add(rowString);

        rowString = new String[]{examinerDuty.billId != null ? examinerDuty.billId :
                examinerDuty.neighbourBillId, "شناسه قبض", examinerDuty.licenceNumber != null ?
                examinerDuty.licenceNumber : "-", "شماره پروانه",
                String.valueOf(examinerDuty.sanadNumber), "شماره سند"};
        temp.add(rowString);

        rowString = new String[]{examinerDuty.fatherName, "نام پدر", examinerDuty.sureName,
                "نام خانوادگی", examinerDuty.firstName, "نام"};
        temp.add(rowString);

        rowString = new String[]{examinerDuty.phoneNumber, "تلفن ثابت", examinerDuty.mobile != null ?
                examinerDuty.mobile : examinerDuty.moshtarakMobile, "تلفن همراه",
                examinerDuty.nationalId, "کدملی"};
        temp.add(rowString);

        rowString = new String[]{examinerDuty.postalCode, "کد پستی", examinerDuty.address, "آدرس"};
        temp.add(rowString);

        rowString = new String[]{examinerDuty.noeVagozariS, "نوع واگذاری", "اطلاعات فعلی",
                "اطلاعات قبلی", "شرح"};
        temp.add(rowString);


        int aianKolNew = examinerDuty.aianKolNew != null ? examinerDuty.aianKolNew : 0;
        int aianMaskooniNew = examinerDuty.aianMaskooniNew != null ? examinerDuty.aianMaskooniNew : 0;
        int aianNonMaskooniNew = examinerDuty.aianNonMaskooniNew != null ? examinerDuty.aianNonMaskooniNew : 0;

        /*rowString = new String[]{(examinerDuty.block == null || examinerDuty.block.isEmpty()) ? "-" :
                examinerDuty.block, "بلوک",
                examinerDuty.arseNew != null ? String.valueOf(examinerDuty.arseNew) : "-",
                String.valueOf(examinerDuty.arse), "عرصه"};*/
        rowString = new String[]{(examinerDuty.blockId == null || examinerDuty.blockId.isEmpty()) ? "-" :
                examinerDuty.blockId, "بلوک",
                examinerDuty.arseNew != null ? String.valueOf(examinerDuty.arseNew) : "-",
                String.valueOf(examinerDuty.arse), "عرصه"};
        temp.add(rowString);

        rowString = new String[]{(examinerDuty.arz == null || examinerDuty.arz.isEmpty()) ? "-" :
                examinerDuty.arz, "عرض گذر", aianKolNew != 0 ? String.valueOf(aianKolNew) : "-",
                String.valueOf(examinerDuty.aianKol), "اعیانی کل"};
        temp.add(rowString);

        rowString = new String[]{String.valueOf(examinerDuty.arzeshMelk), "ارزش معاملاتی",
                aianMaskooniNew != 0 ? String.valueOf(aianMaskooniNew) : "-",
                String.valueOf(examinerDuty.aianMaskooni), "اعیانی مسکونی"};
        temp.add(rowString);

        rowString = new String[]{String.valueOf(examinerDuty.taxfifS), "نوع تخفیف",
                aianNonMaskooniNew != 0 ? String.valueOf(aianNonMaskooniNew) : "-",
                String.valueOf(examinerDuty.aianNonMaskooni), "اعیانی تجاری"};
        temp.add(rowString);

        //TODO
        rowString = new String[]{String.valueOf(examinerDuty.tedadTaxfif), "تعداد واحد تخفیف",
                String.valueOf(aianKolNew - (aianNonMaskooniNew + aianMaskooniNew)),
                String.valueOf(examinerDuty.aianKol - (examinerDuty.aianNonMaskooni + examinerDuty.aianMaskooni)),
                "اعیانی سایر"};
        temp.add(rowString);


        rowString = new String[]{examinerDuty.ensheabType, "نوع انشعاب",
                examinerDuty.tedadMaskooniNew != null ? String.valueOf(examinerDuty.tedadMaskooniNew) : "-",
                String.valueOf(examinerDuty.tedadMaskooni), "تعداد واحد مسکونی"};
        temp.add(rowString);

        rowString = new String[]{examinerDuty.adamLicence ? "دارد" : "ندارد", "مجوز عدم تولی",
                examinerDuty.tedadTejariNew != null ? String.valueOf(examinerDuty.tedadTejariNew) : "-",
                String.valueOf(examinerDuty.tedadTejari), "تعداد واحد تجاری"};
        temp.add(rowString);

        rowString = new String[]{examinerDuty.qaradad ? "دارد" : "ندارد", "قرارداد آماده سازی",
                examinerDuty.tedadSaierNew != null ? String.valueOf(examinerDuty.tedadSaierNew) : "-",
                String.valueOf(examinerDuty.tedadSaier), "تعداد واحد سایر"};
        temp.add(rowString);

        rowString = new String[]{examinerDuty.qaradadNumber != null ? examinerDuty.qaradadNumber : "-", "شماره قرارداد",
                String.valueOf(examinerDuty.zarfiatQarardadiNew),
                String.valueOf(examinerDuty.zarfiatQarardadi), "ظرفیت قراردادی"};
        temp.add(rowString);

        rowString = new String[]{"ظرفیت", "مقدار", "واحد محاسبه", "تعداد واحد", "نوع شغل", "کاربری"};
        temp.add(rowString);

        final ArrayList<Tejariha> tejarihas = new ArrayList<>(getApplicationComponent().MyDatabase().tejarihaDao().getTejarihaByTrackNumber(examinerDuty.trackNumber));
        for (int i = 0; i < tejarihas.size(); i++) {
            final Tejariha tejariha = tejarihas.get(i);
            rowString = new String[]{String.valueOf(tejariha.capacity), tejariha.a, tejariha.vahedMohasebe,
                    String.valueOf(tejariha.tedadVahed), tejariha.noeShoql, tejariha.karbari};
            temp.add(rowString);
        }
        for (int i = 8; i > tejarihas.size(); i--) {
            rowString = new String[]{"-", "-", "-", "-", "-", "-"};
            temp.add(rowString);
        }

        rowString = new String[]{examinerDuty.qotrEnsheabFS, "قطر انشعاب فاضلاب",
                examinerDuty.qotrEnsheabS, "قطر انشعاب آب",
                examinerDuty.looleF ? "دارد" : "ندارد", "امکان فنی فاضلاب:",
                examinerDuty.looleA ? "دارد" : "ندارد", "امکان فنی آب:"};
        temp.add(rowString);

        rowString = new String[]{examinerDuty.ezharNazarA ? "دارد" : "ندارد", "اظهار نظر بهره برداری:"};
        temp.add(rowString);

        rowString = new String[]{String.valueOf(examinerDuty.faseleOtherA), "سایر:",
                String.valueOf(examinerDuty.faseleSangA), "سنگ فرش:",
                String.valueOf(examinerDuty.faseleAsphaltA), "آسفالت:",
                String.valueOf(examinerDuty.faseleKhakiA), "خاکی:", "فاصله تا شبکه آب"};
        temp.add(rowString);

        rowString = new String[]{String.valueOf(examinerDuty.faseleOtherF), "سایر:",
                String.valueOf(examinerDuty.faseleSangF), "سنگ فرش:",
                String.valueOf(examinerDuty.faseleAsphaltF), "آسفالت:",
                String.valueOf(examinerDuty.faseleKhakiF), "خاکی:", "تا شبکه فاضلاب"};
        temp.add(rowString);

        final int charPerLine = 100;
        String description = examinerDuty.description;
        rowString = new String[]{description.substring(0, Math.min(description.length(), charPerLine)), "توضیحات: "};
        temp.add(rowString);
        /* 8 lines * 100 char */
        /* 14 lines descriptions */

        while (temp.size() < 42) {
            if (description.length() > charPerLine) {
                description = description.substring(charPerLine);
                rowString = new String[]{description.substring(0, Math.min(description.length(), charPerLine)), " "};
            } else {
                rowString = new String[]{"-", " "};
            }
            temp.add(rowString);
        }

        StringBuilder requestDictionaryString = new StringBuilder();
        for (RequestDictionary requestDictionary : examinerDuty.requestDictionary) {
            if (requestDictionary.isSelected)
                requestDictionaryString.append(requestDictionary.title.concat(" - "));
        }
        rowString = new String[]{examinerDuty.examinerName, examinerDuty.zoneTitle, requestDictionaryString.toString()};
        temp.add(rowString);
        return temp;
    }

    private List<String[]> getCrookiData() {
        final List<String[]> temp = new ArrayList<>();
        String[] rowString = new String[]{examinerDuty.zoneTitle, examinerDuty.trackNumber};
        temp.add(rowString);

        rowString = new String[]{String.valueOf(examinerDuty.x1), String.valueOf(examinerDuty.y1),
                String.valueOf(examinerDuty.x2), String.valueOf(examinerDuty.y2)};
        temp.add(rowString);

        rowString = new String[]{examinerDuty.address};
        temp.add(rowString);

        rowString = new String[]{examinerDuty.nameAndFamily != null ? examinerDuty.nameAndFamily :
                examinerDuty.firstName.concat(" ").concat(examinerDuty.sureName),
                examinerDuty.radif, (examinerDuty.eshterak == null ||
                examinerDuty.eshterak.trim().isEmpty()) ? "-" : examinerDuty.eshterak, examinerDuty.phoneNumber,
                examinerDuty.mobile != null ? examinerDuty.mobile : examinerDuty.moshtarakMobile,
                examinerDuty.postalCode};
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

            rowString = new String[]{examinerDuty.nameAndFamily != null ? examinerDuty.nameAndFamily :
                    examinerDuty.firstName.concat(" ").concat(examinerDuty.sureName),
                    examinerDuty.fatherName, examinerDuty.nationalId, examinerDuty.mobile != null ?
                    examinerDuty.mobile : examinerDuty.moshtarakMobile, examinerDuty.operation,
                    examinerDuty.licenceNumber != null ? examinerDuty.licenceNumber : "-",
                    examinerDuty.sodurDate != null ? examinerDuty.sodurDate : "-"};
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


            rowString = new String[]{String.valueOf(examinerDuty.x1), String.valueOf(examinerDuty.y1)};
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
