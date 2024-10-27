package com.leon.estimate_new.fragments.dialog;

import android.graphics.Bitmap;
import android.graphics.pdf.PdfRenderer;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.RawRes;
import androidx.fragment.app.DialogFragment;

import com.leon.estimate_new.BuildConfig;
import com.leon.estimate_new.R;
import com.leon.estimate_new.databinding.FragmentValueHelpBinding;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ValueHelpFragment extends DialogFragment {
    private FragmentValueHelpBinding binding;
    private final int maxNumber = 9;
    private int pageNumber = 0;
    public ValueHelpFragment() {
    }

    public static ValueHelpFragment newInstance() {
        return new ValueHelpFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding  = FragmentValueHelpBinding.inflate(getLayoutInflater());
        initialize();
        return binding.getRoot();    }

    private void initialize() {
        try {
            openPdfFromRaw();
            binding.imageButtonPrevious.setVisibility(View.GONE);
            binding.imageButtonNext.setVisibility(View.VISIBLE);
        } catch (IOException e) {
            e.printStackTrace();
        }
        setOnArrowButtonClickListener();
    }


    private void setOnArrowButtonClickListener() {
        binding.imageButtonNext.setOnClickListener(v -> {
            binding.imageButtonPrevious.setVisibility(View.VISIBLE);
            pageNumber++;
            if (pageNumber + 1 == maxNumber)
                binding.imageButtonNext.setVisibility(View.GONE);
            try {
                openPdfFromRaw();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        binding.imageButtonPrevious.setOnClickListener(v -> {
            pageNumber--;
            if (pageNumber == 0)
                binding.imageButtonPrevious.setVisibility(View.GONE);
            try {
                openPdfFromRaw();
                binding.imageButtonNext.setVisibility(View.VISIBLE);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void openPdfFromRaw() throws IOException {
        final File fileCopy = new File(requireContext().getCacheDir(), "HELP_VALUE_" + BuildConfig.VERSION_CODE + "_PAGE_".concat(String.valueOf(pageNumber)));
        copyToCache(fileCopy, R.raw.value);
        final ParcelFileDescriptor fileDescriptor =
                ParcelFileDescriptor.open(fileCopy,
                        ParcelFileDescriptor.MODE_READ_ONLY);
        final PdfRenderer mPdfRenderer = new PdfRenderer(fileDescriptor);
        PdfRenderer.Page mPdfPage = mPdfRenderer.openPage(pageNumber);
        final Bitmap bitmap = Bitmap.createBitmap(mPdfPage.getWidth(), mPdfPage.getHeight(),
                Bitmap.Config.ARGB_8888);
        mPdfPage.render(bitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY);
        binding.imageView.setImageBitmap(bitmap);
    }

    private void copyToCache(File file, @RawRes int pdfResource) throws IOException {
        if (!file.exists()) {
            final InputStream input = getResources().openRawResource(pdfResource);
            final FileOutputStream output = new FileOutputStream(file);
            final byte[] buffer = new byte[1024];
            int size;
            while ((size = input.read(buffer)) != -1) {
                output.write(buffer, 0, size);
            }
            input.close();
            output.close();
        }
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onResume() {
        if (getDialog() != null && getDialog().getWindow() != null) {
            WindowManager.LayoutParams params = getDialog().getWindow().getAttributes();
            params.width = ViewGroup.LayoutParams.MATCH_PARENT;
            params.height = ViewGroup.LayoutParams.MATCH_PARENT;
            getDialog().getWindow().setAttributes(params);
        }
        super.onResume();
    }
}