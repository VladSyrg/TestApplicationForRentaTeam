package com.syrgdev.testapplicationforrentateam.users.view.usersitem;


import com.syrgdev.testapplicationforrentateam.common.UserData;
import com.syrgdev.testapplicationforrentateam.common.view.base.BaseObservableView;

public abstract class UsersItemView
        extends BaseObservableView<UsersItemView.Observer> {

    public interface Observer {

        void onItemClicked(UserData userData);

    }

    public abstract void bind(UserData userData);
}
