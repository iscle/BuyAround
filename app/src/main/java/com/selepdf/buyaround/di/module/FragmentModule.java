package com.selepdf.buyaround.di.module;

import com.selepdf.buyaround.fragment.AccountFragment;
import com.selepdf.buyaround.fragment.AddStoreFragment;
import com.selepdf.buyaround.fragment.AddressFragment;
import com.selepdf.buyaround.fragment.AddressesFragment;
import com.selepdf.buyaround.fragment.CartFragment;
import com.selepdf.buyaround.fragment.CategoriesFragment;
import com.selepdf.buyaround.fragment.FavouritesFragment;
import com.selepdf.buyaround.fragment.HomeFragment;
import com.selepdf.buyaround.fragment.LoginFragment;
import com.selepdf.buyaround.fragment.NotificationsFragment;
import com.selepdf.buyaround.fragment.OrdersFragment;
import com.selepdf.buyaround.fragment.PayFragment;
import com.selepdf.buyaround.fragment.PaymentFragment;
import com.selepdf.buyaround.fragment.PersonalInfoFragment;
import com.selepdf.buyaround.fragment.AddProductFragment;
import com.selepdf.buyaround.fragment.ProductFragment;
import com.selepdf.buyaround.fragment.RegisterFragment;
import com.selepdf.buyaround.fragment.SearchFragment;
import com.selepdf.buyaround.fragment.UseConditionsFragment;

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
    abstract UseConditionsFragment useConditionsFragment();

    @ContributesAndroidInjector
    abstract AddProductFragment addProductFragment();

    @ContributesAndroidInjector
    abstract AddStoreFragment storeFragment();

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
}
