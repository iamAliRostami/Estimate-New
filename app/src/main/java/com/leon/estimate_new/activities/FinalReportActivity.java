package com.leon.estimate_new.activities;


import static com.leon.estimate_new.utils.PDFUtility.PDF_ADDRESS;
import static com.leon.estimate_new.utils.PDFUtility.createDataTable;
import static com.leon.estimate_new.utils.PDFUtility.getImageFromDrawable;
import static com.leon.estimate_new.utils.PDFUtility.getImagesFromPDF;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
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
        binding.signatureView1.setVisibility(View.GONE);
        binding.signatureView2.setVisibility(View.GONE);
        try {
            PDFUtility.createPdf(this, null, getFormData(), true, bitmap1, bitmap2);
            binding.imageViewPdf.setImageBitmap(getImagesFromPDF(new File(PDF_ADDRESS), this));
        } catch (Exception e) {
            e.printStackTrace();
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
}