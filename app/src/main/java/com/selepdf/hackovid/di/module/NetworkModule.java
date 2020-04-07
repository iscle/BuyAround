package com.selepdf.hackovid.di.module;

import com.selepdf.hackovid.HackovidApplication;
import com.selepdf.hackovid.service.HackovidService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class NetworkModule {

    @Singleton
    @Provides
    public HackovidService provideHackovidService() {
        return new Retrofit.Builder()
                .baseUrl(HackovidApplication.BASE_URL)
                .build()
                .create(HackovidService.class);
    }
}
