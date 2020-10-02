package com.syrgdev.testapplicationforrentateam.about.presenter;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.syrgdev.testapplicationforrentateam.about.view.AboutView;
import com.syrgdev.testapplicationforrentateam.common.presenter.base.BaseFragment;
import com.syrgdev.testapplicationforrentateam.common.view.ViewFactory;

import javax.inject.Inject;

public class AboutFragment extends BaseFragment {

    @Inject
    ViewFactory mViewFactory;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getPresentationComponent().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        AboutView aboutView = mViewFactory.newViewInstance(AboutView.class, container);
        return aboutView.getRootView();
    }

}