package com.leon.estimate_new.fragments;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class ProgressDialogFragment extends DialogFragment {

    private static final String ARGS_TITLE = ProgressDialogFragment.class.getSimpleName() + "_title";
    private static final String ARGS_MESSAGE = ProgressDialogFragment.class.getSimpleName() + "_message";
    private static final String ARGS_CANCEL = ProgressDialogFragment.class.getSimpleName() + "_cancel";

    private ProgressDialogFragment.OnProgressDialogDismissListener mOnProgressDialogDismissListener;

    private String mTitle;
    private String mMessage;
    private String mCancel;

    public static ProgressDialogFragment newInstance(String title, String message, String cancel) {
        ProgressDialogFragment fragment = new ProgressDialogFragment();
        Bundle args = new Bundle();
        args.putString(ARGS_TITLE, title);
        args.putString(ARGS_MESSAGE, message);
        args.putString(ARGS_CANCEL, cancel);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // prevent re-creation during configuration chance to allow us to dismiss this DialogFragment
        setRetainInstance(true);
        setCancelable(false);

        if (getArguments() != null) {
            mTitle = getArguments().getString(ARGS_TITLE);
            mMessage = getArguments().getString(ARGS_MESSAGE);
            mCancel = getArguments().getString(ARGS_CANCEL);
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof ProgressDialogFragment.OnProgressDialogDismissListener) {
            mOnProgressDialogDismissListener = (ProgressDialogFragment.OnProgressDialogDismissListener) context;
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);
        // create a dialog to show progress
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setTitle(mTitle);
        progressDialog.setMessage(mMessage);
        progressDialog.setIndeterminate(false);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setMax(100);
        progressDialog.setButton(DialogInterface.BUTTON_NEGATIVE, mCancel, (dialog, which) -> onDismiss(dialog));
        return progressDialog;
    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);
        if (mOnProgressDialogDismissListener != null) {
            mOnProgressDialogDismissListener.onProgressDialogDismiss();
        }
    }

    @Override
    public void onDestroyView() {
        Dialog dialog = getDialog();
        if (dialog != null && getRetainInstance()) {
            dialog.setDismissMessage(null);
        }
        super.onDestroyView();
    }

    public void setProgress(int progress) {
        if ((ProgressDialog) getDialog() != null)
            ((ProgressDialog) getDialog()).setProgress(progress);
    }

    interface OnProgressDialogDismissListener {
        void onProgressDialogDismiss();
    }
}
