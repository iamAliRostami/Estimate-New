package com.leon.estimate_new.fragments.dialog;

import static com.leon.estimate_new.helpers.MyApplication.getApplicationComponent;
import static com.leon.estimate_new.utils.Validator.checkEmpty;

import android.annotation.SuppressLint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.leon.estimate_new.R;
import com.leon.estimate_new.adapters.TejariSayerAdapter;
import com.leon.estimate_new.databinding.FragmentTejarihaSayerBinding;
import com.leon.estimate_new.fragments.forms.BaseInfoFragment;
import com.leon.estimate_new.tables.ExaminerDuties;
import com.leon.estimate_new.tables.KarbariDictionary;
import com.leon.estimate_new.tables.Tejariha;
import com.leon.estimate_new.utils.CustomToast;
import com.leon.estimate_new.utils.mapper.CustomMapper;

import java.util.ArrayList;

public class TejarihaSayerFragment extends DialogFragment implements View.OnClickListener {
    private final Callback baseInfoFragment;
    private final ArrayList<Tejariha> tejariha = new ArrayList<>();
    private TejariSayerAdapter adapter;
    private FragmentTejarihaSayerBinding binding;
    private TejarihaSayerViewModel tejarihaSayerVM;
    private final ArrayList<String> karbariTitles = new ArrayList<>();

    public TejarihaSayerFragment(final BaseInfoFragment baseInfoFragment) {
        this.baseInfoFragment = baseInfoFragment;
        tejariha.addAll(this.baseInfoFragment.getTejariha());
    }

    public static TejarihaSayerFragment newInstance(final BaseInfoFragment baseInfoFragment) {
        TejarihaSayerFragment fragment = new TejarihaSayerFragment(baseInfoFragment);
        fragment.setCancelable(false);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentTejarihaSayerBinding.inflate(inflater, container, false);
        tejarihaSayerVM = new TejarihaSayerViewModel(baseInfoFragment.getExaminerDuty().trackNumber,
                baseInfoFragment.getExaminerDuty().karbariS);
        binding.setTejarihaSayerVM(tejarihaSayerVM);
        initialize();
        return binding.getRoot();
    }

    private void initialize() {
        binding.imageViewPlus.setOnClickListener(this);
        binding.buttonSubmit.setOnClickListener(this);
        binding.textViewKarbari.setOnClickListener(this);
        initializeKarbari();
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
    private void addItem() {
        //TODO
        CustomMapper.INSTANCE.tejarihaToTejarihaViewModel(tejarihaSayerVM);
        tejariha.add(CustomMapper.INSTANCE.tejarihaToTejarihaViewModel(tejarihaSayerVM)
                /*new Tejariha(baseInfoFragment.getKarbariDictionary().get(binding.spinner1.getSelectedItemPosition()).title,
                binding.editTextNoeShoql.getText().toString(),
                Integer.parseInt(binding.editTextVahed.getText().toString()),
                binding.editTextVahedMohasebe.getText().toString(),
                binding.editTextA2.getText().toString(),
                Integer.parseInt(binding.editTextCapacity.getText().toString()),
                baseInfoFragment.getExaminerDuty().trackNumber)*/
        );
        adapter.notifyDataSetChanged();
    }

    private void initializeKarbari() {
        ArrayList<KarbariDictionary> dictionary = baseInfoFragment.getKarbariDictionary();
        for (int i = 0, dictionarySize = dictionary.size(); i < dictionarySize; i++) {
            KarbariDictionary karbariDictionary = dictionary.get(i);
            karbariTitles.add(karbariDictionary.title);
        }
//        binding.spinner1.setAdapter(new SpinnerCustomAdapter(requireContext(), karbariTitles));
//        binding.spinner1.setSelection(selected);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.image_view_plus) {
            if (checkEmpty(binding.editTextNoeShoql, requireContext()) &&
                    checkEmpty(binding.editTextCapacity, requireContext()) &&
                    checkEmpty(binding.editTextVahed, requireContext()) &&
                    checkEmpty(binding.editTextA2, requireContext()) &&
                    checkEmpty(binding.editTextVahedMohasebe, requireContext())) {
                if (tejariha.size() == 8) {
                    new CustomToast().warning(getString(R.string.tejari_over_flow), Toast.LENGTH_LONG);
                    return;
                }
                addItem();
                tejarihaSayerVM = new TejarihaSayerViewModel();
                binding.setTejarihaSayerVM(tejarihaSayerVM);
            }
        } else if (id == R.id.button_submit) {
            getApplicationComponent().MyDatabase().tejarihaDao().delete();
            getApplicationComponent().MyDatabase().tejarihaDao().insertTejariha(adapter.getTejarihas());
            baseInfoFragment.setTejariha(adapter.getTejarihas());
            dismiss();
        } else if (id == R.id.text_view_karbari) {
            showMenu(binding.textViewKarbari, karbariTitles);
        }
    }

    private void showMenu(MaterialAutoCompleteTextView editText, ArrayList<String> titles) {
        final PopupMenu popup = new PopupMenu(requireActivity(), editText, Gravity.TOP);
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

    @Override
    public void onResume() {
        if (getDialog() != null && getDialog().getWindow() != null) {
            WindowManager.LayoutParams params = getDialog().getWindow().getAttributes();
            params.width = ViewGroup.LayoutParams.MATCH_PARENT;
            params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
            getDialog().getWindow().setAttributes(params);
        }
        super.onResume();
    }

    public interface Callback {
        ArrayList<KarbariDictionary> getKarbariDictionary();

        ArrayList<Tejariha> getTejariha();

        void setTejariha(ArrayList<Tejariha> tejarihas);

        ExaminerDuties getExaminerDuty();
    }
}