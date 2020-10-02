package com.syrgdev.testapplicationforrentateam.common.view.main;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.transition.ChangeBounds;
import androidx.transition.TransitionManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.syrgdev.testapplicationforrentateam.R;

public class MainActivityViewImpl extends MainActivityView {

    BottomNavigationView mBottomNavigationView;
    private View mFragmentContainer;
    private ConstraintLayout mConstraintLayout;
    ConstraintSet constraintSet = new ConstraintSet();
    private boolean isBottomNavigationViewVisible = true;

    public MainActivityViewImpl(LayoutInflater layoutInflater, ViewGroup container) {
        setRootView(layoutInflater.inflate(R.layout.view_main_activity, container));
        mFragmentContainer = findViewById(R.id.nav_host_fragment);
        mBottomNavigationView = findViewById(R.id.bottom_navigation_view);
        mConstraintLayout = findViewById(R.id.container);
        if (!isBottomNavigationViewVisible) {
            showBottomNavigationView();
        }
    }

    public boolean isBottomNavigationViewVisible() {
        return isBottomNavigationViewVisible;
    }

    @Override
    public BottomNavigationView getBottomNavigationView() {
        return mBottomNavigationView;
    }

    @Override
    public int getFragmentContainerId() {
        return mFragmentContainer.getId();
    }

    @Override
    public void showBottomNavigationView() {
        isBottomNavigationViewVisible = true;
        constraintSet.clone(mConstraintLayout.getContext(), R.layout.view_main_activity);
        ChangeBounds transition = new ChangeBounds();
        transition.setInterpolator(new AccelerateDecelerateInterpolator());
        transition.setDuration(300);
        TransitionManager.beginDelayedTransition(mConstraintLayout, transition);
        constraintSet.applyTo(mConstraintLayout);
        getRootView().invalidate();
    }

    @Override
    public void hideBottomNavigationView() {
        isBottomNavigationViewVisible = false;
        constraintSet.clone(mConstraintLayout.getContext(), R.layout.view_main_activity_bottom_navigation_view_hidden);
        ChangeBounds transition = new ChangeBounds();
        transition.setInterpolator(new AccelerateDecelerateInterpolator());
        transition.setDuration(300);
        TransitionManager.beginDelayedTransition(mConstraintLayout, transition);
        constraintSet.applyTo(mConstraintLayout);
    }

}
