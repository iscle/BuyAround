package com.selepdf.buyaround.di.module;

import android.content.Context;
import android.util.DisplayMetrics;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.selepdf.buyaround.network.ResponseStatusDeserializer;
import com.selepdf.buyaround.network.model.Response;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class BuyAroundModule {

    @Provides
    @Singleton
    public static DisplayMetrics provideDisplayMetrics(Context context) {
        return context.getResources().getDisplayMetrics();
    }

    @Provides
    @Singleton
    public static ResponseStatusDeserializer provideResponseStatusDeserializer() {
        return new ResponseStatusDeserializer();
    }

    @Provides
    @Singleton
    public static Gson provideGson(ResponseStatusDeserializer responseStatusDeserializer) {
        return new GsonBuilder()
                .registerTypeAdapter(Response.Status.class, responseStatusDeserializer)
                .create();
    }
}
