package com.leon.estimate_new.fragments.dialog;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;

import com.leon.estimate_new.R;
import com.leon.estimate_new.databinding.FragmentValueBinding;
import com.leon.estimate_new.tables.Arzeshdaraei;
import com.leon.estimate_new.tables.Block;
import com.leon.estimate_new.tables.Formula;
import com.leon.estimate_new.utils.CustomToast;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ValueFragment extends DialogFragment {
    private FragmentValueBinding binding;
    private Callback formActivity;
    private Arzeshdaraei arzeshdaraei;
    private final ArrayList<Integer> value = new ArrayList<>();
    private int maskooni = 0, tejari = 0, omoomi = 0, sanati = 0, edari = 0, hotel = 0;
    private final TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            counting(false);
        }
    };

    public ValueFragment() {
    }

    public static ValueFragment newInstance() {
        return new ValueFragment();
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
        arzeshdaraei = formActivity.getArzeshdaraei();
        value.clear();
        value.addAll(formActivity.getValue());
        initializeSpinners();
        initializeEditText();
        addEditTextsChangedListener();
        onButtonCountingClickListener();
    }

    private void onButtonCountingClickListener() {
        binding.buttonCounting.setOnClickListener(v -> {
            if (checkIsNoEmpty(binding.editTextMaskooni) || checkIsNoEmpty(binding.editTextEdari)
                    || checkIsNoEmpty(binding.editTextHotel) || checkIsNoEmpty(binding.editTextOmumi)
                    || checkIsNoEmpty(binding.editTextSanati) || checkIsNoEmpty(binding.editTextTejari)) {
                value.set(0, maskooni);
                value.set(1, tejari);
                value.set(2, edari);
                value.set(3, omoomi);
                value.set(4, sanati);
                value.set(5, hotel);
                value.set(6, binding.spinner1.getSelectedItemPosition());
                value.set(7, binding.spinner2.getSelectedItemPosition());
                counting(true);
            } else
                new CustomToast().warning(getString(R.string.at_least_enter_one));
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

    private void addEditTextsChangedListener() {
        binding.editTextMaskooni.addTextChangedListener(textWatcher);
        binding.editTextTejari.addTextChangedListener(textWatcher);
        binding.editTextEdari.addTextChangedListener(textWatcher);
        binding.editTextOmumi.addTextChangedListener(textWatcher);
        binding.editTextSanati.addTextChangedListener(textWatcher);
        binding.editTextHotel.addTextChangedListener(textWatcher);
    }

    private void counting(boolean dis) {
        if (binding.editTextHotel.getText().toString().length() > 0)
            hotel = Integer.parseInt(binding.editTextHotel.getText().toString());
        if (binding.editTextMaskooni.getText().toString().length() > 0)
            maskooni = Integer.parseInt(binding.editTextMaskooni.getText().toString());
        if (binding.editTextTejari.getText().toString().length() > 0)
            tejari = Integer.parseInt(binding.editTextTejari.getText().toString());
        if (binding.editTextSanati.getText().toString().length() > 0)
            sanati = Integer.parseInt(binding.editTextSanati.getText().toString());
        if (binding.editTextEdari.getText().toString().length() > 0)
            edari = Integer.parseInt(binding.editTextEdari.getText().toString());
        if (binding.editTextOmumi.getText().toString().length() > 0)
            omoomi = Integer.parseInt(binding.editTextOmumi.getText().toString());

        if (maskooni > 0 || hotel > 0 || tejari > 0 || sanati > 0 || edari > 0 || omoomi > 0) {
            double countMaskooni = 20000, countEdariDolati = 20000, countTejari = 20000,
                    countSanati = 20000, countKhadamati = 20000, countSayer = 20000;
            final Block block = arzeshdaraei.blocks.get(binding.spinner1.getSelectedItemPosition());
            final Formula formula = arzeshdaraei.formulas.get(binding.spinner2.getSelectedItemPosition());

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

            countMaskooni = (countMaskooni * maskooni);
            countEdariDolati = (countEdariDolati * edari);
            countTejari = (countTejari * tejari);
            countSanati = (countSanati * sanati);
            countKhadamati = (countKhadamati * omoomi);
            countSayer = (countSayer * hotel);
            int count = (int) ((countMaskooni + countEdariDolati + countTejari + countSanati
                    + countKhadamati + countSayer)
                    / (maskooni + tejari + omoomi + sanati + edari + hotel));
            count = count / 1000;
            count = count * 1000;
            binding.textViewCount.setText(String.valueOf(count));
            Log.e("value", String.valueOf(count));
            if (dis) {
                formActivity.setValue(count);
                dismiss();
            }
        } else {
            binding.textViewCount.setText(getString(R.string.zero));
            if (dis) {
                new CustomToast().warning(getString(R.string.at_least_enter_one));
            }
        }
    }

    private void initializeEditText() {
        binding.editTextMaskooni.setText(String.valueOf(value.get(0)));
        binding.editTextTejari.setText(String.valueOf(value.get(1)));
        binding.editTextEdari.setText(String.valueOf(value.get(2)));
        binding.editTextOmumi.setText(String.valueOf(value.get(3)));
        binding.editTextSanati.setText(String.valueOf(value.get(4)));
        binding.editTextHotel.setText(String.valueOf(value.get(5)));
    }

    private void initializeSpinners() {
        initializeSpinnerBlock();
        initializeSpinnerGozar();
        binding.spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                counting(false);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        binding.spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                counting(false);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    void initializeSpinnerGozar() {
        List<String> arrayListSpinner = new ArrayList<>();
        for (Formula formula : arzeshdaraei.formulas) {
            arrayListSpinner.add(formula.gozarTitle);
        }
        binding.spinner2.setAdapter(createArrayAdapter(arrayListSpinner));
        binding.spinner2.setSelection(value.get(7));
    }

    void initializeSpinnerBlock() {
        List<String> arrayListSpinner = new ArrayList<>();
        for (Block block : arzeshdaraei.blocks) {
            arrayListSpinner.add(block.blockId);
        }

        binding.spinner1.setAdapter(createArrayAdapter(arrayListSpinner));
        binding.spinner1.setSelection(value.get(6));
    }

    private ArrayAdapter<String> createArrayAdapter(List<String> arrayListSpinner) {
        return new ArrayAdapter<String>(requireContext(),
                R.layout.item_dropdown_menu, arrayListSpinner) {
            @NotNull
            @Override
            public View getView(int position, View convertView, @NotNull ViewGroup parent) {
                final View view = super.getView(position, convertView, parent);
                final CheckedTextView textView = view.findViewById(android.R.id.text1);
                textView.setChecked(true);
                textView.setTextSize(getResources().getDimension(R.dimen.text_size_small));
                textView.setTextColor(ContextCompat.getColor(requireContext(), android.R.color.black));
                return view;
            }
        };
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof Activity) {
            formActivity = (Callback) context;
        }
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
        void setValue(int value);

        Arzeshdaraei getArzeshdaraei();

        void setValue(ArrayList<Integer> value);

        ArrayList<Integer> getValue();
    }
}