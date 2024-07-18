package com.leon.estimate_new.fragments.documents;

import static com.leon.estimate_new.utils.ImageUtils.brightnessController;
import static com.leon.estimate_new.utils.ImageUtils.contrastController;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.leon.estimate_new.R;
import com.leon.estimate_new.databinding.FragmentBrightnessContrastBinding;

public class BrightnessContrastFragment extends Fragment implements SeekBar.OnSeekBarChangeListener,
        View.OnClickListener {
    private FragmentBrightnessContrastBinding binding;
    private Callback documentActivity;
    private Bitmap bitmapTemp;

    public static BrightnessContrastFragment newInstance() {
        return new BrightnessContrastFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentBrightnessContrastBinding.inflate(inflater, container, false);
        initialize();
        return binding.getRoot();
    }

    private void initialize() {
        binding.seekBarBrightness.setMax(300);
        binding.seekBarBrightness.setProgress(150);
        binding.seekBarBrightness.setOnSeekBarChangeListener(this);
        binding.seekBarContrast.setMax(100);
        binding.seekBarContrast.setProgress(50);
        binding.seekBarContrast.setOnSeekBarChangeListener(this);
        binding.buttonAccepted.setOnClickListener(this);
        binding.buttonClose.setOnClickListener(this);
        bitmapTemp = documentActivity.getBitmap();
        binding.imageView.setImageBitmap(bitmapTemp);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id ==R.id.button_accepted){
            documentActivity.setFinalBitmap(bitmapTemp);
        } else if (id ==R.id.button_close) {
            documentActivity.cancelEditing();
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        final int id = seekBar.getId();
        if (id == R.id.seekBar_contrast) {
            final float contrast = (float) (progress) / 10;
            bitmapTemp = contrastController(bitmapTemp, contrast,
                    binding.seekBarBrightness.getProgress() - 250);
            binding.imageView.setImageBitmap(bitmapTemp);
            binding.textViewContrast.setText(getString(R.string.contrast).concat(String.valueOf(contrast)));
        } else if (id == R.id.seekBar_brightness) {
            final int brightness = progress - 150;
            bitmapTemp = brightnessController(bitmapTemp, brightness);
            binding.imageView.setImageBitmap(bitmapTemp);
            binding.textViewBrightness.setText(getString(R.string.brightness).concat(String.valueOf(brightness)));
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof Activity) {
            documentActivity = (Callback) context;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


    public interface Callback {
        Bitmap getBitmap();

        void setTempBitmap(Bitmap tempBitmap);

        void setFinalBitmap(Bitmap finalBitmap);

        void cancelEditing();
    }
}