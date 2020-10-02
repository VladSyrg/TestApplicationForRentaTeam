package com.syrgdev.testapplicationforrentateam.user.presenter;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.syrgdev.testapplicationforrentateam.common.ScreensManager;
import com.syrgdev.testapplicationforrentateam.common.UserData;
import com.syrgdev.testapplicationforrentateam.common.presenter.MainActivity;
import com.syrgdev.testapplicationforrentateam.common.presenter.base.BaseFragment;
import com.syrgdev.testapplicationforrentateam.common.view.ViewFactory;
import com.syrgdev.testapplicationforrentateam.user.view.UserView;

import javax.inject.Inject;

import static com.syrgdev.testapplicationforrentateam.BuildConfig.USER_DATA;

public class UserFragment extends BaseFragment
        implements UserView.Observer {

    @Inject
    ScreensManager mScreensManager;

    @Inject
    ViewFactory mViewFactory;

    private UserView mUserView;
    private UserData mUserData;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getPresentationComponent().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mUserView = mViewFactory.newViewInstance(UserView.class, container);
        mUserData = (UserData) requireArguments().getParcelable(USER_DATA);
        return mUserView.getRootView();
    }

    @Override
    public void onStart() {
        super.onStart();
        mUserView.registerObserver(this);
        mUserView.bind(mUserData);
    }

    @Override
    public void onStop() {
        super.onStop();
        mUserView.unregisterObserver(this);
    }

    @Override
    public void onNavigateBackPressed() {
        mScreensManager.navigateBack();
    }

    @Override
    public void getBottomNavigationViewVisibility() {
        boolean isVisible = ((MainActivity) requireActivity()).isBottomNavigationViewVisible();
        mUserView.onIsBottomNavigationViewVisible(isVisible);
    }

    @Override
    public void hideBottomNavigationView() {
        ((MainActivity) requireActivity()).hideBottomNavigationView();
    }

}