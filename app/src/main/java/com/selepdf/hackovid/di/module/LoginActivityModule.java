package com.selepdf.hackovid.di.module;

import com.selepdf.hackovid.factory.ViewModelFactory;
import com.selepdf.hackovid.view.LoginActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class LoginActivityModule {

    @ContributesAndroidInjector(modules = {ViewModelFactory.class})
    abstract LoginActivity loginActivity();
}
