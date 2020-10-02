package com.syrgdev.testapplicationforrentateam.common.dependencyInjection.presentation;


import com.syrgdev.testapplicationforrentateam.common.presenter.MainActivity;
import com.syrgdev.testapplicationforrentateam.user.presenter.UserFragment;
import com.syrgdev.testapplicationforrentateam.users.presenter.UsersFragment;
import com.syrgdev.testapplicationforrentateam.about.presenter.AboutFragment;

import dagger.Subcomponent;

@Subcomponent(modules = {PresentationModule.class,UseCasesModule.class})
public interface PresentationComponent {

    void inject(MainActivity mainActivity);

    void inject(UsersFragment usersFragment);

    void inject(UserFragment userFragment);

    void inject(AboutFragment aboutFragment);

}
