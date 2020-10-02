package com.syrgdev.testapplicationforrentateam.common;

import android.os.Bundle;

import androidx.annotation.IdRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.syrgdev.testapplicationforrentateam.R;
import com.syrgdev.testapplicationforrentateam.common.presenter.MainActivity;

import static com.syrgdev.testapplicationforrentateam.BuildConfig.USER_DATA;

public class ScreensManager {

    private int mNavHostFragmentId;
    private FragmentActivity mActivity;
    private NavController mNavController;

    public void init(BottomNavigationView bottomNavigationView,
                     FragmentActivity activity,
                     @IdRes int navHostFragmentId) {
        mNavHostFragmentId = navHostFragmentId;
        mActivity = activity;
        findNavController();
        setupBottomNavigationViewWithNavController(bottomNavigationView);
    }

    public void findNavController() {
        Fragment navHostFragment = ((MainActivity) mActivity).getSupportFragmentManager().findFragmentById(mNavHostFragmentId);
        assert navHostFragment != null;
        mNavController = NavHostFragment.findNavController(navHostFragment);
    }

    public void setupBottomNavigationViewWithNavController(BottomNavigationView bottomNavigationView) {
        NavigationUI.setupWithNavController(bottomNavigationView, mNavController);
    }

    public void navigateBack() {
        mNavController.navigateUp();
    }

    public void navigateToUserFragment(UserData userData) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(USER_DATA, userData);
        mNavController.navigate(R.id.action_users_to_user, bundle);
    }

}