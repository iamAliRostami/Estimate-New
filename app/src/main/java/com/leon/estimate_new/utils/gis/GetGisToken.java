package com.leon.estimate_new.utils.gis;

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
import com.leon.estimate_new.infrastructure.ICallbackError;
import com.leon.estimate_new.infrastructure.ICallbackIncomplete;
import com.leon.estimate_new.tables.GISToken;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

public class GetGisToken extends BaseAsync {

    private final Object object;

    public GetGisToken(Context context, Object... view) {
        super( false);
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
        final Call<GISToken> call = abfaService.getGISToken();
        HttpClientWrapper.callHttpAsync(call, SHOW.getValue(), activity, new GetGISToken(),
                new GISTokenIncomplete(), new GetError());

    }

    @Override
    public void backgroundTask(Context context) {

    }

    @SuppressLint("UseCompatLoadingForDrawables")
    class GetGISToken implements ICallback<GISToken> {

        @Override
        public void execute(Response<GISToken> response) {
            if (response.body() != null) {
                ((MapDescriptionFragment) object).setGisToken(response.body().token);
            }
        }
    }

    class GISTokenIncomplete implements ICallbackIncomplete<GISToken> {
        @Override
        public void executeIncomplete(Response<GISToken> response) {

        }
    }
}
