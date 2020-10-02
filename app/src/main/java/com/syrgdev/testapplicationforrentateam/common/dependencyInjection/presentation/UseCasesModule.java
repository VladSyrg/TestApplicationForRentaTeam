package com.syrgdev.testapplicationforrentateam.common.dependencyInjection.presentation;

import com.syrgdev.testapplicationforrentateam.persistance.PersistenceManager;
import com.syrgdev.testapplicationforrentateam.users.usecase.FetchUsersUseCase;

import dagger.Module;
import dagger.Provides;


@Module
public class UseCasesModule {

    @Provides
    FetchUsersUseCase getFetchUsersUseCase(PersistenceManager persistenceManager) {
        return new FetchUsersUseCase(persistenceManager);
    }

}
