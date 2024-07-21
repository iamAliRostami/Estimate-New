package com.leon.estimate_new.utils.custom_views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.Nullable;

import com.leon.estimate_new.R;

public class TitleEditTextView extends View {
    public TitleEditTextView(Context context) {
        super(context);
    }

    public TitleEditTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        inflate(context,R.layout.title_edit_text,this);
    }

    public TitleEditTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public TitleEditTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
}
