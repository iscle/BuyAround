package com.selepdf.hackovid.di.module;

import com.selepdf.hackovid.HackovidApplication;
import com.selepdf.hackovid.service.HackovidService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    @Singleton
    @Provides
    public static Interceptor provideInterceptor() {
        return chain -> {
            Request request = chain.request()
                    .newBuilder()
                    .addHeader("Authorization", "NoTokenYet") // TODO: Change this
                    .build();

            return chain.proceed(request);
        };
    }

    @Singleton
    @Provides
    public static OkHttpClient provideOkHttpClient(Interceptor interceptor) {
        return new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();
    }

    @Singleton
    @Provides
    public static HackovidService provideHackovidService(OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl(HackovidApplication.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
                .create(HackovidService.class);
    }
}
