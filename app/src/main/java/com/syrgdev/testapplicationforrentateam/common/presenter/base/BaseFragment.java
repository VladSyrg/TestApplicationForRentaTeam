package com.syrgdev.testapplicationforrentateam.common.presenter.base;


import androidx.annotation.UiThread;
import androidx.fragment.app.Fragment;

import com.syrgdev.testapplicationforrentateam.TestApplicationForRentaTeam;
import com.syrgdev.testapplicationforrentateam.common.dependencyInjection.application.ApplicationComponent;
import com.syrgdev.testapplicationforrentateam.common.dependencyInjection.presentation.PresentationComponent;
import com.syrgdev.testapplicationforrentateam.common.dependencyInjection.presentation.PresentationModule;
import com.syrgdev.testapplicationforrentateam.common.dependencyInjection.presentation.UseCasesModule;

public abstract class BaseFragment extends Fragment {

    private boolean isInjectorUsed;

    @UiThread
    protected PresentationComponent getPresentationComponent() {
        if (isInjectorUsed) {
            throw new RuntimeException("there is no need to use injector more than once");
        }
        isInjectorUsed = true;
        return getApplicationComponent().newPresentationComponent(
                new PresentationModule(requireActivity()),
                new UseCasesModule());
    }

    private ApplicationComponent getApplicationComponent() {
        return ((TestApplicationForRentaTeam) requireActivity().getApplication()).getApplicationComponent();
    }

}