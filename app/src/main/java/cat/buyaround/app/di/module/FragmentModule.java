package cat.buyaround.app.di.module;

import cat.buyaround.app.fragment.AccountFragment;
import cat.buyaround.app.fragment.AddProductFragment;
import cat.buyaround.app.fragment.AddStoreFragment;
import cat.buyaround.app.fragment.AddressFragment;
import cat.buyaround.app.fragment.AddressesFragment;
import cat.buyaround.app.fragment.CartFragment;
import cat.buyaround.app.fragment.CategoriesFragment;
import cat.buyaround.app.fragment.FavouritesFragment;
import cat.buyaround.app.fragment.HomeFragment;
import cat.buyaround.app.fragment.LocationFragment;
import cat.buyaround.app.fragment.LoginFragment;
import cat.buyaround.app.fragment.NotificationsFragment;
import cat.buyaround.app.fragment.OrdersFragment;
import cat.buyaround.app.fragment.PayFragment;
import cat.buyaround.app.fragment.PaymentFragment;
import cat.buyaround.app.fragment.PersonalInfoFragment;
import cat.buyaround.app.fragment.ProductFragment;
import cat.buyaround.app.fragment.RegisterFragment;
import cat.buyaround.app.fragment.SearchFragment;
import cat.buyaround.app.fragment.StoreFragment;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract RegisterFragment registerFragment();

    @ContributesAndroidInjector
    abstract LoginFragment loginFragment();

    @ContributesAndroidInjector
    abstract HomeFragment homeFragment();

    @ContributesAndroidInjector
    abstract SearchFragment searchFragment();

    @ContributesAndroidInjector
    abstract OrdersFragment ordersFragment();

    @ContributesAndroidInjector
    abstract AccountFragment accountFragment();

    @ContributesAndroidInjector
    abstract PersonalInfoFragment personalInfoFragment();

    @ContributesAndroidInjector
    abstract AddressesFragment addressesFragment();

    @ContributesAndroidInjector
    abstract PaymentFragment paymentFragment();

    @ContributesAndroidInjector
    abstract AddProductFragment addProductFragment();

    @ContributesAndroidInjector
    abstract AddStoreFragment addStoreFragment();

    @ContributesAndroidInjector
    abstract AddressFragment addressFragment();

    @ContributesAndroidInjector
    abstract CategoriesFragment categoriesFragment();

    @ContributesAndroidInjector
    abstract NotificationsFragment notificationsFragment();

    @ContributesAndroidInjector
    abstract FavouritesFragment favouritesFragment();

    @ContributesAndroidInjector
    abstract CartFragment cartFragment();

    @ContributesAndroidInjector
    abstract ProductFragment productFragment();

    @ContributesAndroidInjector
    abstract PayFragment payFragment();

    @ContributesAndroidInjector
    abstract LocationFragment locationFragment();

    @ContributesAndroidInjector
    abstract StoreFragment storeFragment();

}
