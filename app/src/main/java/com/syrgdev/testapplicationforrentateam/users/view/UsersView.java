package com.syrgdev.testapplicationforrentateam.users.view;

import com.syrgdev.testapplicationforrentateam.common.Error;
import com.syrgdev.testapplicationforrentateam.common.UserData;
import com.syrgdev.testapplicationforrentateam.common.view.base.BaseObservableView;

import java.util.List;

public abstract class UsersView extends BaseObservableView<UsersView.Observer> {

    public interface Observer {

        void onItemClicked(UserData userData);

        void onRetryButtonClicked();

        void getBottomNavigationViewVisibility();

        void showBottomNavigationView();
    }

    public abstract void fetchUsersStarted();

    public abstract void onUsersFetched(List<UserData> userDataList);

    public abstract void onError(Error error);

    public abstract void noInternetConnection();

    public abstract void onIsBottomNavigationViewVisible(boolean isVisible);

}
