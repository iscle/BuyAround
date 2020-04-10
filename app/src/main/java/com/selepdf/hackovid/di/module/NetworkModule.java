package com.selepdf.hackovid.di.module;

import com.google.gson.Gson;
import com.selepdf.hackovid.HackovidApplication;
import com.selepdf.hackovid.network.TokenInterceptor;
import com.selepdf.hackovid.network.HackovidService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    @Singleton
    @Provides
    public static OkHttpClient provideOkHttpClient(TokenInterceptor interceptor) {
        return new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();
    }

    @Singleton
    @Provides
    public static HackovidService provideHackovidService(OkHttpClient client, Gson gson) {
        return new Retrofit.Builder()
                .baseUrl(HackovidApplication.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build()
                .create(HackovidService.class);
    }
}
