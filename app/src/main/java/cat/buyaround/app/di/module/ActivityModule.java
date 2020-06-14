package cat.buyaround.app.di.module;

import cat.buyaround.app.activity.MainActivity;
import cat.buyaround.app.activity.SplashActivity;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityModule {
    @ContributesAndroidInjector
    abstract SplashActivity splashActivity();

    @ContributesAndroidInjector
    abstract MainActivity mainActivity();
}
