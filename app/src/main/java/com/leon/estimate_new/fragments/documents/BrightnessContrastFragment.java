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
    private boolean contrastOrBrightness;
    private Bitmap bitmapTemp;
    private float contrast = 1.7F;
    private int brightness;

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
        binding.seekBarContrast.setProgress(17);
        binding.seekBarContrast.setOnSeekBarChangeListener(this);

        binding.buttonAccepted.setOnClickListener(this);
        binding.buttonClose.setOnClickListener(this);
        bitmapTemp = documentActivity.getBitmap();
        binding.imageView.setImageBitmap(bitmapTemp);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        final int id = seekBar.getId();
        if (id == R.id.seekBar_contrast) {
            contrastOrBrightness = true;
            contrast = (float) (progress) / 10;
            binding.imageView.setImageBitmap(contrastController(brightnessController(bitmapTemp,
                    brightness), contrast, brightness));
            binding.textViewContrast.setText(getString(R.string.contrast).concat(String.valueOf(contrast)));
        } else if (id == R.id.seekBar_brightness) {
            contrastOrBrightness = false;
            brightness = progress - 150;
//            binding.imageView.setImageBitmap(brightnessController(bitmapTemp, brightness));
            binding.imageView.setImageBitmap(brightnessController(contrastController(bitmapTemp,
                    contrast, brightness), brightness));
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
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.button_accepted) {
            if (contrastOrBrightness && contrast != 1.7F)
                documentActivity.setFinalBitmap(contrastController(brightnessController(bitmapTemp,
                        brightness), contrast, brightness));
            else if (!contrastOrBrightness && brightness != 0)
                documentActivity.setFinalBitmap(brightnessController(contrastController(bitmapTemp,
                        contrast, brightness), brightness));
            else documentActivity.setFinalBitmap(bitmapTemp);
        } else if (id == R.id.button_close) {
            documentActivity.cancelEditing();
        }
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

        void setFinalBitmap(Bitmap finalBitmap);

        void cancelEditing();
    }
}