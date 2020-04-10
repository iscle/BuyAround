package com.selepdf.hackovid.di.module;

import android.accounts.Account;

import com.selepdf.hackovid.fragment.AccountFragment;
import com.selepdf.hackovid.fragment.AddressesFragment;
import com.selepdf.hackovid.fragment.HomeFragment;
import com.selepdf.hackovid.fragment.LoginFragment;
import com.selepdf.hackovid.fragment.OrdersFragment;
import com.selepdf.hackovid.fragment.PaymentFragment;
import com.selepdf.hackovid.fragment.PersonalInfoFragment;
import com.selepdf.hackovid.fragment.ProductFragment;
import com.selepdf.hackovid.fragment.RegisterFragment;
import com.selepdf.hackovid.fragment.SearchFragment;
import com.selepdf.hackovid.fragment.UseConditionsFragment;

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
    abstract ProductFragment productFragment();
}
