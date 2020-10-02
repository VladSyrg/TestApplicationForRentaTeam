package com.syrgdev.testapplicationforrentateam.about.view;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.syrgdev.testapplicationforrentateam.R;

public class AboutViewImpl extends AboutView {

    Toolbar mToolbar;
    TextView mAboutTextView;

    public AboutViewImpl(LayoutInflater layoutInflater, ViewGroup container) {

        setRootView(layoutInflater.inflate(R.layout.view_about, container, false));
        mToolbar = findViewById(R.id.toolbar);
        mToolbar.setTitle(R.string.title_about);
        mAboutTextView = findViewById(R.id.aboutTextView);
    }
}
