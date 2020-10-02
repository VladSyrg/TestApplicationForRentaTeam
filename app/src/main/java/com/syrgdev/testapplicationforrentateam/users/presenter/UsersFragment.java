package com.syrgdev.testapplicationforrentateam.users.presenter;


import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.syrgdev.testapplicationforrentateam.common.Error;
import com.syrgdev.testapplicationforrentateam.common.ScreensManager;
import com.syrgdev.testapplicationforrentateam.common.UserData;
import com.syrgdev.testapplicationforrentateam.common.presenter.MainActivity;
import com.syrgdev.testapplicationforrentateam.common.presenter.base.BaseFragment;
import com.syrgdev.testapplicationforrentateam.common.view.ViewFactory;
import com.syrgdev.testapplicationforrentateam.users.usecase.FetchUsersUseCase;
import com.syrgdev.testapplicationforrentateam.users.view.UsersView;

import java.util.List;

import javax.inject.Inject;

public class UsersFragment extends BaseFragment
        implements UsersView.Observer,
        FetchUsersUseCase.Observer {

    @Inject
    ViewFactory mViewFactory;

    @Inject
    FetchUsersUseCase mFetchUsersUseCase;

    private UsersView mUsersView;

    @Inject
    ScreensManager mScreensManager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getPresentationComponent().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mUsersView = mViewFactory.newViewInstance(UsersView.class, container);
        return mUsersView.getRootView();
    }

    @Override
    public void onStart() {
        super.onStart();
        mUsersView.registerObserver(this);
        mFetchUsersUseCase.registerObserver(this);
        fetchUsers(isInternetAvailable());
    }

    private void fetchUsers(boolean isInternetAvailable) {
        mUsersView.fetchUsersStarted();
        mFetchUsersUseCase.fetchUsers(isInternetAvailable);
    }

    private boolean isInternetAvailable() {
        ConnectivityManager cm = (ConnectivityManager) requireActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }

    @Override
    public void onStop() {
        super.onStop();
        mUsersView.unregisterObserver(this);
        mFetchUsersUseCase.unregisterObserver(this);
    }

    @Override
    public void onUsersFetched(List<UserData> posts) {
        mUsersView.onUsersFetched(posts);
    }

    @Override
    public void onError(Error error) {
        mUsersView.onError(error);
    }

    @Override
    public void noInternetConnection() {
        mUsersView.noInternetConnection();
    }

    @Override
    public void onItemClicked(UserData userData) {
        mScreensManager.navigateToUserFragment(userData);
    }

    @Override
    public void onRetryButtonClicked() {
        fetchUsers(isInternetAvailable());
    }

    @Override
    public void getBottomNavigationViewVisibility() {
        boolean isVisible = ((MainActivity) requireActivity()).isBottomNavigationViewVisible();
        mUsersView.onIsBottomNavigationViewVisible(isVisible);
    }

    @Override
    public void showBottomNavigationView() {
        ((MainActivity) requireActivity()).showBottomNavigationView();
    }

}