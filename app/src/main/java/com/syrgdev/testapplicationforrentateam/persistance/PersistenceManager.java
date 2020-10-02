package com.syrgdev.testapplicationforrentateam.persistance;


import com.syrgdev.testapplicationforrentateam.common.Error;
import com.syrgdev.testapplicationforrentateam.common.UserData;
import com.syrgdev.testapplicationforrentateam.common.observable.BaseObservable;

import java.util.List;

public abstract class PersistenceManager
        extends BaseObservable<PersistenceManager.Observer> {

    public interface Observer {

        void onUsersFetched(List<UserData> userDataList);

        void noInternetConnection();

        void onError(Error error);

    }

    public abstract void fetchUsers(boolean isInternetAvailable);

}
