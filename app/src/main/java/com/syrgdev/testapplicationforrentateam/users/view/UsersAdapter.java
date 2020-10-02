package com.syrgdev.testapplicationforrentateam.users.view;


import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.syrgdev.testapplicationforrentateam.common.UserData;
import com.syrgdev.testapplicationforrentateam.common.view.ViewFactory;
import com.syrgdev.testapplicationforrentateam.users.view.usersitem.UsersItemView;

import java.util.ArrayList;
import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.ViewHolder>
        implements UsersItemView.Observer {


    public interface Observer {

        void onItemClicked(UserData userData);

    }

    List<UserData> mUserDataList = new ArrayList<>();

    private ViewFactory mViewFactory;
    private Observer mObserver;

    public UsersAdapter(ViewFactory viewFactory, Observer observer) {
        this.mViewFactory = viewFactory;
        this.mObserver = observer;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        UsersItemView usersItemView = mViewFactory.newViewInstance(UsersItemView.class, parent);
        usersItemView.registerObserver(this);
        return new ViewHolder(usersItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        UserData userData = mUserDataList.get(position);
        holder.mUsersItemView.bind(userData);
    }

    @Override
    public int getItemCount() {
        return mUserDataList.size();
    }

    public void setUsers(List<UserData> userDataList) {
        mUserDataList = new ArrayList<>(userDataList);
        notifyDataSetChanged();
    }

    public void onItemClicked(UserData userData) {
        mObserver.onItemClicked(userData);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public UsersItemView mUsersItemView;

        public ViewHolder(UsersItemView usersItemView) {
            super(usersItemView.getRootView());
            mUsersItemView = usersItemView;
        }

    }

}