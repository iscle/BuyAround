package com.selepdf.hackovid.di.module;

import com.google.gson.Gson;
import com.selepdf.hackovid.HackovidApplication;
import com.selepdf.hackovid.auth.TokenManager;
import com.selepdf.hackovid.network.TokenInterceptor;
import com.selepdf.hackovid.service.HackovidService;

import javax.inject.Singleton;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

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
