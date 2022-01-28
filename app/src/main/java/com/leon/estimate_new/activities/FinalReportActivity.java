package com.leon.estimate_new.activities;

import static com.leon.estimate_new.enums.BundleEnum.TRACK_NUMBER;
import static com.leon.estimate_new.helpers.MyApplication.getApplicationComponent;
import static com.leon.estimate_new.helpers.MyApplication.setActivityComponent;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.GsonBuilder;
import com.leon.estimate_new.R;
import com.leon.estimate_new.databinding.ActivityFinalReportBinding;
import com.leon.estimate_new.tables.ExaminerDuties;
import com.leon.estimate_new.tables.RequestDictionary;
import com.leon.estimate_new.utils.CustomToast;
import com.leon.estimate_new.utils.images.PrepareOutputImage;
import com.leon.estimate_new.utils.images.PrepareOutputPrivilege;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FinalReportActivity extends AppCompatActivity {
    private ActivityFinalReportBinding binding;
    private ExaminerDuties examinerDuty;
    private int pageNumber = 0;
    private int maxNumber = 2;
    private List<String[]> licenceRows;
    private final ArrayList<Bitmap> bitmaps = new ArrayList<>();
    private boolean licence = true/*false*/;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFinalReportBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setActivityComponent(this);
        initialize();
    }

    private void initialize() {
        if (getIntent().getExtras() != null) {
            examinerDuty = getApplicationComponent().MyDatabase().examinerDutiesDao()
                    .examinerDutiesByTrackNumber(getIntent().getExtras().getString(TRACK_NUMBER.getValue()));
        }
        examinerDuty.requestDictionary = new ArrayList<>(Arrays.asList(new GsonBuilder().create()
                .fromJson(examinerDuty.requestDictionaryString, RequestDictionary[].class)));
        for (int i = 0; i < examinerDuty.requestDictionary.size() && !licence; i++) {
            if (examinerDuty.requestDictionary.get(i).title.equals("آب") ||
                    examinerDuty.requestDictionary.get(i).title.equals("فاضلاب")) {
                licence = true;
                maxNumber++;
            }
        }
        createOutputImage();
//        new PrepareOutputPrivilege(this, examinerDuty, this).execute(this);
////        new PrepareOutputImage(this, this).execute(this);
        setOnAcceptedButtonClickListener();
        initializeArrowButton();
    }


    private void initializeArrowButton() {
        binding.imageButtonPrevious.setVisibility(View.GONE);
        binding.imageButtonNext.setOnClickListener(v -> {
            binding.imageButtonPrevious.setVisibility(View.VISIBLE);
            pageNumber++;
            if (pageNumber + 1 == maxNumber)
                binding.imageButtonNext.setVisibility(View.GONE);
            binding.imageViewPdf.setImageBitmap(bitmaps.get(pageNumber));
        });
        binding.imageButtonPrevious.setOnClickListener(v -> {
            binding.imageButtonNext.setVisibility(View.VISIBLE);
            pageNumber--;
            if (pageNumber == 0)
                binding.imageButtonPrevious.setVisibility(View.GONE);
            binding.imageViewPdf.setImageBitmap(bitmaps.get(pageNumber));
        });
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
        final Bitmap bitmap1 = binding.signatureView1.getSignatureBitmap();
        final Bitmap bitmap2 = binding.signatureView2.getSignatureBitmap();
        binding.signatureView1.setVisibility(View.GONE);
        binding.signatureView2.setVisibility(View.GONE);
        createOutputImage(bitmap1, bitmap2);
    }

    private void createOutputImage(Bitmap... tempBitmaps) {
        bitmaps.clear();
        try {
            if (tempBitmaps != null && tempBitmaps.length > 0) {
                new PrepareOutputImage(this, this, tempBitmaps[0], tempBitmaps[1]).execute(this);
                if (licence)
                    new PrepareOutputPrivilege(this, examinerDuty, licenceRows, this,
                            tempBitmaps[0]).execute(this);
            } else {
                new PrepareOutputImage(this, this).execute(this);
                if (licence)
                    new PrepareOutputPrivilege(this, examinerDuty, this).execute(this);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setLicenceImageView(Bitmap bitmap, Object... objects) {
        bitmaps.add(bitmap);
        licenceRows = (List<String[]>) objects[0];
        runOnUiThread(() -> binding.imageViewPdf.setImageBitmap(bitmap));
    }

    public void setFormImageView(Bitmap[] bitmap) {
        bitmaps.addAll(Arrays.asList(bitmap));
        runOnUiThread(() -> binding.imageViewPdf.setImageBitmap(bitmap[0]));
    }
}