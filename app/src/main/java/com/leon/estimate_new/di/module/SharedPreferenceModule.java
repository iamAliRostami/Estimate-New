package com.leon.estimate_new.di.module;

import android.content.Context;


import com.leon.estimate_new.di.view_model.SharedPreferencemanagerModel;
import com.leon.estimate_new.enums.SharedReferenceNames;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Singleton
@Module
public class SharedPreferenceModule {
    private final SharedPreferencemanagerModel sharedPreferencemanagerModel;

    public SharedPreferenceModule(Context context, SharedReferenceNames sharedReferenceNames) {
        sharedPreferencemanagerModel = new SharedPreferencemanagerModel(context, sharedReferenceNames.getValue());
    }

    @Singleton
    @Provides
    public SharedPreferencemanagerModel providesSharedPreferenceModel() {
        return sharedPreferencemanagerModel;
    }
}
