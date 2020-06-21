package cat.buyaround.app.di.module;

import android.content.Context;
import android.util.DisplayMetrics;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Date;

import javax.inject.Singleton;

import cat.buyaround.app.network.ResponseStatusDeserializer;
import cat.buyaround.app.network.model.SimpleResponse;
import cat.buyaround.app.network.serialization.DateTypeAdapter;
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
    public static DateTypeAdapter provideDateTypeAdapter() {
        return new DateTypeAdapter();
    }

    @Provides
    @Singleton
    public static Gson provideGson(ResponseStatusDeserializer responseStatusDeserializer, DateTypeAdapter dateTypeAdapter) {
        return new GsonBuilder()
                .registerTypeAdapter(SimpleResponse.Status.class, responseStatusDeserializer)
                .registerTypeAdapter(Date.class, dateTypeAdapter)
                .create();
    }
}
