package com.syrgdev.testapplicationforrentateam.user.view;


import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.syrgdev.testapplicationforrentateam.R;
import com.syrgdev.testapplicationforrentateam.common.UserData;

public class UserViewImpl extends UserView {

    Toolbar mToolbar;
    TextView mEmailTextView,
            mFirstNameTextView,
            mSecondNameTextView;
    ImageView mAvatarImageView;

    public UserViewImpl(LayoutInflater layoutInflater, ViewGroup container) {
        setRootView(layoutInflater.inflate(R.layout.view_user, container, false));
        mToolbar = findViewById(R.id.toolbar);
        mToolbar.setTitle(R.string.title_user);
        mToolbar.setNavigationOnClickListener(v -> {
            for (Observer observer : getObservers()) {
                observer.onNavigateBackPressed();
            }
        });

        mEmailTextView = findViewById(R.id.emailTextView);
        mFirstNameTextView = findViewById(R.id.firstNameTextView);
        mSecondNameTextView = findViewById(R.id.secondNameTextView);
        mAvatarImageView = findViewById(R.id.avatarImageView);
    }

    @Override
    public void bind(UserData userData) {
        mEmailTextView.setText(userData.getEmail());
        mFirstNameTextView.setText(userData.getFirstName());
        mSecondNameTextView.setText(userData.getLastName());

        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.ic_image_placeholder)
                .error(R.drawable.ic_image_download_failed)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .priority(Priority.HIGH);

        Glide.with(mAvatarImageView.getContext())
                .load(userData.getAvatar())
                .apply(options)
                .into(mAvatarImageView);

        for (Observer observer : getObservers()) {
            observer.getBottomNavigationViewVisibility();
        }
    }

    @Override
    public void onIsBottomNavigationViewVisible(boolean isVisible) {
        if (isVisible) {
            hideBottomNavigationView();
        }
    }

    private void hideBottomNavigationView() {
        for (Observer observer : getObservers()) {
            observer.hideBottomNavigationView();
        }
    }

}
