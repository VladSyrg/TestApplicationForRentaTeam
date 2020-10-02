package com.syrgdev.testapplicationforrentateam.common.view.main;


import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.syrgdev.testapplicationforrentateam.common.view.base.BaseView;

public abstract class MainActivityView extends BaseView {

    public abstract BottomNavigationView getBottomNavigationView();

    public abstract int getFragmentContainerId();

    public abstract boolean isBottomNavigationViewVisible();

    public abstract void hideBottomNavigationView();

    public abstract void showBottomNavigationView();

}
