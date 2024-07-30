package com.leon.estimate_new.fragments.dialog;

import static com.leon.estimate_new.utils.Validator.checkEmpty;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.leon.estimate_new.R;
import com.leon.estimate_new.databinding.FragmentValueBinding;
import com.leon.estimate_new.fragments.forms.BaseInfoFragment;
import com.leon.estimate_new.tables.Arzeshdaraei;
import com.leon.estimate_new.tables.Block;
import com.leon.estimate_new.tables.Formula;
import com.leon.estimate_new.utils.CustomToast;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;

public class ValueFragment extends DialogFragment implements TextWatcher, View.OnClickListener {
    private final Arzeshdaraei arzeshdaraei;
    private final Callback baseInfoFragment;
    private final ArrayList<Integer> values = new ArrayList<>();
    private final ArrayList<String> blockTitles = new ArrayList<>();
    private final ArrayList<String> gozarTitles = new ArrayList<>();
    private final HashMap<String, Integer> gozarMap = new HashMap<>();
    private final HashMap<String, Integer> blockMap = new HashMap<>();
    private FragmentValueBinding binding;

    public ValueFragment(final BaseInfoFragment baseInfoFragment) {
        this.baseInfoFragment = baseInfoFragment;
        arzeshdaraei = this.baseInfoFragment.getArzeshdaraei();
        values.addAll(this.baseInfoFragment.getValue());
    }

    public static ValueFragment newInstance(final BaseInfoFragment baseInfoFragment) {
        return new ValueFragment(baseInfoFragment);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentValueBinding.inflate(inflater, container, false);
        initialize();
        return binding.getRoot();
    }

    private void initialize() {
        initializeArrays();
        initializeEditText();
        editTextsChangedListener();
        setOnClickListener();
    }

    private void setOnClickListener() {
        binding.buttonCount.setOnClickListener(this);
        binding.textViewBlock.setOnClickListener(this);
        binding.textViewGozar.setOnClickListener(this);

    }

    private void editTextsChangedListener() {
        binding.editTextMaskooni.addTextChangedListener(this);
        binding.editTextTejari.addTextChangedListener(this);
        binding.editTextEdari.addTextChangedListener(this);
        binding.editTextOmumi.addTextChangedListener(this);
        binding.editTextSanati.addTextChangedListener(this);
        binding.editTextHotel.addTextChangedListener(this);
    }

    private void counting(boolean dis) {
        if (!binding.editTextMaskooni.getText().toString().isEmpty())
            values.set(0, Integer.parseInt(binding.editTextMaskooni.getText().toString()));
        if (!binding.editTextTejari.getText().toString().isEmpty())
            values.set(1, Integer.parseInt(binding.editTextTejari.getText().toString()));
        if (!binding.editTextEdari.getText().toString().isEmpty())
            values.set(2, Integer.parseInt(binding.editTextEdari.getText().toString()));
        if (!binding.editTextOmumi.getText().toString().isEmpty())
            values.set(3, Integer.parseInt(binding.editTextOmumi.getText().toString()));
        if (!binding.editTextSanati.getText().toString().isEmpty())
            values.set(4, Integer.parseInt(binding.editTextSanati.getText().toString()));
        if (!binding.editTextHotel.getText().toString().isEmpty())
            values.set(5, Integer.parseInt(binding.editTextHotel.getText().toString()));
        if (values.get(0) > 0 || values.get(1) > 0 || values.get(2) > 0 || values.get(3) > 0 ||
                values.get(4) > 0 || values.get(5) > 0) {
            Integer gozarPosition = gozarMap.get(binding.textViewGozar.getText().toString());
            Integer blockPosition = blockMap.get(binding.textViewBlock.getText().toString());
            double countMaskooni = 20000, countEdariDolati = 20000, countTejari = 20000,
                    countSanati = 20000, countKhadamati = 20000, countSayer = 20000;
            values.set(6, blockPosition);
            values.set(7, gozarPosition);
            final Block block = arzeshdaraei.blocks.get(values.get(6));
            final Formula formula = arzeshdaraei.formulas.get(values.get(7));

            if ((block.maskooni * formula.maskooniZ) > 20000)
                countMaskooni = (block.maskooni * formula.maskooniZ);
            if ((block.tejari * formula.tejariZ) > 20000)
                countTejari = (block.tejari * formula.tejariZ);
            if ((block.edariDolati * formula.edariDolatiZ) > 20000)
                countEdariDolati = (block.edariDolati * formula.edariDolatiZ);
            if ((block.sanati * formula.sanatiZ) > 20000)
                countSanati = (block.sanati * formula.sanatiZ);
            if ((block.khadamati * formula.khadamatiZ) > 20000)
                countKhadamati = (block.khadamati * formula.khadamatiZ);
            if ((block.sayer * formula.sayerZ) > 20000)
                countSayer = (block.sayer * formula.sayerZ);

            countMaskooni = (countMaskooni * values.get(0));
            countTejari = (countTejari * values.get(1));
            countEdariDolati = (countEdariDolati * values.get(2));
            countKhadamati = (countKhadamati * values.get(3));
            countSanati = (countSanati * values.get(4));
            countSayer = (countSayer * values.get(5));
            int sum = 0;
            for (Integer i : values.subList(0, 5)) {
                int i1 = i;
                sum += i1;
            }
            int count = (int) ((countMaskooni + countEdariDolati + countTejari + countSanati
                    + countKhadamati + countSayer)
                    / (sum));
            count = count / 1000;
            count = count * 1000;
            binding.textViewCount.setText(String.valueOf(count));
            if (dis) {
                if (gozarPosition != null) {
                    baseInfoFragment.setValue(values, count, binding.textViewBlock.getText().toString(),
                            MessageFormat.format("{0} - {1}",
                                    (int) arzeshdaraei.formulas.get(gozarPosition).gozarTo,
                                    (int) arzeshdaraei.formulas.get(gozarPosition).gozarFrom));
                    dismiss();
                }
            }
        } else {
            binding.textViewCount.setText(getString(R.string.zero));
            if (dis) {
                new CustomToast().warning(getString(R.string.at_least_enter_one));
            }
        }
    }

    private void initializeEditText() {
        binding.editTextMaskooni.setText(String.valueOf(values.get(0)));
        binding.editTextTejari.setText(String.valueOf(values.get(1)));
        binding.editTextEdari.setText(String.valueOf(values.get(2)));
        binding.editTextOmumi.setText(String.valueOf(values.get(3)));
        binding.editTextSanati.setText(String.valueOf(values.get(4)));
        binding.editTextHotel.setText(String.valueOf(values.get(5)));
    }

    private void initializeArrays() {
        initializeBlock();
        initializeGozar();
    }


    private void initializeGozar() {
        gozarTitles.clear();
        ArrayList<Formula> formulas = arzeshdaraei.formulas;
        for (int i = 0; i < formulas.size(); i++) {
            Formula formula = formulas.get(i);
            gozarTitles.add(formula.gozarTitle);
            gozarMap.put(formula.gozarTitle, i);
        }
    }

    private void initializeBlock() {
        blockTitles.clear();
        ArrayList<Block> blocks = arzeshdaraei.blocks;
        for (int i = 0; i < blocks.size(); i++) {
            Block block = blocks.get(i);
            blockTitles.add(block.blockId);
            blockMap.put(block.blockId, i);
        }
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.button_count) {
            if (checkEmpty(binding.editTextMaskooni, requireContext()) ||
                    checkEmpty(binding.editTextEdari, requireContext()) ||
                    checkEmpty(binding.editTextHotel, requireContext()) ||
                    checkEmpty(binding.editTextOmumi, requireContext()) ||
                    checkEmpty(binding.editTextSanati, requireContext()) ||
                    checkEmpty(binding.editTextTejari, requireContext())) {
                counting(true);
            } else
                new CustomToast().warning(getString(R.string.at_least_enter_one));
        } else if (id == R.id.text_view_block) {
            showMenu(binding.textViewBlock, blockTitles);
        } else if (id == R.id.text_view_gozar) {
            showMenu(binding.textViewGozar, gozarTitles);
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
            counting(false);
            return true;
        });
        popup.show();
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        counting(false);
    }

    @Override
    public void onResume() {
        if (getDialog() != null && getDialog().getWindow() != null) {
            WindowManager.LayoutParams params = getDialog().getWindow().getAttributes();
            params.width = ViewGroup.LayoutParams.WRAP_CONTENT;
            params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
            getDialog().getWindow().setAttributes(params);
        }
        super.onResume();
    }

    public interface Callback {
        void setValue(ArrayList<Integer> values, int value, String block, String arz);

        Arzeshdaraei getArzeshdaraei();

        ArrayList<Integer> getValue();
    }
}