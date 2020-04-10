package com.selepdf.hackovid.factory;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.selepdf.hackovid.repository.HackovidRepository;
import com.selepdf.hackovid.viewmodel.AccountViewModel;
import com.selepdf.hackovid.viewmodel.AddressesViewModel;
import com.selepdf.hackovid.viewmodel.HomeViewModel;
import com.selepdf.hackovid.viewmodel.LoginViewModel;
import com.selepdf.hackovid.viewmodel.OrdersViewModel;
import com.selepdf.hackovid.viewmodel.PaymentViewModel;
import com.selepdf.hackovid.viewmodel.PersonalInfoViewModel;
import com.selepdf.hackovid.viewmodel.RegisterViewModel;
import com.selepdf.hackovid.viewmodel.SearchViewModel;

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

        throw new IllegalArgumentException("Unknown class!");
    }
}
