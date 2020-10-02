package com.syrgdev.testapplicationforrentateam.common.view;


import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.syrgdev.testapplicationforrentateam.about.view.AboutView;
import com.syrgdev.testapplicationforrentateam.about.view.AboutViewImpl;
import com.syrgdev.testapplicationforrentateam.common.view.base.IView;
import com.syrgdev.testapplicationforrentateam.common.view.main.MainActivityView;
import com.syrgdev.testapplicationforrentateam.common.view.main.MainActivityViewImpl;
import com.syrgdev.testapplicationforrentateam.user.view.UserView;
import com.syrgdev.testapplicationforrentateam.user.view.UserViewImpl;
import com.syrgdev.testapplicationforrentateam.users.view.UsersView;
import com.syrgdev.testapplicationforrentateam.users.view.UsersViewImpl;
import com.syrgdev.testapplicationforrentateam.users.view.usersitem.UsersItemView;
import com.syrgdev.testapplicationforrentateam.users.view.usersitem.UsersItemViewImpl;

public class ViewFactory {

    private final LayoutInflater mLayoutInflater;

    public ViewFactory(LayoutInflater layoutInflater) {
        mLayoutInflater = layoutInflater;
    }

    public <T extends IView> T newViewInstance(Class<T> viewClass, @Nullable ViewGroup container) {

        IView view;

        if (viewClass.isAssignableFrom(UsersView.class)) {
            view = new UsersViewImpl(mLayoutInflater, container, this);
        } else if (viewClass.isAssignableFrom(MainActivityView.class)) {
            view = new MainActivityViewImpl(mLayoutInflater, container);
        } else if (viewClass.isAssignableFrom(UserView.class)) {
            view = new UserViewImpl(mLayoutInflater, container);
        } else if (viewClass.isAssignableFrom(UsersItemView.class)) {
            view = new UsersItemViewImpl(mLayoutInflater, container);
        } else if (viewClass.isAssignableFrom(AboutView.class)) {
            view = new AboutViewImpl(mLayoutInflater, container);
        } else {
            throw new IllegalArgumentException("unsupported view class: " + viewClass);
        }

        //noinspection unchecked
        return (T) view;
    }

}
