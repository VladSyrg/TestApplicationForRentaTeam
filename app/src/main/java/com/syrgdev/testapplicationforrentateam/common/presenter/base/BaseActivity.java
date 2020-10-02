package com.syrgdev.testapplicationforrentateam.common.presenter.base;


import androidx.annotation.UiThread;
import androidx.appcompat.app.AppCompatActivity;

import com.syrgdev.testapplicationforrentateam.TestApplicationForRentaTeam;
import com.syrgdev.testapplicationforrentateam.common.dependencyInjection.application.ApplicationComponent;
import com.syrgdev.testapplicationforrentateam.common.dependencyInjection.presentation.PresentationComponent;
import com.syrgdev.testapplicationforrentateam.common.dependencyInjection.presentation.PresentationModule;
import com.syrgdev.testapplicationforrentateam.common.dependencyInjection.presentation.UseCasesModule;

public class BaseActivity extends AppCompatActivity {

    private boolean mIsInjectorUsed;

    @UiThread
    protected PresentationComponent getPresentationComponent() {
        if (mIsInjectorUsed) {
            throw new RuntimeException("there is no need to use injector more than once");
        }
        mIsInjectorUsed = true;
        return getApplicationComponent().newPresentationComponent(
                new PresentationModule(this),
                new UseCasesModule());
    }

    private ApplicationComponent getApplicationComponent() {
        return ((TestApplicationForRentaTeam) getApplication()).getApplicationComponent();
    }

}
