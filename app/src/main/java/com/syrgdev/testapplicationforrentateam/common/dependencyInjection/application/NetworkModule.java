package com.syrgdev.testapplicationforrentateam.common.dependencyInjection.application;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.syrgdev.testapplicationforrentateam.network.RentaTeamTestAPI;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.syrgdev.testapplicationforrentateam.BuildConfig.BASE_URL;

@Module
public class NetworkModule {

    @Provides
    Gson getGson() {
        return new GsonBuilder()
                .create();
    }

    @Singleton
    @Provides
    Retrofit getRetrofit(Gson gson) {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Singleton
    @Provides
    RentaTeamTestAPI getRentaTeamTestAPI(Retrofit retrofit) {
        return retrofit.create(RentaTeamTestAPI.class);
    }

}
