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
import com.leon.estimate_new.adapters.SpinnerCustomAdapter;
import com.leon.estimate_new.databinding.ActivityFinalReportBinding;
import com.leon.estimate_new.tables.ExaminerDuties;
import com.leon.estimate_new.tables.RequestDictionary;
import com.leon.estimate_new.tables.ResultDictionary;
import com.leon.estimate_new.utils.CustomToast;
import com.leon.estimate_new.utils.document.PrepareOutputImage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FinalReportActivity extends AppCompatActivity {
    private ActivityFinalReportBinding binding;
    private ExaminerDuties examinerDuty;
    private int pageNumber = 0, maxNumber = 2;
    private boolean licence = false;
    private List<String[]> licenceRows;
    private final ArrayList<Bitmap> bitmaps = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFinalReportBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setActivityComponent(this);
        if (getIntent().getExtras() != null)
            examinerDuty = getApplicationComponent().MyDatabase().examinerDutiesDao()
                    .examinerDutiesByTrackNumber(getIntent().getExtras().getString(TRACK_NUMBER.getValue()));
        initialize();
    }

    private void initialize() {
        examinerDuty.requestDictionary = new ArrayList<>(Arrays.asList(new GsonBuilder().create()
                .fromJson(examinerDuty.requestDictionaryString, RequestDictionary[].class)));
        for (int i = 0; i < examinerDuty.requestDictionary.size() && !licence; i++)
            if (examinerDuty.requestDictionary.get(i).title.equals("آب") ||
                    examinerDuty.requestDictionary.get(i).title.equals("فاضلاب")) {
                licence = true;
                maxNumber++;
            }
        createOutputImage();
        setOnAcceptedButtonClickListener();
        initializeArrowButton();
        initializeSpinner();
        binding.imageViewRefresh1.setOnClickListener(v -> binding.signatureView1.clearCanvas());
        binding.imageViewRefresh2.setOnClickListener(v -> binding.signatureView2.clearCanvas());
    }

    private void initializeSpinner() {
        final ArrayList<String> items = new ArrayList<>();
        final ArrayList<ResultDictionary> resultDictionaries =
                new ArrayList<>(getApplicationComponent().MyDatabase().resultDictionaryDao().getResults());
        for (ResultDictionary resultDictionary : resultDictionaries)
            items.add(resultDictionary.title);
        binding.spinner.setAdapter(new SpinnerCustomAdapter(getApplicationContext(), items));
    }

    private void initializeArrowButton() {
        binding.imageButtonPrevious.setVisibility(View.GONE);
        binding.imageButtonNext.setOnClickListener(v -> {
            binding.imageButtonPrevious.setVisibility(View.VISIBLE);
            pageNumber++;
            if (pageNumber + 1 == maxNumber) binding.imageButtonNext.setVisibility(View.GONE);
            binding.imageViewPdf.setImageBitmap(bitmaps.get(pageNumber));
        });
        binding.imageButtonPrevious.setOnClickListener(v -> {
            binding.imageButtonNext.setVisibility(View.VISIBLE);
            pageNumber--;
            if (pageNumber == 0) binding.imageButtonPrevious.setVisibility(View.GONE);
            binding.imageViewPdf.setImageBitmap(bitmaps.get(pageNumber));
        });
    }

    private void setOnAcceptedButtonClickListener() {
        binding.buttonAccepted.setOnClickListener(v -> {
            if (binding.signatureView1.isBitmapEmpty() || binding.signatureView2.isBitmapEmpty())
                new CustomToast().warning(getString(R.string.request_sign), Toast.LENGTH_LONG);
            else addImageSign();
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
                new PrepareOutputImage(this, examinerDuty, licence, licenceRows, this,
                        tempBitmaps[0], tempBitmaps[1]).execute(this);
            } else {
                new PrepareOutputImage(this, examinerDuty, licence, this).execute(this);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setLicenceImageView(Bitmap bitmap, Object... objects) {
        bitmaps.add(2, bitmap);
        licenceRows = (List<String[]>) objects[0];
    }

    public void setFormImageView(Bitmap[] bitmap) {
        bitmaps.addAll(Arrays.asList(bitmap));
        pageNumber = 0;
        runOnUiThread(() -> {
            binding.imageViewPdf.setImageBitmap(bitmap[0]);
            binding.imageButtonPrevious.setVisibility(View.GONE);
            binding.imageButtonNext.setVisibility(View.VISIBLE);
        });
    }
}