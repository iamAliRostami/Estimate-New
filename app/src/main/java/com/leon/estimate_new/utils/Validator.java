package com.leon.estimate_new.utils;

import static com.leon.estimate_new.helpers.Constants.MOBILE_REGEX;

import android.content.Context;

import com.google.android.material.textfield.TextInputEditText;
import com.leon.estimate_new.R;

public class Validator {

    public static boolean checkEmpty(TextInputEditText editText, Context context) {
        if (editText.getText() == null || editText.getText().toString().isEmpty()) {
            new CustomToast().warning(context.getString(R.string.error_empty));
            editText.requestFocus();
            return false;
        }
        return true;
    }


    public static boolean nationalIdValidation(TextInputEditText editText, Context context) {
        if (editText.getText() == null || editText.getText().toString().isEmpty()) {
            new CustomToast().warning(context.getString(R.string.error_empty));
            editText.requestFocus();
            return false;
        } else if (editText.getText().toString().length() < 10) {
            new CustomToast().warning(context.getString(R.string.error_format));
            editText.requestFocus();
            return false;
        }
        return true;
    }

    public static boolean mobileValidation(TextInputEditText editText, Context context) {
        if (editText.getText() == null) {
            new CustomToast().warning(context.getString(R.string.error_empty));
            editText.requestFocus();
            return false;
        } else if (!MOBILE_REGEX.matcher(editText.getText().toString()).matches()) {
            new CustomToast().warning(context.getString(R.string.error_format));
            editText.requestFocus();
            return false;
        }
        return true;
    }

    public static boolean postalCodeValidation(TextInputEditText editText, Context context) {
        if (editText.getText() != null &&
                !editText.getText().toString().isEmpty() &&
                editText.getText().toString().length() < 10) {
            new CustomToast().warning(context.getString(R.string.error_format));
            editText.requestFocus();
            return false;
        }
        return true;
    }
}
