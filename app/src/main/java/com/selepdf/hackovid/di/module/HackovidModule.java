package com.selepdf.hackovid.di.module;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.selepdf.hackovid.LoginResponseStatusDeserializer;
import com.selepdf.hackovid.model.LoginResponse;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class HackovidModule {

    @Provides
    @Singleton
    public static LoginResponseStatusDeserializer provideLoginResponseStatusDeserializer() {
        return new LoginResponseStatusDeserializer();
    }

    @Provides
    @Singleton
    public static Gson provideGson(LoginResponseStatusDeserializer loginResponseStatusDeserializer) {
        return new GsonBuilder()
                .registerTypeAdapter(LoginResponse.Status.class, loginResponseStatusDeserializer)
                .create();
    }
}
