package com.leon.estimate_new.fragments.forms;

import static com.leon.estimate_new.helpers.Constants.BASE_FRAGMENT;
import static com.leon.estimate_new.utils.Validator.checkEmpty;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.PopupMenu;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.leon.estimate_new.R;
import com.leon.estimate_new.databinding.FragmentTechnicalInfoBinding;
import com.leon.estimate_new.tables.ExaminerDuties;
import com.leon.estimate_new.utils.mapper.CustomMapper;

public class TechnicalInfoFragment extends Fragment implements View.OnClickListener {
    private FragmentTechnicalInfoBinding binding;
    private TechnicalInfoViewModel technicalInfoVM;

    private String[] qotrTitles;
    private String[] jensTitles;

    private Callback formActivity;

    public TechnicalInfoFragment() {
    }

    public static TechnicalInfoFragment newInstance() {
        return new TechnicalInfoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentTechnicalInfoBinding.inflate(inflater, container, false);
        technicalInfoVM = CustomMapper.INSTANCE.examinerDutyToTechnicalInfoViewModel(formActivity.getExaminerDuty());
        binding.setTechnicalInfoVM(technicalInfoVM);
        initialize();
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        formActivity.setTitle(getString(R.string.app_name).concat(" / ").concat("صفحه چهارم"), false);
    }

    private void initialize() {
        binding.checkboxChahAbBaran.setChecked(technicalInfoVM.isChahAbBaran());
        binding.checkboxLooleAb.setChecked(technicalInfoVM.isLooleA());
        binding.checkboxLooleFazelab.setChecked(technicalInfoVM.isLooleF());
        binding.checkboxVahedAb.setChecked(technicalInfoVM.isEzharNazarA());
        initializeArrays();
        binding.buttonPre.setOnClickListener(this);
        binding.buttonSubmit.setOnClickListener(this);
//        binding.textViewQotr.setOnClickListener(this);
//        binding.textViewJens.setOnClickListener(this);
    }

    private void initializeArrays() {
        qotrTitles = getResources().getStringArray(R.array.menu_qotr_loole);
        jensTitles = getResources().getStringArray(R.array.menu_jens_loole);
        binding.textViewJens.setSimpleItems(jensTitles);
        binding.textViewQotr.setSimpleItems(qotrTitles);
        if (technicalInfoVM.getJensLooleS() != null)
            binding.textViewJens.setText(technicalInfoVM.getJensLooleS(), false);
        if (technicalInfoVM.getQotrLooleS() != null)
            binding.textViewQotr.setText(technicalInfoVM.getQotrLooleS(), false);
    }


    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.button_pre) {
            formActivity.setOnPreClickListener(BASE_FRAGMENT);
        } else if (id == R.id.button_submit) {
            if (checkForm()) {
                formActivity.setTechnicalForm(technicalInfoVM);
            }
        } else if (id == R.id.text_view_jens) {
            showMenu(binding.textViewJens, jensTitles);
        } else if (id == R.id.text_view_qotr) {
            showMenu(binding.textViewQotr, qotrTitles);
        }
    }

    private void showMenu(MaterialAutoCompleteTextView textView, String[] titles) {
        final PopupMenu popup = new PopupMenu(requireActivity(), textView, Gravity.TOP);
        for (String title : titles) {
            MenuItem item = popup.getMenu().add(title);
            if (item.getIcon() != null) {
                Drawable icon = item.getIcon();
                int iconMarginPx = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                        R.dimen.small_dp, getResources().getDisplayMetrics());
                InsetDrawable insetDrawable = new InsetDrawable(icon, iconMarginPx, 0, iconMarginPx, 0);
                item.setIcon(insetDrawable);
            }
        }
        popup.setOnMenuItemClickListener(menuItem -> {
            textView.setText(menuItem.getTitle());
            return true;
        });
        popup.show();
    }

    private boolean checkForm() {
        return checkEmpty(binding.editTextKhaki, requireContext()) &&
                checkEmpty(binding.editTextAsphalt, requireContext()) &&
                checkEmpty(binding.editTextSang, requireContext()) &&
                checkEmpty(binding.editTextOther, requireContext()) &&
                checkEmpty(binding.editTextKhakiFazelab, requireContext()) &&
                checkEmpty(binding.editTextAsphaltFazelab, requireContext()) &&
                checkEmpty(binding.editTextSangFazelab, requireContext()) &&
                checkEmpty(binding.editTextOtherFazelab, requireContext()) &&
                checkEmpty(binding.editTextOmqZirzamin, requireContext()) &&
                checkEmpty(binding.textViewQotr, requireContext()) &&
                checkEmpty(binding.textViewJens, requireContext()) &&
                (!technicalInfoVM.isNewEnsheab() || checkEmpty(binding.editTextEshterak, requireContext()));
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof Activity) {
            formActivity = (Callback) context;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


    public interface Callback {
        void setOnPreClickListener(int position);

        void setTitle(String title, boolean showMenu);

        void setTechnicalForm(TechnicalInfoViewModel examinerDuty);

        ExaminerDuties getExaminerDuty();
    }
}