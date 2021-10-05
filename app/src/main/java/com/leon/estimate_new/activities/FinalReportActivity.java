package com.leon.estimate_new.activities;


import static com.leon.estimate_new.utils.PDFUtility.PDF_ADDRESS;
import static com.leon.estimate_new.utils.PDFUtility.createDataTable;
import static com.leon.estimate_new.utils.PDFUtility.getImageFromDrawable;
import static com.leon.estimate_new.utils.PDFUtility.getImagesFromPDF;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.languages.ArabicLigaturizer;
import com.itextpdf.text.pdf.languages.LanguageProcessor;
import com.leon.estimate_new.R;
import com.leon.estimate_new.databinding.ActivityFinalReportBinding;
import com.leon.estimate_new.helpers.Constants;
import com.leon.estimate_new.utils.CustomToast;
import com.leon.estimate_new.utils.PDFUtility;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FinalReportActivity extends AppCompatActivity {
    private ActivityFinalReportBinding binding;
    private BaseFont baseFont;

    private static Font catFont, redFont, subFont, smallBold;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFinalReportBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initialize();
    }

    private void initialize() {
        try {
            PDFUtility.createPdf(this, null, getFormData(), true);
            binding.imageViewPdf.setImageBitmap(getImagesFromPDF(new File(PDF_ADDRESS), this));
        } catch (Exception e) {
            e.printStackTrace();
        }
        setOnAcceptedButtonClickListener();
//        initializeFonts();
//        try {
//            Document document = new Document();
//            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(PDF_ADDRESS));
//            writer.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
//            document.open();
//            addTitlePage(document);
//            document.close();
//            binding.imageViewPdf.setImageBitmap(getImagesFromPDF(new File(PDF_ADDRESS), this));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    private void setOnAcceptedButtonClickListener() {
        binding.buttonAccepted.setOnClickListener(v -> {
            if (binding.signatureView1.isBitmapEmpty() || binding.signatureView2.isBitmapEmpty()) {
                new CustomToast().warning(getString(R.string.request_sign), Toast.LENGTH_LONG);
            } else {
                addImageSign();
            }
        });
    }

    private void addImageSign() {
        Bitmap bitmap1 = binding.signatureView1.getSignatureBitmap();
        Bitmap bitmap2 = binding.signatureView2.getSignatureBitmap();
        try {
            PDFUtility.createPdf(this, null, getFormData(), true, bitmap1, bitmap2);
            binding.imageViewPdf.setImageBitmap(getImagesFromPDF(new File(PDF_ADDRESS), this));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initializeFonts() {
        try {
            baseFont = BaseFont.createFont(Constants.PDF_FONT_NAME, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }

        catFont = new Font(baseFont, 18, Font.BOLD);
        redFont = new Font(baseFont, 12, Font.NORMAL, BaseColor.RED);
        subFont = new Font(baseFont, 16, Font.BOLD);
        smallBold = new Font(baseFont, 12, Font.BOLD);
    }

    private void addTitlePage(Document document)
            throws DocumentException {
        Paragraph preface = new Paragraph();
        LanguageProcessor pe = new ArabicLigaturizer();
        Paragraph paragraph = new Paragraph();
        paragraph.setAlignment(Element.ALIGN_RIGHT);
        paragraph.setFont(catFont);
        paragraph.add(pe.process("آب و فاضلاب استان اصفهان پرچم"));
        preface.add(paragraph);

        preface.setAlignment(Element.ALIGN_RIGHT);
        document.add(preface);
        getImageFromDrawable(((BitmapDrawable) ContextCompat.getDrawable(this, R.drawable.img_menu_logo)));

        document.add(createDataTable(getFormData()));

        document.newPage();
    }

    private List<String[]> getFormData() {
        int count = 20;

        List<String[]> temp = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            temp.add(new String[]{"ستون " + (i + 1), "ستون " + (i + 1), "ستون " + (i + 1),
                    "ستون " + (i + 1), "ستون " + (i + 1), "ستون " + (i + 1)});
        }
        return temp;
    }
}