package com.leon.estimate_new.di.view_model;

import static com.leon.estimate_new.helpers.MyApplication.getApplicationComponent;
import static com.leon.estimate_new.utils.DifferentCompanyManager.getActiveCompanyName;
import static com.leon.estimate_new.utils.DifferentCompanyManager.getBaseUrl;
import static com.leon.estimate_new.utils.DifferentCompanyManager.getLocalBaseUrl;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.leon.estimate_new.R;

import java.io.File;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public final class NetworkHelperModel {
    private static boolean ONLINE = true;
    private static final TimeUnit TIME_UNIT = TimeUnit.SECONDS;
    private static final boolean RETRY_ENABLED = false;
    private static final long READ_TIMEOUT = 120;
    private static final long WRITE_TIMEOUT = 60;
    private static final long CONNECT_TIMEOUT = 10;

    @Inject
    OkHttpClient okHttpClient;
    @Inject
    Gson gson;
    @Inject
    Retrofit retrofit;
    @Inject
    HttpLoggingInterceptor interceptor;

    /*** with cache */
    @Inject
    public Retrofit getInstance(Context context) {
        final int cacheSize = 50 * 1024 * 1024;// 50 MB
        final File httpCacheDirectory = new File(context.getCacheDir(), context.getString(R.string.cache_folder));
        final Cache cache = new Cache(httpCacheDirectory, cacheSize);

        final String baseUrl = ONLINE ? getBaseUrl(getActiveCompanyName()) :
                getLocalBaseUrl(getActiveCompanyName());
        return new Retrofit.Builder().baseUrl(baseUrl)
                .client(getHttpClient().newBuilder()
                        .addInterceptor(chain -> chain.proceed(chain.request().newBuilder().build()))
                        .cache(cache)
                        .build())
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setLenient()
                        .create())).build();
    }


    @Inject
    public Retrofit getInstance(String url, int denominator) {
        return getInstance().newBuilder()
                .client(getHttpClient(denominator))
                .addConverterFactory(GsonConverterFactory.create(getApplicationComponent().Gson()))
                .baseUrl(url)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
    }

    @Inject
    public Retrofit getInstance(String... s) {
        return getInstance(ONLINE, 1, s);
    }


    @Inject
    public Retrofit getInstance(int denominator, String... s) {
        return getInstance(true, denominator, s);
    }

    @Inject
    public Retrofit getInstance(String s, int readTimeout, int writeTimeout, int connectTimeout) {
        return getInstance(ONLINE, 1).newBuilder()
                .client(getHttpClient(s).newBuilder()
                        .readTimeout(readTimeout, TIME_UNIT)
                        .writeTimeout(writeTimeout, TIME_UNIT)
                        .connectTimeout(connectTimeout, TIME_UNIT)
                        .build())
                .build();
    }

    @Inject
    public Retrofit getInstance(boolean b, String... s) {
        ONLINE = b;
        return getInstance(ONLINE, 1, s);
    }

    @Inject
    public Retrofit getInstance(boolean b, int denominator, String... s) {
        final String baseUrl = b ? getBaseUrl(getActiveCompanyName()) :
                getLocalBaseUrl(getActiveCompanyName());
        if (s.length == 0) {
            if (retrofit == null)
                return new Retrofit.Builder()
                        .baseUrl(baseUrl)
                        .client(getHttpClient())
                        .addConverterFactory(GsonConverterFactory.create(getApplicationComponent().Gson()))
                        .addConverterFactory(ScalarsConverterFactory.create())
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .build();
            else return retrofit.newBuilder()
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .client(getHttpClient(denominator, s))
                    .addConverterFactory(GsonConverterFactory.create(getApplicationComponent().Gson()))
                    .build();
        }
        return retrofit;
    }

    @Inject
    public OkHttpClient getHttpClient() {
        if (okHttpClient == null) {
            return new OkHttpClient.Builder()
                    .readTimeout(READ_TIMEOUT, TIME_UNIT)
                    .writeTimeout(WRITE_TIMEOUT, TIME_UNIT)
                    .connectTimeout(CONNECT_TIMEOUT, TIME_UNIT)
                    .retryOnConnectionFailure(RETRY_ENABLED)
                    .addInterceptor(getInterceptor()).build();
        }
        return okHttpClient;
    }

    @Inject
    public OkHttpClient getHttpClient(String... s) {
        return getHttpClient().newBuilder()
                .addInterceptor(chain -> {
                    final Request request = chain.request().newBuilder()
                            .addHeader("Authorization", "Bearer " + s[0])
                            .build();
                    return chain.proceed(request);
                }).build();
    }

    @Inject
    public OkHttpClient getHttpClient(int denominator, String... s) {
        if (denominator == 1) {
            if (s.length > 0)
                return getHttpClient(s[0]);
            else return getHttpClient();
        }
        if (okHttpClient == null) {
            okHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(getInterceptor())
                    .readTimeout(READ_TIMEOUT / denominator, TIME_UNIT)
                    .writeTimeout(WRITE_TIMEOUT / denominator, TIME_UNIT)
                    .connectTimeout(CONNECT_TIMEOUT, TIME_UNIT)
                    .retryOnConnectionFailure(RETRY_ENABLED)
                    .addInterceptor(chain -> {
                        final Request request = chain.request().newBuilder()
                                .addHeader("Authorization", "Bearer " + s[0])
                                .build();
                        return chain.proceed(request);
                    }).build();
        }
        return okHttpClient;
    }

    @Inject
    public HttpLoggingInterceptor getInterceptor() {
        if (interceptor == null) {
            interceptor = new HttpLoggingInterceptor();
            interceptor.level(HttpLoggingInterceptor.Level.BODY);
        }
        return interceptor;
    }

    @Inject
    public Gson getGson() {
        if (gson == null)
            gson = new GsonBuilder()
                    .setLenient()
                    .create();
        return gson;
    }
}

