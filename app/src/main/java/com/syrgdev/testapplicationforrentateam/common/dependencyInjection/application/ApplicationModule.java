package com.syrgdev.testapplicationforrentateam.common.dependencyInjection.application;

import android.app.Application;
import android.content.Context;

import androidx.room.Room;

import com.syrgdev.testapplicationforrentateam.common.ScreensManager;
import com.syrgdev.testapplicationforrentateam.network.RentaTeamTestAPI;
import com.syrgdev.testapplicationforrentateam.persistance.PersistenceManager;
import com.syrgdev.testapplicationforrentateam.persistance.PersistenceManagerImpl;
import com.syrgdev.testapplicationforrentateam.persistance.room.Database;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

import static com.syrgdev.testapplicationforrentateam.BuildConfig.DB_NAME;

@Module
public class ApplicationModule {

    private final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    public Context getContext() {
        return mApplication;
    }

    @Singleton
    @Provides
    ScreensManager getScreensManager() {
        return new ScreensManager();
    }

    @Singleton
    @Provides
    PersistenceManager getPersistenceManager(RentaTeamTestAPI rentaTeamTestAPI, Database database) {
        return new PersistenceManagerImpl(rentaTeamTestAPI, database);
    }

    @Singleton
    @Provides
    Database getDatabase(Context context) {
        return Room.databaseBuilder(context, Database.class, DB_NAME).build();
    }

}
