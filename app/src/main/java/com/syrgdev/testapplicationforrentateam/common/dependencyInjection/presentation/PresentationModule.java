package com.syrgdev.testapplicationforrentateam.common.dependencyInjection.presentation;


import android.content.Context;
import android.view.LayoutInflater;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.syrgdev.testapplicationforrentateam.common.view.ViewFactory;

import dagger.Module;
import dagger.Provides;

@Module
public class PresentationModule {

    private final FragmentActivity mActivity;

    public PresentationModule(FragmentActivity activity) {
        mActivity = activity;
    }

    @Provides
    FragmentManager getFragmentManager() {
        return mActivity.getSupportFragmentManager();
    }

    @Provides
    LayoutInflater getLayoutInflater() {
        return LayoutInflater.from(mActivity);
    }

    @Provides
    FragmentActivity getActivity() {
        return mActivity;
    }

    @Provides
    Context getContext(FragmentActivity activity) {
        return activity;
    }

    @Provides
    ViewFactory getViewFactory(LayoutInflater layoutInflater) {
        return new ViewFactory(layoutInflater);
    }

}
