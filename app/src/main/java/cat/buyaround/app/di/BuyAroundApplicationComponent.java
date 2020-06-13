package cat.buyaround.app.di;

import android.content.Context;

import cat.buyaround.app.BuyAroundApplication;
import cat.buyaround.app.di.module.ActivityModule;
import cat.buyaround.app.di.module.BuyAroundModule;
import cat.buyaround.app.di.module.FragmentModule;
import cat.buyaround.app.di.module.NetworkModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;

@Singleton
@Component(modules = {
        AndroidInjectionModule.class,
        BuyAroundModule.class,
        NetworkModule.class,
        FragmentModule.class,
        ActivityModule.class,
})
public interface BuyAroundApplicationComponent extends AndroidInjector<BuyAroundApplication> {

    @Component.Factory
    interface Factory {
        BuyAroundApplicationComponent create(@BindsInstance Context context);
    }
}
