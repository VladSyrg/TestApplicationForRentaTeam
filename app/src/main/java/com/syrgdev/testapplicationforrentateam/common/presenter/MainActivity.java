package com.syrgdev.testapplicationforrentateam.common.presenter;

import android.os.Bundle;

import com.syrgdev.testapplicationforrentateam.common.ScreensManager;
import com.syrgdev.testapplicationforrentateam.common.presenter.base.BaseActivity;
import com.syrgdev.testapplicationforrentateam.common.view.ViewFactory;
import com.syrgdev.testapplicationforrentateam.common.view.main.MainActivityView;

import javax.inject.Inject;

public class MainActivity extends BaseActivity {

    MainActivityView mMainActivityView;

    @Inject
    ViewFactory mViewFactory;

    @Inject
    ScreensManager mScreensManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getPresentationComponent().inject(this);
        mMainActivityView = mViewFactory.newViewInstance(MainActivityView.class, null);
        setContentView(mMainActivityView.getRootView());
        mScreensManager.init(
                mMainActivityView.getBottomNavigationView(),
                this,
                mMainActivityView.getFragmentContainerId());

    }

    public void showBottomNavigationView() {
        mMainActivityView.showBottomNavigationView();
    }

    public void hideBottomNavigationView() {
        mMainActivityView.hideBottomNavigationView();
    }

    public boolean isBottomNavigationViewVisible() {
        return mMainActivityView.isBottomNavigationViewVisible();
    }


}