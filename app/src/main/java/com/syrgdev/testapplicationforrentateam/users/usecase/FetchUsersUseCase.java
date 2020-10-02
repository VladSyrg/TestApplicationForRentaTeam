package com.syrgdev.testapplicationforrentateam.users.usecase;


import com.syrgdev.testapplicationforrentateam.common.Error;
import com.syrgdev.testapplicationforrentateam.common.UserData;
import com.syrgdev.testapplicationforrentateam.common.observable.BaseObservable;
import com.syrgdev.testapplicationforrentateam.persistance.PersistenceManager;

import java.util.List;

public class FetchUsersUseCase
        extends BaseObservable<FetchUsersUseCase.Observer>
        implements PersistenceManager.Observer {

    public interface Observer {

        void onUsersFetched(List<UserData> posts);

        void onError(Error error);

        void noInternetConnection();
    }

    PersistenceManager mPersistenceManager;

    public FetchUsersUseCase(PersistenceManager persistenceManager) {
        mPersistenceManager = persistenceManager;
    }

    public void onUsersFetched(List<UserData> users) {
        mPersistenceManager.unregisterObserver(this);
        for (Observer observer : getObservers()) {
            observer.onUsersFetched(users);
        }
    }

    @Override
    public void noInternetConnection() {
        for (Observer observer : getObservers()) {
            observer.noInternetConnection();
        }
    }

    @Override
    public void onError(Error error) {
        mPersistenceManager.unregisterObserver(this);
        for (Observer observer : getObservers()) {
            observer.onError(error);
        }

    }

    public void fetchUsers(boolean isInternetAvailable) {
        mPersistenceManager.registerObserver(this);
        mPersistenceManager.fetchUsers(isInternetAvailable);
    }

}
