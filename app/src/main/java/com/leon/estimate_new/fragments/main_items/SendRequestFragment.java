package com.leon.estimate_new.fragments.main_items;

import static com.leon.estimate_new.utils.Validator.billIdValidation;
import static com.leon.estimate_new.utils.Validator.checkEmpty;
import static com.leon.estimate_new.utils.Validator.mobileValidation;
import static com.leon.estimate_new.utils.Validator.nationalIdValidation;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.leon.estimate_new.R;
import com.leon.estimate_new.databinding.FragmentSendRequestBinding;
import com.leon.estimate_new.tables.Request;
import com.leon.estimate_new.tables.RequestToSend;
import com.leon.estimate_new.utils.request.SendRequest;

import org.jetbrains.annotations.NotNull;

public class SendRequestFragment extends Fragment implements View.OnClickListener {
    private FragmentSendRequestBinding binding;
    private Request request = new Request(true);
    private long lastClickTime = 0;

    public SendRequestFragment() {
    }

    public static SendRequestFragment newInstance() {
        return new SendRequestFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSendRequestBinding.inflate(inflater, container, false);
        binding.setRequest(request);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initialize();
    }

    private void initialize() {
        binding.buttonSendRequest.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (SystemClock.elapsedRealtime() - lastClickTime < 1000) return;
        lastClickTime = SystemClock.elapsedRealtime();
        final int id = v.getId();
        if (id == R.id.button_send_request) {
            if (billIdValidation(binding.editTextBillId, requireContext()) &&
                    mobileValidation(binding.editTextMobile, requireContext())) {
                if (request.isNewRequest()) {
                    if (checkEmpty(binding.editTextAddress, requireContext()) &&
                            checkEmpty(binding.editTextName, requireContext()) &&
                            checkEmpty(binding.editTextFamily, requireContext()) &&
                            nationalIdValidation(binding.editTextNationalId, requireContext())) {
                        new SendRequest(requireContext(), new RequestToSend(request.getSelectedServices(),
                                request.getBillId(), request.getMobile(), request.getFirstName(), request.getSureName(),
                                request.getNationalId(), request.getAddress()), this).execute(requireActivity());
                    }
                } else {
                    new SendRequest(requireContext(), new RequestToSend(request.getSelectedServices(),
                            request.getBillId(), request.getMobile()), this).execute(requireActivity());
                }

            }
        }
    }

    public void afterRequest() {
        request = new Request(request.isNewRequest());
        binding.setRequest(request);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}