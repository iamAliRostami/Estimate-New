package com.leon.estimate_new.utils.gis;

import static com.leon.estimate_new.enums.MapLayerType.GIS_SANITATION_TRANSFER;
import static com.leon.estimate_new.enums.MapLayerType.GIS_WATER_PIPE;
import static com.leon.estimate_new.enums.MapLayerType.GIS_WATER_TRANSFER;
import static com.leon.estimate_new.enums.ProgressType.SHOW;
import static com.leon.estimate_new.enums.SharedReferenceKeys.TOKEN_FOR_GIS;
import static com.leon.estimate_new.helpers.MyApplication.getApplicationComponent;
import static com.leon.estimate_new.helpers.MyApplication.getContext;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.leon.estimate_new.base_items.BaseAsync;
import com.leon.estimate_new.di.view_model.HttpClientWrapper;
import com.leon.estimate_new.enums.MapLayerType;
import com.leon.estimate_new.fragments.forms.MapDescriptionFragment;
import com.leon.estimate_new.infrastructure.IAbfaService;
import com.leon.estimate_new.infrastructure.ICallback;
import com.leon.estimate_new.infrastructure.ICallbackIncomplete;
import com.leon.estimate_new.tables.GISInfo;
import com.leon.estimate_new.utils.CustomErrorHandling;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

public class GetGisPoint extends BaseAsync {
    private final MapLayerType currentType;
    private final double[] latLong;

    private final Object object;
    private final String billId;

    public GetGisPoint(Context context, double[] latLong, String billId, Object... view) {
        super(false);
        this.latLong = latLong;
        this.billId = billId;
        this.object = view[0];
        this.currentType = (MapLayerType) view[1];
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
        final String token = getApplicationComponent().SharedPreferenceModel()
                .getStringData(TOKEN_FOR_GIS.getValue());
        Call<String> call;
        if (currentType == GIS_WATER_PIPE) {
            call = abfaService.getGisWaterPipe(new GISInfo("jesuschrist", token, billId,
                    latLong[0], latLong[1]));
        } else if (currentType == GIS_WATER_TRANSFER) {
            call = abfaService.getGisWaterTransfer(new GISInfo("jesuschrist", token, billId,
                    latLong[0], latLong[1]));
        } else if (currentType == GIS_SANITATION_TRANSFER) {
            call = abfaService.getGisSanitationTransfer(new GISInfo("jesuschrist", token, billId,
                    latLong[0], latLong[1]));
        } else {
            call = abfaService.getGisParcels(new GISInfo("jesuschrist", token, billId,
                    latLong[0], latLong[1]));
        }
        HttpClientWrapper.callHttpAsync(call, SHOW.getValue(), activity, new GetGISLayer(),
                new GetGISIncomplete(), new GetError());

    }

    @Override
    public void backgroundTask(Context context) {

    }

    class GetGISLayer implements ICallback<String> {
        @Override
        public void execute(Response<String> response) {
            ((MapDescriptionFragment) object).setGisLayer();
        }
    }

    class GetGISIncomplete implements ICallbackIncomplete<String> {
        @Override
        public void executeIncomplete(Response<String> response) {

            CustomErrorHandling errorHandling = new CustomErrorHandling(getContext());

            try {CustomErrorHandling.APIError apiError = errorHandling.parseError(response);
                JSONObject jObjError = new JSONObject(response.errorBody().string());
                Log.e("message", apiError.message());
                Log.e("message", jObjError.getJSONObject("error").getString("message"));
            } catch (JSONException | IOException e) {
                e.printStackTrace();
            }
            Log.e("message", response.errorBody().toString());
        }
    }
}
