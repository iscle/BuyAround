package com.selepdf.hackovid.di;

import android.content.Context;

import com.selepdf.hackovid.HackovidApplication;
import com.selepdf.hackovid.di.module.FragmentModule;
import com.selepdf.hackovid.di.module.NetworkModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;

@Singleton
@Component(modules = {
        AndroidInjectionModule.class,
        NetworkModule.class,
        FragmentModule.class,
})
public interface HackovidApplicationComponent extends AndroidInjector<HackovidApplication> {

    @Component.Factory
    interface Factory {
        HackovidApplicationComponent create(@BindsInstance Context applicationContext);
    }
}
