package cat.buyaround.app;

import cat.buyaround.app.di.DaggerBuyAroundApplicationComponent;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

public class BuyAroundApplication extends DaggerApplication {
    public static final String BASE_URL = "https://hackovid.selepdf.com/api/";

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerBuyAroundApplicationComponent.factory().create(getApplicationContext());
    }
}
