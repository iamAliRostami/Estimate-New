package com.leon.estimate_new.activities;

import static com.leon.estimate_new.enums.BundleEnum.LICENCE_TITLE;
import static com.leon.estimate_new.enums.BundleEnum.OTHER_TITLE;
import static com.leon.estimate_new.enums.BundleEnum.TITLE;
import static com.leon.estimate_new.enums.BundleEnum.TRACK_NUMBER;
import static com.leon.estimate_new.helpers.MyApplication.getApplicationComponent;
import static com.leon.estimate_new.helpers.MyApplication.setActivityComponent;
import static com.leon.estimate_new.utils.Validator.checkEmpty;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;

import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.google.gson.GsonBuilder;
import com.leon.estimate_new.R;
import com.leon.estimate_new.databinding.ActivityFinalReportBinding;
import com.leon.estimate_new.tables.ExaminerDuties;
import com.leon.estimate_new.tables.RequestDictionary;
import com.leon.estimate_new.tables.ResultDictionary;
import com.leon.estimate_new.utils.CustomToast;
import com.leon.estimate_new.utils.document.PrepareOutputImage;
import com.leon.estimate_new.utils.estimating.UploadImages;
import com.leon.estimate_new.utils.estimating.UploadNavigated;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class FinalReportActivity extends AppCompatActivity implements View.OnClickListener {
    private final ArrayList<ResultDictionary> resultDictionaries = new ArrayList<>();
    private final ArrayList<Bitmap> bitmaps = new ArrayList<>();
    private final ArrayList<String> resultTitle = new ArrayList<>();
    private final HashMap<String, Integer> resultMap = new HashMap<>();
    private ActivityFinalReportBinding binding;
    private long lastClickTime = 0;
    private ExaminerDuties examinerDuty;
    private boolean licence = false, finalSubmit = false, sent = true;
    private int pageNumber = 0, maxNumber = 2, imageNumber = 0, licenceTitle, estimateTitle, crookiTitle;
    private List<String[]> licenceRows;

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        binding = ActivityFinalReportBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setActivityComponent(this);
        if (getIntent().getExtras() != null) {
            examinerDuty = getApplicationComponent().MyDatabase().examinerDutiesDao()
                    .examinerDutiesByTrackNumber(getIntent().getExtras().getString(TRACK_NUMBER.getValue()));
            crookiTitle = getIntent().getExtras().getInt(OTHER_TITLE.getValue());
            licenceTitle = getIntent().getExtras().getInt(LICENCE_TITLE.getValue());
            estimateTitle = getIntent().getExtras().getInt(TITLE.getValue());
        }
        initialize();
    }

    private void initialize() {
        examinerDuty.requestDictionary = new ArrayList<>(Arrays.asList(new GsonBuilder().create()
                .fromJson(examinerDuty.requestDictionaryString, RequestDictionary[].class)));
        for (int i = 0; i < examinerDuty.requestDictionary.size() && !licence; i++) {
            if ((examinerDuty.requestDictionary.get(i).title.trim().equals("انشعاب آب") ||
                    examinerDuty.requestDictionary.get(i).title.trim().equals("انشعاب فاضلاب")) &
                    examinerDuty.requestDictionary.get(i).isSelected) {
                examinerDuty.operation = examinerDuty.requestDictionary.get(i).title;
                licence = true;
                maxNumber++;
            }
        }
        createOutputImage();
        initializeArrays();
        setOnClickListener();
        binding.imageButtonPrevious.setVisibility(View.GONE);
    }


    private void initializeArrays() {
        resultDictionaries.addAll(getApplicationComponent().MyDatabase().resultDictionaryDao().getResults());
        for (int i = 0; i < resultDictionaries.size(); i++) {
            ResultDictionary resultDictionary = resultDictionaries.get(i);
            resultTitle.add(resultDictionary.title);
            resultMap.put(resultDictionary.title, resultDictionary.id);
        }
    }

    private void setOnClickListener() {
        binding.imageViewRefresh1.setOnClickListener(this);
        binding.imageViewRefresh2.setOnClickListener(this);
        binding.imageButtonNext.setOnClickListener(this);
        binding.imageButtonPrevious.setOnClickListener(this);
        binding.buttonAccepted.setOnClickListener(this);
        binding.buttonDenial.setOnClickListener(this);
        binding.textViewResult.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (SystemClock.elapsedRealtime() - lastClickTime < 1000) return;
        lastClickTime = SystemClock.elapsedRealtime();
        final int id = v.getId();
        if (id == R.id.button_denial) {
            finish();
        } else if (id == R.id.image_view_refresh_1) {
            binding.signatureView1.clearCanvas();
        } else if (id == R.id.image_view_refresh_2) {
            binding.signatureView2.clearCanvas();
        } else if (id == R.id.image_button_next) {
            binding.imageButtonPrevious.setVisibility(View.VISIBLE);
            pageNumber++;
            if (pageNumber + 1 == maxNumber) binding.imageButtonNext.setVisibility(View.GONE);
            binding.imageViewOutput.setImageBitmap(bitmaps.get(pageNumber));
        } else if (id == R.id.image_button_previous) {
            binding.imageButtonNext.setVisibility(View.VISIBLE);
            pageNumber--;
            if (pageNumber == 0) binding.imageButtonPrevious.setVisibility(View.GONE);
            binding.imageViewOutput.setImageBitmap(bitmaps.get(pageNumber));
        } else if (id == R.id.button_accepted) {
            if (binding.signatureView1.isBitmapEmpty() || binding.signatureView2.isBitmapEmpty())
                new CustomToast().warning(getString(R.string.request_sign), Toast.LENGTH_LONG);
            else if (checkEmpty(binding.textViewResult, this)) addImageSign();
        } else if (id == R.id.text_view_result) {
            showMenu(binding.textViewResult, resultTitle);
        }
    }

    private void showMenu(MaterialAutoCompleteTextView editText, ArrayList<String> titles) {
        final PopupMenu popup = new PopupMenu(this, editText, Gravity.TOP);
        for (int i = 0; i < titles.size(); i++) {
            MenuItem item = popup.getMenu().add(titles.get(i));
            if (item.getIcon() != null) {
                Drawable icon = item.getIcon();
                int iconMarginPx = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                        R.dimen.small_dp, getResources().getDisplayMetrics());
                InsetDrawable insetDrawable = new InsetDrawable(icon, iconMarginPx, 0, iconMarginPx, 0);
                item.setIcon(insetDrawable);
            }
        }
        popup.setOnMenuItemClickListener(menuItem -> {
            editText.setText(menuItem.getTitle());
            return true;
        });
        popup.show();
    }

    private void addImageSign() {
        final Bitmap bitmap1 = binding.signatureView1.getSignatureBitmap();
        final Bitmap bitmap2 = binding.signatureView2.getSignatureBitmap();
        binding.signatureView1.setVisibility(View.GONE);
        binding.signatureView2.setVisibility(View.GONE);
        finalSubmit = true;
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

    public void setFormImageView(Bitmap[] bitmap, Object... objects) {
        bitmaps.addAll(Arrays.asList(bitmap));
        pageNumber = 0;
        runOnUiThread(() -> {
            binding.imageViewOutput.setImageBitmap(bitmap[0]);
            binding.imageButtonPrevious.setVisibility(View.GONE);
            binding.imageButtonNext.setVisibility(View.VISIBLE);
        });
        if (licence) {
            bitmaps.add(2, (Bitmap) objects[0]);
            licenceRows = (List<String[]>) objects[1];
        }
        //TODO
        if (finalSubmit) sendImages();
    }

    private void finalSubmit() {
        Integer result = resultMap.get(binding.textViewResult.getText().toString());
        if (result != null) {
            if (sent)
                new UploadNavigated(examinerDuty, result, this).execute(this);
            else
                getApplicationComponent().MyDatabase().examinerDutiesDao()
                        .updateExaminationByPeymayesh(true, examinerDuty.trackNumber);
        }
        finish();
    }

    public void sendImages() {
        if (imageNumber == bitmaps.size())
            finalSubmit();
        else {
            int id = switch (imageNumber) {
                case 2 -> crookiTitle;
                case 3 -> licenceTitle;
                default -> estimateTitle;
            };
            new UploadImages(id, examinerDuty.trackNumber, examinerDuty.billId != null ?
                    examinerDuty.billId : examinerDuty.neighbourBillId, examinerDuty.isNewEnsheab,
                    this).execute(this);
        }
        setImageNumber();
    }

    private void setImageNumber() {
        imageNumber++;
    }

    public Bitmap getBitmap() {
        return bitmaps.get(imageNumber - 1);
    }

    public void setSent(boolean b) {
        sent = b;
    }
}