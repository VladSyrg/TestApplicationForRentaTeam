package com.syrgdev.testapplicationforrentateam.persistance;

import android.annotation.SuppressLint;

import com.syrgdev.testapplicationforrentateam.common.Error;
import com.syrgdev.testapplicationforrentateam.common.UserData;
import com.syrgdev.testapplicationforrentateam.network.RentaTeamTestAPI;
import com.syrgdev.testapplicationforrentateam.network.UserSchema;
import com.syrgdev.testapplicationforrentateam.persistance.room.Database;
import com.syrgdev.testapplicationforrentateam.persistance.room.UserEntity;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class PersistenceManagerImpl extends PersistenceManager {

    RentaTeamTestAPI mRentaTeamTestAPI;

    Database mDatabase;

    public PersistenceManagerImpl(RentaTeamTestAPI rentaTeamTestAPI, Database database) {
        mRentaTeamTestAPI = rentaTeamTestAPI;
        mDatabase = database;
    }

    @Override
    public void fetchUsers(boolean isInternetAvailable) {
        fetchFromDb(isInternetAvailable);
    }

    public void fetchFromDb(boolean isInternetAvailable) {
        getDatabase().userEntityDao()
                .fetchAll()
                .map(listUserEntity -> {
                    List<UserData> userDataList = new ArrayList<>();
                    for (UserEntity entity : listUserEntity) {
                        userDataList.add(Converters.toUserData(entity));
                    }
                    return userDataList;
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<UserData>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(List<UserData> userDataList) {
                        if (userDataList.isEmpty()) {
                            fetchFromApi(isInternetAvailable);
                        } else {
                            for (Observer observer : getObservers()) {
                                observer.onUsersFetched(userDataList);
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        for (Observer observer : getObservers()) {
                            observer.onError(new Error(e, "Ошибка при обращении к базе данных."));
                        }
                    }
                });
    }

    private void fetchFromApi(boolean isInternetAvailable) {
        if (isInternetAvailable) {
            mRentaTeamTestAPI.getUsers()
                    .map(responseSchema -> new ArrayList<>(responseSchema.getData()))
                    .map(userSchemaList -> {
                        List<UserData> userDataListFromAPI = new ArrayList<>();
                        for (UserSchema userSchema : userSchemaList) {
                            userDataListFromAPI.add(Converters.toUserData(userSchema));
                        }
                        return userDataListFromAPI;
                    })
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new SingleObserver<List<UserData>>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @SuppressLint("CheckResult")
                        @Override
                        public void onSuccess(List<UserData> userDataList) {

                            for (Observer observer : getObservers()) {
                                observer.onUsersFetched(userDataList);
                                List<UserEntity> entities = new ArrayList<>();
                                for (UserData userData : userDataList) {
                                    entities.add(Converters.toUserEntity(userData));
                                }
                                //Сохраняем полученные данные в базу данных.
                                Completable.fromAction(
                                        () -> getDatabase()
                                                .userEntityDao()
                                                .insert(entities))
                                        .subscribeOn(Schedulers.io())
                                        .observeOn(AndroidSchedulers.mainThread())
                                        .subscribe(new CompletableObserver() {
                                            @Override
                                            public void onSubscribe(Disposable d) {

                                            }

                                            @Override
                                            public void onComplete() {

                                            }

                                            @Override
                                            public void onError(Throwable e) {
                                                for (Observer observer : getObservers()) {
                                                    observer.onError(new Error(e, "Ошибка при записи данных в базу данных."));
                                                }
                                            }
                                        });
                            }

                        }

                        @Override
                        public void onError(Throwable e) {
                            for (Observer observer : getObservers()) {
                                observer.onError(new Error(e, "Ошибка при обращении к серверу."));
                            }
                        }
                    });
        } else {
            for (Observer observer : getObservers()) {
                observer.noInternetConnection();
            }
        }

    }

    public Database getDatabase() {
        return mDatabase;
    }

}