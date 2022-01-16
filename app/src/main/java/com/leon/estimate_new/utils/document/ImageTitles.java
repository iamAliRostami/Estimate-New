package com.leon.estimate_new.utils.document;

import static com.leon.estimate_new.enums.ProgressType.NOT_SHOW;
import static com.leon.estimate_new.enums.SharedReferenceKeys.TOKEN_FOR_FILE;
import static com.leon.estimate_new.helpers.MyApplication.getApplicationComponent;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.Toast;

import com.leon.estimate_new.R;
import com.leon.estimate_new.activities.DocumentFormActivity;
import com.leon.estimate_new.base_items.BaseAsync;
import com.leon.estimate_new.di.view_model.HttpClientWrapper;
import com.leon.estimate_new.infrastructure.IAbfaService;
import com.leon.estimate_new.infrastructure.ICallback;
import com.leon.estimate_new.infrastructure.ICallbackIncomplete;
import com.leon.estimate_new.tables.ImageDataTitle;
import com.leon.estimate_new.utils.CustomFile;
import com.leon.estimate_new.utils.CustomToast;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ImageTitles extends BaseAsync {
    public ImageTitles(Context context, Object... view) {
        super(context, view);
    }

    @Override
    public void postTask(Object o) {

    }

    @Override
    public void preTask(Object o) {

    }

    @Override
    public void backgroundTask(Activity activity) {
        final Retrofit retrofit = getApplicationComponent().NetworkHelperModel().getInstance();
        final IAbfaService abfaService = retrofit.create(IAbfaService.class);
        final Call<ImageDataTitle> call = abfaService.getTitle(getApplicationComponent()
                .SharedPreferenceModel().getStringData(TOKEN_FOR_FILE.getValue()));
        HttpClientWrapper.callHttpAsync(call, NOT_SHOW.getValue(),
                activity, new GetImageTitles(activity), new GetImageTitlesIncomplete(activity),
                new GetErrorRedirect(activity));
    }
}
class GetImageTitles implements ICallback<ImageDataTitle> {
    private final Activity activity;

    GetImageTitles(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void execute(Response<ImageDataTitle> response) {
//        if (title.isSuccess()) {
//            int selected = 0, counter = 0;
//            imageDataTitle = title;
//            for (ImageDataTitle.DataTitle dataTitle : title.getData()) {
//                if (dataTitle.getTitle().equals("کروکی"))
//                    selected = counter;
//                counter = counter + 1;
//                arrayListTitle.add(dataTitle.getTitle());
//            }
//            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(context,
//                    R.layout.dropdown_menu_popup_item, arrayListTitle) {
//                @NotNull
//                @Override
//                public View getView(int position, View convertView, @NotNull ViewGroup parent) {
//                    View view = super.getView(position, convertView, parent);
//                    final CheckedTextView textView = view.findViewById(android.R.id.text1);
//                    textView.setChecked(true);
//                    textView.setTextSize(context.getResources().getDimension(R.dimen.textSizeSmall));
//                    textView.setTextColor(getResources().getColor(R.color.black));
//                    return view;
//                }
//            };
//            binding.spinnerTitle.setAdapter(arrayAdapter);
//            binding.spinnerTitle.setSelection(selected);
//            images.addAll(CustomFile.loadImage(dataBase, trackNumber, billId, title, context));
//            imageViewAdapter.notifyDataSetChanged();
//        } else {
//            new CustomToast().error(activity.getString(R.string.error_call_backup),
//                    Toast.LENGTH_LONG);
//            activity.finish();
//        }
    }
}

class GetImageTitlesIncomplete implements ICallbackIncomplete<ImageDataTitle> {
    private final Activity activity;

    GetImageTitlesIncomplete(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void executeIncomplete(Response<ImageDataTitle> response) {
        new CustomToast().warning(activity.getString(R.string.error_not_auth),Toast.LENGTH_LONG);
        activity.finish();
    }
}