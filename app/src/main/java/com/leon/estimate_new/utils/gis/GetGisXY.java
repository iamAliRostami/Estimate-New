package com.leon.estimate_new.utils.gis;

import static com.leon.estimate_new.enums.ProgressType.NOT_SHOW;
import static com.leon.estimate_new.enums.ProgressType.SHOW;
import static com.leon.estimate_new.helpers.MyApplication.getApplicationComponent;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;

import com.leon.estimate_new.base_items.BaseAsync;
import com.leon.estimate_new.di.view_model.HttpClientWrapper;
import com.leon.estimate_new.fragments.forms.MapDescriptionFragment;
import com.leon.estimate_new.infrastructure.IAbfaService;
import com.leon.estimate_new.infrastructure.ICallback;
import com.leon.estimate_new.infrastructure.ICallbackIncomplete;
import com.leon.estimate_new.tables.Place;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

public class GetGisXY extends BaseAsync {
    private final String billId;
    private final Object object;

    public GetGisXY(Context context, String billId, Object... view) {
//        super(context, false, view);
        super( false);
        this.billId = billId;
        object = view[0];
    }

    @Override
    public void postTask(Object o) {

    }

    @Override
    public void preTask(Object o) {

    }

    @Override
    public void backgroundTask(Activity activity) {
        final Retrofit retrofit = getApplicationComponent().Retrofit();
        final IAbfaService abfaService = retrofit.create(IAbfaService.class);
        final Call<Place> call = abfaService.getXY(billId);
        HttpClientWrapper.callHttpAsync(call, SHOW.getValue(), activity, new GetXY(),
                new GetXYIncomplete(), new GetError());
    }

    @Override
    public void backgroundTask(Context context) {

    }

    @SuppressLint("UseCompatLoadingForDrawables")
    class GetXY implements ICallback<Place> {

        @Override
        public void execute(Response<Place> response) {
            if (response.body() != null) {
                ((MapDescriptionFragment) object).setXY(response.body());
            }
        }
    }

    class GetXYIncomplete implements ICallbackIncomplete<Place> {
        @Override
        public void executeIncomplete(Response<Place> response) {

        }
    }
}