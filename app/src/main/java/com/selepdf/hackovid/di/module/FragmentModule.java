package com.selepdf.hackovid.di.module;

import com.selepdf.hackovid.fragment.LoginFragment;
import com.selepdf.hackovid.fragment.RegisterFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract RegisterFragment registerFragment();

    @ContributesAndroidInjector
    abstract LoginFragment loginFragment();

}
