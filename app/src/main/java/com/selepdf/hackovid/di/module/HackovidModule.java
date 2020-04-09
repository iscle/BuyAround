package com.selepdf.hackovid.di.module;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.selepdf.hackovid.model.ResponseStatus;
import com.selepdf.hackovid.network.ResponseStatusDeserializer;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class HackovidModule {

    @Provides
    @Singleton
    public static ResponseStatusDeserializer provideResponseStatusDeserializer() {
        return new ResponseStatusDeserializer();
    }

    @Provides
    @Singleton
    public static Gson provideGson(ResponseStatusDeserializer responseStatusDeserializer) {
        return new GsonBuilder()
                .registerTypeAdapter(ResponseStatus.class, responseStatusDeserializer)
                .create();
    }
}
