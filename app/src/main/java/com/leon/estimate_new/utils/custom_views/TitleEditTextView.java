package com.leon.estimate_new.utils.custom_views;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.InputFilter;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.leon.estimate_new.R;

import java.util.regex.Pattern;

public class TitleEditTextView extends ConstraintLayout {
    private TextInputLayout textInputLayout;
    private TextInputEditText textInputEditText;
    private final View view;
    private final Context context;
    private int nextId;
    private final Pattern MOBILE_REGEX = Pattern.compile("^((\\+98|0)9\\d{9})$");

    public TitleEditTextView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        view = inflate(context, R.layout.title_edit_text, this);
        this.context = context;
        initializeViews(attrs);
    }

    private void initializeViews(@Nullable AttributeSet attrs) {
        textInputLayout = view.findViewById(R.id.text_input_layout);
        textInputEditText = view.findViewById(R.id.text_input_edit_text);

        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.TitleEditTextView);

        int n = a.getIndexCount();
        for (int i = 0; i < n; i++) {
            int attr = a.getIndex(i);
            if (attr == R.styleable.TitleEditTextView_title_text) {
                setLayoutTitle(a.getString(attr));
            } else if (attr == R.styleable.TitleEditTextView_input_text) {
                setInputText(a.getString(attr));
            } else if (attr == R.styleable.TitleEditTextView_android_inputType) {
                textInputEditText.setInputType(a.getInt(attr, EditorInfo.TYPE_TEXT_VARIATION_NORMAL));
            } else if (attr == R.styleable.TitleEditTextView_android_maxLength) {
                textInputEditText.setFilters(new InputFilter[]{
                        new InputFilter.LengthFilter(a.getInt(attr, 100))});
            } else if (attr == R.styleable.TitleEditTextView_nextFocus) {
                nextId = textInputEditText.getId();
                Log.e("id 1", String.valueOf(nextId));
                setNextFocus(a.getResourceId(attr, nextId));
            }
        }
        textInputEditText.setOnEditorActionListener((textView, i, keyEvent) -> {
            if (i==EditorInfo.IME_ACTION_NEXT){
                TextInputEditText viewTemp = view.findViewById(nextId);
                viewTemp.requestFocus();
            }
            return false;
        });
        a.recycle();
    }

    public void setNextFocus(@IdRes int id) {
        Log.e("id 2", String.valueOf(id));
        textInputEditText.setNextFocusUpId(id);
        textInputEditText.setNextFocusDownId(id);
        textInputEditText.setNextFocusForwardId(id);
        textInputEditText.setNextFocusLeftId(id);
        textInputEditText.setNextFocusRightId(id);
    }

    public void setLayoutTitle(String title) {
        textInputLayout.setHint(title);
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
        if (textInputEditText.getText() == null || textInputEditText.getText().toString().length() < 10) {
            textInputEditText.setError(context.getString(R.string.error_format));
            textInputEditText.requestFocus();
            return false;
        }
        return true;
    }

    public boolean nationalCodeValidation() {
        if (textInputEditText.getText() == null || textInputEditText.getText().toString().length() < 10) {
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
