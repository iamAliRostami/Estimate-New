package com.leon.estimate_new.utils.custom_views;

import static com.leon.estimate_new.helpers.Constants.MOBILE_REGEX;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.EditorInfo;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.BindingAdapter;
import androidx.databinding.InverseBindingAdapter;
import androidx.databinding.InverseBindingListener;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.leon.estimate_new.R;

public class TitleEditTextView extends ConstraintLayout {
    private TextInputEditText textInputEditText;
    private TextInputLayout textInputLayout;
    private final Context context;
    private final View view;
    private int nextId;

    public TitleEditTextView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        view = inflate(context, R.layout.title_edit_text, this);
        this.context = context;
        initializeViews();
        initializeAttribute(attrs);
    }

    private void initializeViews() {
        textInputLayout = view.findViewById(R.id.text_input_layout);
        textInputEditText = view.findViewById(R.id.text_input_edit_text);
        textInputEditText.setOnEditorActionListener((textView, i, keyEvent) -> {
            if (i == EditorInfo.IME_ACTION_NEXT) {
                TextInputEditText viewTemp = view.findViewById(nextId);
                viewTemp.requestFocus();
            }
            return false;
        });
    }

    private void initializeAttribute(@Nullable AttributeSet attrs) {
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.TitleEditTextView);
        int n = a.getIndexCount();
        for (int i = 0; i < n; i++) {
            int attr = a.getIndex(i);
            if (attr == R.styleable.TitleEditTextView_title_text) {
                setLayoutTitle(a.getString(attr));
            } else if (attr == R.styleable.TitleEditTextView_android_text) {
                setInputText(a.getString(attr));
            } else if (attr == R.styleable.TitleEditTextView_android_inputType) {
                textInputEditText.setInputType(a.getInt(attr, EditorInfo.TYPE_TEXT_VARIATION_NORMAL));
            } else if (attr == R.styleable.TitleEditTextView_android_maxLength) {
                textInputEditText.setFilters(new InputFilter[]{
                        new InputFilter.LengthFilter(a.getInt(attr, 100))});
            } else if (attr == R.styleable.TitleEditTextView_nextFocus) {
                nextId = textInputEditText.getId();
                setNextFocus(a.getResourceId(attr, nextId));
            } else if (attr == R.styleable.TitleEditTextView_lines) {
                textInputEditText.setLines(a.getInt(attr, 1));
                textInputEditText.setMaxLines(a.getInt(attr, 1));
            } else if (attr == R.styleable.TitleEditTextView_android_enabled) {
                textInputEditText.setEnabled(a.getBoolean(attr, true));
            } else if (attr == R.styleable.TitleEditTextView_android_gravity) {
                textInputEditText.setGravity(a.getInt(attr, Gravity.CENTER));
            } else if (attr == R.styleable.TitleEditTextView_android_imeOptions) {
                textInputEditText.setImeOptions(a.getInt(attr, EditorInfo.IME_ACTION_NEXT));
            }
        }
        a.recycle();
    }

    public void setLayoutTitle(String title) {
        textInputLayout.setHint(title);
    }

    @BindingAdapter("android:text")
    public static void setInputText(TitleEditTextView view, String inputText) {
        if (!view.getInputText().equals(inputText)) {
            view.setInputText(inputText);
        }
    }

    @InverseBindingAdapter(attribute = "android:text")
    public static String getInputText(TitleEditTextView view) {
        return view.getInputText();
    }

    @BindingAdapter("android:textAttrChanged")
    public static void setListeners(TitleEditTextView view, InverseBindingListener attrChange) {
        view.getTextInputEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                attrChange.onChange();
            }
        });
    }

    public void setNextFocus(@IdRes int id) {
        textInputEditText.setNextFocusUpId(id);
        textInputEditText.setNextFocusDownId(id);
        textInputEditText.setNextFocusForwardId(id);
        textInputEditText.setNextFocusLeftId(id);
        textInputEditText.setNextFocusRightId(id);
    }

    public void setInputText(String inputText) {
        textInputEditText.setText(inputText);
    }

    public void setInputType(int inputType) {
        textInputEditText.setInputType(inputType);
    }

    public String getInputText() {
        if (textInputEditText.getText() == null) {
            return "";
        }
        return textInputEditText.getText().toString();

    }

    public boolean validation() {
        if (textInputEditText.getText() == null || textInputEditText.getText().toString().isEmpty()) {
            textInputEditText.setError(context.getString(R.string.error_empty));
            textInputEditText.requestFocus();
            return false;
        }
        return true;
    }

    public boolean postalCodeValidation() {
        if (textInputEditText.getText() != null &&
                !textInputEditText.getText().toString().isEmpty() &&
                textInputEditText.getText().toString().length() < 10) {
            textInputEditText.setError(context.getString(R.string.error_format));
            textInputEditText.requestFocus();
            return false;
        }
        return true;
    }

    public boolean nationalIdValidation() {
        if (textInputEditText.getText() == null || textInputEditText.getText().toString().isEmpty()) {
            textInputEditText.setError(context.getString(R.string.error_empty));
            textInputEditText.requestFocus();
            return false;
        } else if (textInputEditText.getText().toString().length() < 10) {
            textInputEditText.setError(context.getString(R.string.error_format));
            textInputEditText.requestFocus();
            return false;
        }
        return true;
    }

    public boolean mobileValidation() {
        if (textInputEditText.getText() == null) {
            textInputEditText.setError(context.getString(R.string.error_empty));
            textInputEditText.requestFocus();
            return false;
        } else if (!MOBILE_REGEX.matcher(textInputEditText.getText().toString()).matches()) {
            textInputEditText.setError(context.getString(R.string.error_format));
            textInputEditText.requestFocus();
            return false;
        }
        return true;
    }

    public TextInputLayout getTextInputLayout() {
        return textInputLayout;
    }


    public TextInputEditText getTextInputEditText() {
        return textInputEditText;
    }
}


