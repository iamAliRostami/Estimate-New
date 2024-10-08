package com.leon.estimate_new.fragments.main_items;

import static com.leon.estimate_new.helpers.MyApplication.getApplicationComponent;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.leon.estimate_new.R;
import com.leon.estimate_new.databinding.FragmentUploadBinding;
import com.leon.estimate_new.utils.uploading.UploadNavigated;

public class UploadFragment extends Fragment implements View.OnClickListener {
    private FragmentUploadBinding binding;
    private long lastClickTime = 0;

    public UploadFragment() {
    }

    public static UploadFragment newInstance() {
        return new UploadFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentUploadBinding.inflate(inflater, container, false);
        initialize();
        return binding.getRoot();
    }

    private void initialize() {
        binding.buttonUpload.setOnClickListener(this);
        binding.textViewImage.setText(String.valueOf(getApplicationComponent().MyDatabase().imagesDao().getUnsentImage()));
        binding.textViewEshterak.setText(String.valueOf(getApplicationComponent().MyDatabase().calculationUserInputDao().getCalculationUserInputUnsent()));
    }

    @Override
    public void onClick(View view) {
        if (SystemClock.elapsedRealtime() - lastClickTime < 1000) return;
        lastClickTime = SystemClock.elapsedRealtime();
        final int id = view.getId();
        if (id == R.id.button_upload) {
            new UploadNavigated(requireContext(), binding.buttonUpload).execute(requireActivity());
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}