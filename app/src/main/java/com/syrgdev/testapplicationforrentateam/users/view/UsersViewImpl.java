package com.syrgdev.testapplicationforrentateam.users.view;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.syrgdev.testapplicationforrentateam.R;
import com.syrgdev.testapplicationforrentateam.common.Error;
import com.syrgdev.testapplicationforrentateam.common.UserData;
import com.syrgdev.testapplicationforrentateam.common.view.ViewFactory;

import java.util.List;

public class UsersViewImpl extends UsersView
        implements UsersAdapter.Observer {

    Toolbar mToolbar;
    RecyclerView mRecyclerView;
    ProgressBar mProgressBar;
    TextView mEmptyListTextView,
            mErrorMessageTextView;
    UsersAdapter mUsersAdapter;
    View mNoInternetView;
    Button mRetry;

    public UsersViewImpl(LayoutInflater layoutInflater, ViewGroup container, ViewFactory viewFactory) {

        setRootView(layoutInflater.inflate(R.layout.view_users, container, false));

        mToolbar = findViewById(R.id.toolbar);
        mToolbar.setTitle(R.string.title_users);

        mProgressBar = findViewById(R.id.progress_bar);
        mEmptyListTextView = findViewById(R.id.empty_list_text_view);
        mErrorMessageTextView = findViewById(R.id.error_text_view);
        mNoInternetView = findViewById(R.id.no_internet_view);
        mRecyclerView = findViewById(R.id.recycler_view);
        mRetry = findViewById(R.id.retry_button);
        mRetry.setOnClickListener(v -> {
            for (Observer observer : getObservers()) {
                observer.onRetryButtonClicked();
            }
        });

        mUsersAdapter = new UsersAdapter(viewFactory, this);
        mRecyclerView.setAdapter(mUsersAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

    }

    public void bind(List<UserData> userDataList) {
        if (!userDataList.isEmpty()) {
            mUsersAdapter.setUsers(userDataList);
        }
        for (Observer observer : getObservers()) {
            observer.getBottomNavigationViewVisibility();
        }
    }

    public void showProgressBar() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    public void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }

    public void showErrorMessage(Error error) {
        showErrorMessage();
        mErrorMessageTextView.setText(error.getMessage());
    }

    public void hideErrorMessage() {
        mErrorMessageTextView.setVisibility(View.GONE);
    }

    public void showErrorMessage() {
        mErrorMessageTextView.setVisibility(View.VISIBLE);
    }

    public void showRecyclerView() {
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    public void hideRecyclerView() {
        mRecyclerView.setVisibility(View.GONE);
    }

    public void showEmptyListTextView() {
        mEmptyListTextView.setVisibility(View.VISIBLE);
    }

    public void showNoInternetView() {
        hideProgressBar();
        mNoInternetView.setVisibility(View.VISIBLE);
    }

    public void hideNoInternetView() {
        mNoInternetView.setVisibility(View.GONE);
    }

    @Override
    public void fetchUsersStarted() {
        hideNoInternetView();
        showProgressBar();
    }

    public void onUsersFetched(List<UserData> userDataList) {
        hideProgressBar();
        hideErrorMessage();
        if (!userDataList.isEmpty()) {
            showRecyclerView();
            bind(userDataList);
        } else {
            showEmptyListTextView();
            hideRecyclerView();
        }
    }

    @Override
    public void onError(Error error) {
        hideRecyclerView();
        hideProgressBar();
        showErrorMessage(error);
    }

    @Override
    public void noInternetConnection() {
        showNoInternetView();
    }

    @Override
    public void onIsBottomNavigationViewVisible(boolean isVisible) {
        if (!isVisible) {
            showBottomNavigationView();
        }
    }

    private void showBottomNavigationView() {
        for (Observer observer : getObservers()) {
            observer.showBottomNavigationView();
        }
    }

    @Override
    public void onItemClicked(UserData userData) {
        for (Observer observer : getObservers()) {
            observer.onItemClicked(userData);
        }
    }

}