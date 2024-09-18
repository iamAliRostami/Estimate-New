package com.leon.estimate_new.di.module;

import static com.leon.estimate_new.enums.SharedReferenceKeys.TOKEN;
import static com.leon.estimate_new.helpers.MyApplication.getPreferenceManager;

import com.google.gson.Gson;
import com.leon.estimate_new.di.view_model.NetworkHelperModel;

import dagger.Module;
import dagger.Provides;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;

@Module
public class NetworkModule {
    final NetworkHelperModel networkHelperModel;

    public NetworkModule() {
        networkHelperModel = new NetworkHelperModel();
    }

    @Provides
    public Gson providesGson() {
        return networkHelperModel.getGson();
    }

    @Provides
    public Retrofit providesRetrofit() {
        return networkHelperModel.getInstance(getPreferenceManager().getStringData(TOKEN.getValue()));
    }

    @Provides
    public HttpLoggingInterceptor providesHttpLoggingInterceptor() {
        return networkHelperModel.getInterceptor();
    }

    @Provides
    public NetworkHelperModel providesNetworkHelperModel() {
        return networkHelperModel;
    }
}
