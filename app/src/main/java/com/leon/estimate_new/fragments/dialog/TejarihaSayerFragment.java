package com.leon.estimate_new.fragments.dialog;

import static com.leon.estimate_new.enums.DialogType.Yellow;

import android.annotation.SuppressLint;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.leon.estimate_new.R;
import com.leon.estimate_new.adapters.SpinnerCustomAdapter;
import com.leon.estimate_new.adapters.TejariSayerAdapter;
import com.leon.estimate_new.databinding.FragmentTejarihaSayerBinding;
import com.leon.estimate_new.di.view_model.CustomDialogModel;
import com.leon.estimate_new.fragments.forms.BaseInfoFragment;
import com.leon.estimate_new.tables.ExaminerDuties;
import com.leon.estimate_new.tables.KarbariDictionary;
import com.leon.estimate_new.tables.Tejariha;

import java.util.ArrayList;

public class TejarihaSayerFragment extends DialogFragment {
    private FragmentTejarihaSayerBinding binding;
    private final Callback baseInfoFragment;
    private TejariSayerAdapter adapter;
    private final ArrayList<Tejariha> tejariha = new ArrayList<>();


    public TejarihaSayerFragment(final BaseInfoFragment baseInfoFragment) {
        this.baseInfoFragment = baseInfoFragment;
        tejariha.addAll(this.baseInfoFragment.getTejariha());
    }

    public static TejarihaSayerFragment newInstance(final BaseInfoFragment baseInfoFragment) {
        return new TejarihaSayerFragment(baseInfoFragment);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentTejarihaSayerBinding.inflate(inflater, container, false);
        initialize();
        return binding.getRoot();
    }

    private void initialize() {
        setOnImageViewPlusClickListener();
        initializeKarbariSpinner();
        initializeRecyclerView();
    }

    private void initializeRecyclerView() {
        adapter = new TejariSayerAdapter(tejariha);
        binding.recyclerViewTejariha.setAdapter(adapter);
        binding.recyclerViewTejariha.setLayoutManager(new LinearLayoutManager(getActivity()) {
            @Override
            public boolean requestChildRectangleOnScreen(@NonNull RecyclerView parent,
                                                         @NonNull View child,
                                                         @NonNull Rect rect, boolean immediate) {
                return false;
            }
        });
    }

    @SuppressLint("NotifyDataSetChanged")
    private void setOnImageViewPlusClickListener() {
        binding.imageViewPlus.setOnClickListener(v -> {
            if (checkIsNoEmpty(binding.editTextVahed) && checkIsNoEmpty(binding.editTextNoeShoql)
                    && checkIsNoEmpty(binding.editTextVahedMohasebe)
                    && checkIsNoEmpty(binding.editTextA2)) {
                if (tejariha.size() == 8) {
                    new CustomDialogModel(Yellow, requireContext(),
                            getString(R.string.tejari_over_flow),
                            getString(R.string.dear_user),
                            getString(R.string.tejari),
                            getString(R.string.accepted));
                    return;
                }
                final String karbari = baseInfoFragment.getKarbariDictionary().get(
                        binding.spinner1.getSelectedItemPosition()).title;
                final String noeShoql = binding.editTextNoeShoql.getText().toString();
                final int tedadVahed = Integer.parseInt(binding.editTextVahed.getText().toString());
                final String vahedMohasebe = binding.editTextVahedMohasebe.getText().toString();
                final String a = binding.editTextA2.getText().toString();
                tejariha.add(new Tejariha(karbari, noeShoql, tedadVahed, vahedMohasebe, a,
                        baseInfoFragment.getExaminerDuty().trackNumber));
                adapter.notifyDataSetChanged();
                binding.editTextA2.setText("");
                binding.editTextNoeShoql.setText("");
                binding.editTextVahed.setText("");
                binding.editTextVahedMohasebe.setText("");
            }
        });
    }

    private boolean checkIsNoEmpty(EditText editText) {
        View focusView;
        if (editText.getText().toString().length() < 1) {
            editText.setError(getString(R.string.error_empty));
            focusView = editText;
            focusView.requestFocus();
            return false;
        }
        return true;
    }

    private void initializeKarbariSpinner() {
        final ArrayList<String> arrayListSpinner = new ArrayList<>();
        int selected = 0, counter = 0;
        for (KarbariDictionary karbariDictionary : baseInfoFragment.getKarbariDictionary()) {
            arrayListSpinner.add(karbariDictionary.title);
            if (karbariDictionary.id == baseInfoFragment.getExaminerDuty().karbariId) {
                selected = counter;
            }
            counter = counter + 1;
        }
        binding.spinner1.setAdapter(new SpinnerCustomAdapter(requireContext(), arrayListSpinner));
        binding.spinner1.setSelection(selected);
    }

    @Override
    public void onResume() {
        if (getDialog() != null) {
            WindowManager.LayoutParams params = getDialog().getWindow().getAttributes();
            params.width = ViewGroup.LayoutParams.WRAP_CONTENT;
            params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
            getDialog().getWindow().setAttributes(params);
        }
        super.onResume();
    }

    public interface Callback {
        ArrayList<KarbariDictionary> getKarbariDictionary();

        ArrayList<Tejariha> getTejariha();

        ExaminerDuties getExaminerDuty();
    }
}