package com.selepdf.hackovid;

import com.selepdf.hackovid.di.HackovidApplicationComponent;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

public class HackovidApplication extends DaggerApplication {
    public static final String BASE_URL = "https://hackovid.selepdf.com";

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return HackovidApplicationComponent.factory().create(getApplicationContext());
    }
}
