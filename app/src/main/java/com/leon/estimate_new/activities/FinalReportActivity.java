package com.leon.estimate_new.activities;

import static com.leon.estimate_new.enums.DialogType.Yellow;
import static com.leon.estimate_new.utils.PDFUtility.PDF_ADDRESS;
import static com.leon.estimate_new.utils.PDFUtility.getImagesFromPDF;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.leon.estimate_new.R;
import com.leon.estimate_new.databinding.ActivityFinalReportBinding;
import com.leon.estimate_new.di.view_model.CustomDialogModel;
import com.leon.estimate_new.utils.CustomToast;
import com.leon.estimate_new.utils.Images.PrepareOutputImage;
import com.leon.estimate_new.utils.PDFUtility;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FinalReportActivity extends AppCompatActivity {
    private ActivityFinalReportBinding binding;
    private Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFinalReportBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        activity = this;
        initialize();
    }

    private void initialize() {
//        try {
//            PDFUtility.createPdf(this, null, getFormData(), true);
//            binding.imageViewPdf.setImageBitmap(getImagesFromPDF(new File(PDF_ADDRESS), this));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        new PrepareOutputImage(activity).execute(activity);
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
            new PrepareOutputImage(activity, bitmap1, bitmap2).execute(activity);
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

    public void setImageView(Bitmap bitmap) {
        runOnUiThread(() -> binding.imageViewPdf.setImageBitmap(bitmap));
    }
}