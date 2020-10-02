package com.syrgdev.testapplicationforrentateam.user.view;


import com.syrgdev.testapplicationforrentateam.common.UserData;
import com.syrgdev.testapplicationforrentateam.common.view.base.BaseObservableView;

public abstract class UserView extends BaseObservableView<UserView.Observer> {

    public interface Observer {

        void onNavigateBackPressed();

        void getBottomNavigationViewVisibility();

        void hideBottomNavigationView();
    }

    public abstract void bind(UserData userData);

    public abstract void onIsBottomNavigationViewVisible(boolean isVisible);

}
