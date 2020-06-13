package cat.buyaround.app.di.module;

import android.content.Context;
import android.util.DisplayMetrics;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import cat.buyaround.app.network.ResponseStatusDeserializer;
import cat.buyaround.app.network.model.Response;
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
