package com.selepdf.buyaround.di.module;

import com.google.gson.Gson;
import com.selepdf.buyaround.BuyAroundApplication;
import com.selepdf.buyaround.network.BuyAroundService;
import com.selepdf.buyaround.network.TokenInterceptor;

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
    public static BuyAroundService provideHackovidService(OkHttpClient client, Gson gson) {
        return new Retrofit.Builder()
                .baseUrl(BuyAroundApplication.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build()
                .create(BuyAroundService.class);
    }
}
