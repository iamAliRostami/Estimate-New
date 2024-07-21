package com.leon.estimate_new.utils.custom_views;


import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;

import com.google.android.material.textfield.TextInputLayout;
import com.leon.estimate_new.R;

public class CustomTextInputLayout extends TextInputLayout {

    private float mainHintTextSize;
    private float editTextSize;

    public CustomTextInputLayout(Context context) {
        this(context, null);
    }

    public CustomTextInputLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomTextInputLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CustomTextInputLayout);
        mainHintTextSize = a.getDimensionPixelSize(R.styleable.CustomTextInputLayout_mainHintTextSize,
                0);
        a.recycle();
    }

    @Override
    public void addView(@NonNull View child, int index, @NonNull ViewGroup.LayoutParams params) {
        final boolean b = child instanceof EditText && mainHintTextSize > 0;

        if (b) {
            final EditText e = (EditText) child;
            editTextSize = e.getTextSize();
            e.setTextSize(TypedValue.COMPLEX_UNIT_PX, mainHintTextSize);
        }

        super.addView(child, index, params);

        if (b) {
            getEditText().setTextSize(TypedValue.COMPLEX_UNIT_PX, editTextSize);
        }
    }

    // Units are pixels.

    public float getMainHintTextSize() {
        return mainHintTextSize;
    }
}
