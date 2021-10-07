package com.leon.estimate_new.di.module;

import android.content.Context;

import com.leon.estimate_new.di.view_model.CustomProgressModel;
import com.leon.estimate_new.di.view_model.SharedPreferenceManagerModel;
import com.leon.estimate_new.enums.SharedReferenceNames;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Singleton
@Module
public class CustomProgressModule {
    private final CustomProgressModel customProgressModel;

    public CustomProgressModule() {
        customProgressModel = CustomProgressModel.getInstance();
    }

    @Singleton
    @Provides
    public CustomProgressModel providesCustomProgressModel() {
        return customProgressModel;
    }
}
