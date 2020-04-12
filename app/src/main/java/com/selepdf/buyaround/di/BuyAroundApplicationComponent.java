package com.selepdf.buyaround.di;

import android.content.Context;

import com.selepdf.buyaround.BuyAroundApplication;
import com.selepdf.buyaround.di.module.BuyAroundModule;
import com.selepdf.buyaround.di.module.FragmentModule;
import com.selepdf.buyaround.di.module.NetworkModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;

@Singleton
@Component(modules = {
        AndroidInjectionModule.class,
        BuyAroundModule.class,
        NetworkModule.class,
        FragmentModule.class,
})
public interface BuyAroundApplicationComponent extends AndroidInjector<BuyAroundApplication> {

    @Component.Factory
    interface Factory {
        BuyAroundApplicationComponent create(@BindsInstance Context context);
    }
}
