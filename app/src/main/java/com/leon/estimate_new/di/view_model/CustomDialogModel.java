package com.leon.estimate_new.di.view_model;

import static com.leon.estimate_new.enums.DialogType.Green;
import static com.leon.estimate_new.enums.DialogType.GreenRedirect;
import static com.leon.estimate_new.enums.DialogType.Red;
import static com.leon.estimate_new.enums.DialogType.RedRedirect;
import static com.leon.estimate_new.enums.DialogType.Yellow;
import static com.leon.estimate_new.enums.DialogType.YellowRedirect;
import static com.leon.estimate_new.helpers.MyApplication.getActivityComponent;

import android.content.Context;
import android.content.Intent;

import androidx.core.content.ContextCompat;

import com.leon.estimate_new.R;
import com.leon.estimate_new.activities.MainActivity;
import com.leon.estimate_new.enums.DialogType;
import com.leon.estimate_new.utils.custom_dialog.LovelyStandardDialog;

import javax.inject.Inject;

public class CustomDialogModel {
    private final LovelyStandardDialog lovelyStandardDialog;
    private Context context;

    @Inject
    public CustomDialogModel(Context context) {
        lovelyStandardDialog = new LovelyStandardDialog(context);
    }

    public CustomDialogModel(DialogType choose, Context context, String message, String title,
                             String top, String buttonText, Inline... inline) {
        this.context = context;
        lovelyStandardDialog = getActivityComponent().LovelyStandardDialog();
        lovelyStandardDialog.setTitle(title).setMessage(message).setTopTitle(top);
        if (choose == Green)
            CustomGreenDialog(buttonText);
        else if (choose == Yellow)
            CustomYellowDialog(buttonText);
        else if (choose == Red)
            CustomRedDialog(buttonText);
        else if (choose == GreenRedirect)
            CustomGreenDialogRedirect(buttonText);
        else if (choose == YellowRedirect)
            CustomYellowDialogRedirect(buttonText, inline);
        else if (choose == RedRedirect)
            CustomRedDialogRedirect(buttonText);
    }

    public LovelyStandardDialog getLovelyStandardDialog() {
        return lovelyStandardDialog;
    }

    public void CustomGreenDialogRedirect(String ButtonText) {
        lovelyStandardDialog
                .setTopColorRes(R.color.primary_variant)
                .setTopTitleColor(ContextCompat.getColor(context, R.color.text_color_light))
                .setButtonsBackground(R.drawable.background_submit_button)
                .setPositiveButton(ButtonText, v -> {
                    Intent intent = new Intent(context, MainActivity.class);
                    context.startActivity(intent);
                });
        lovelyStandardDialog.show();
    }

    public void CustomYellowDialogRedirect(String buttonText, Inline... inlines) {
        lovelyStandardDialog
                .setTopTitleColor(ContextCompat.getColor(context, R.color.text_color_light))
                .setButtonsBackground(R.drawable.border_yellow_1)
                .setTopColorRes(R.color.yellow)
                .setNegativeButton(buttonText, v -> inlines[0].inline())
                .show();
    }

    public void CustomRedDialogRedirect(String buttonText) {
        lovelyStandardDialog
                .setTopColorRes(R.color.red)
                .setTopTitleColor(ContextCompat.getColor(context, R.color.text_color_light))
                .setButtonsBackground(R.drawable.border_red_1)
                .setNeutralButton(buttonText, v -> lovelyStandardDialog.dismiss())
                .show();
    }

    public void CustomGreenDialog(String buttonText) {
        lovelyStandardDialog
                .setTopColorRes(R.color.primary_variant)
                .setTopTitleColor(ContextCompat.getColor(context, R.color.text_color_light))
                .hideAllButtons()
                .setButtonsBackground(R.drawable.background_submit_button)
                .setPositiveButton(buttonText, v -> lovelyStandardDialog.dismiss())
                .show();
    }

    public void CustomYellowDialog(String buttonText) {
        lovelyStandardDialog
                .setTopTitleColor(ContextCompat.getColor(context, R.color.text_color_light))
                .setTopColorRes(R.color.yellow)
                .hideAllButtons()
                .setButtonsBackground(R.drawable.border_yellow_1)
                .setNegativeButton(buttonText, v -> lovelyStandardDialog.dismiss())
                .show();
    }

    public void CustomRedDialog(String buttonText) {
        lovelyStandardDialog
                .setTopColorRes(R.color.red)
                .setTopTitleColor(ContextCompat.getColor(context, R.color.text_color_light))
                .setButtonsBackground(R.drawable.border_red_1)
                .setNeutralButton(buttonText, v -> lovelyStandardDialog.dismiss())
                .show();
    }

    public interface Inline {
        void inline();
    }
}
