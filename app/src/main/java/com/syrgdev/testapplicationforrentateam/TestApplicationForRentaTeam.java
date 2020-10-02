package com.syrgdev.testapplicationforrentateam;

import android.app.Application;

import com.syrgdev.testapplicationforrentateam.common.dependencyInjection.application.ApplicationComponent;
import com.syrgdev.testapplicationforrentateam.common.dependencyInjection.application.ApplicationModule;
import com.syrgdev.testapplicationforrentateam.common.dependencyInjection.application.DaggerApplicationComponent;

public class TestApplicationForRentaTeam extends Application {

    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        if (mApplicationComponent == null) {
            mApplicationComponent = DaggerApplicationComponent.builder()
                    .applicationModule(new ApplicationModule(this))
                    .build();
        }
    }

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }
}