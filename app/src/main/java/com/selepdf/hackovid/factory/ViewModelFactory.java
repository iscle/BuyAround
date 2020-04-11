package com.selepdf.hackovid.factory;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.selepdf.hackovid.network.HackovidRepository;
import com.selepdf.hackovid.viewmodel.AccountViewModel;
import com.selepdf.hackovid.viewmodel.AddProductViewModel;
import com.selepdf.hackovid.viewmodel.AddStoreViewModel;
import com.selepdf.hackovid.viewmodel.AddressViewModel;
import com.selepdf.hackovid.viewmodel.AddressesViewModel;
import com.selepdf.hackovid.viewmodel.CategoriesViewModel;
import com.selepdf.hackovid.viewmodel.HomeViewModel;
import com.selepdf.hackovid.viewmodel.LoginViewModel;
import com.selepdf.hackovid.viewmodel.OrdersViewModel;
import com.selepdf.hackovid.viewmodel.PaymentViewModel;
import com.selepdf.hackovid.viewmodel.PersonalInfoViewModel;
import com.selepdf.hackovid.viewmodel.RegisterViewModel;
import com.selepdf.hackovid.viewmodel.SearchViewModel;
import com.selepdf.hackovid.viewmodel.UseConditionsViewModel;

import javax.inject.Inject;

import dagger.Module;

@Module
public class ViewModelFactory implements ViewModelProvider.Factory {

    private HackovidRepository hackovidRepository;

    @Inject
    public ViewModelFactory(HackovidRepository hackovidRepository) {
        this.hackovidRepository = hackovidRepository;
    }

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(LoginViewModel.class)) {
            return (T) new LoginViewModel(hackovidRepository);
        }
        if (modelClass.isAssignableFrom(HomeViewModel.class)) {
            return (T) new HomeViewModel(hackovidRepository);
        }
        if (modelClass.isAssignableFrom(SearchViewModel.class)) {
            return (T) new SearchViewModel(hackovidRepository);
        }
        if (modelClass.isAssignableFrom(OrdersViewModel.class)) {
            return (T) new OrdersViewModel(hackovidRepository);
        }
        if (modelClass.isAssignableFrom(RegisterViewModel.class)) {
            return (T) new RegisterViewModel(hackovidRepository);
        }
        if (modelClass.isAssignableFrom(AccountViewModel.class)) {
            return (T) new AccountViewModel(hackovidRepository);
        }
        if (modelClass.isAssignableFrom(PersonalInfoViewModel.class)) {
            return (T) new PersonalInfoViewModel(hackovidRepository);
        }
        if (modelClass.isAssignableFrom(AddressesViewModel.class)) {
            return (T) new AddressesViewModel(hackovidRepository);
        }
        if (modelClass.isAssignableFrom(PaymentViewModel.class)) {
            return (T) new PaymentViewModel(hackovidRepository);
        }
        if (modelClass.isAssignableFrom(UseConditionsViewModel.class)) {
            return (T) new UseConditionsViewModel(hackovidRepository);
        }
        if (modelClass.isAssignableFrom(AddProductViewModel.class)) {
            return (T) new AddProductViewModel(hackovidRepository);
        }
        if (modelClass.isAssignableFrom(AddStoreViewModel.class)) {
            return (T) new AddStoreViewModel(hackovidRepository);
        }
        if (modelClass.isAssignableFrom(AddressViewModel.class)) {
            return (T) new AddressViewModel(hackovidRepository);
        }
        if (modelClass.isAssignableFrom(CategoriesViewModel.class)) {
            return (T) new CategoriesViewModel(hackovidRepository);
        }

        throw new IllegalArgumentException("Unknown class!");
    }
}
