package com.syrgdev.testapplicationforrentateam.users.view.usersitem;


import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.syrgdev.testapplicationforrentateam.R;
import com.syrgdev.testapplicationforrentateam.common.UserData;

public class UsersItemViewImpl extends UsersItemView {

    TextView mFirstNameTextView, mLastNameTextView;
    UserData mUserData;

    public UsersItemViewImpl(LayoutInflater layoutInflater, ViewGroup container) {
        setRootView(layoutInflater.inflate(R.layout.view_users_item, container, false));
        mFirstNameTextView = findViewById(R.id.firstNameTextView);
        mLastNameTextView = findViewById(R.id.secondNameTextView);
        getRootView().setOnClickListener(v -> {
            for (Observer observer : getObservers()) {
                observer.onItemClicked(mUserData);
            }
        });
    }

    @Override
    public void bind(UserData userData) {
        mUserData = userData;
        mFirstNameTextView.setText(userData.getFirstName());
        mLastNameTextView.setText(userData.getLastName());
    }

}