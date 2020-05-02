package com.selepdf.buyaround.factory;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.selepdf.buyaround.auth.UserManager;
import com.selepdf.buyaround.network.BuyAroundRepository;
import com.selepdf.buyaround.viewmodel.AccountViewModel;
import com.selepdf.buyaround.viewmodel.AddProductViewModel;
import com.selepdf.buyaround.viewmodel.AddStoreViewModel;
import com.selepdf.buyaround.viewmodel.AddressViewModel;
import com.selepdf.buyaround.viewmodel.AddressesViewModel;
import com.selepdf.buyaround.viewmodel.CartViewModel;
import com.selepdf.buyaround.viewmodel.CategoriesViewModel;
import com.selepdf.buyaround.viewmodel.FavouritesViewModel;
import com.selepdf.buyaround.viewmodel.HomeViewModel;
import com.selepdf.buyaround.viewmodel.LocationViewModel;
import com.selepdf.buyaround.viewmodel.LoginViewModel;
import com.selepdf.buyaround.viewmodel.NotificationsViewModel;
import com.selepdf.buyaround.viewmodel.OrdersViewModel;
import com.selepdf.buyaround.viewmodel.PaymentViewModel;
import com.selepdf.buyaround.viewmodel.PersonalInfoViewModel;
import com.selepdf.buyaround.viewmodel.RegisterViewModel;
import com.selepdf.buyaround.viewmodel.SearchViewModel;
import com.selepdf.buyaround.viewmodel.UseConditionsViewModel;

import javax.inject.Inject;

import dagger.Module;

@Module
public class ViewModelFactory implements ViewModelProvider.Factory {

    private BuyAroundRepository buyAroundRepository;
    private UserManager userManager;

    @Inject
    public ViewModelFactory(BuyAroundRepository buyAroundRepository, UserManager userManager) {
        this.buyAroundRepository = buyAroundRepository;
        this.userManager = userManager;
    }

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(LoginViewModel.class)) {
            return (T) new LoginViewModel(buyAroundRepository);
        }
        if (modelClass.isAssignableFrom(HomeViewModel.class)) {
            return (T) new HomeViewModel(buyAroundRepository);
        }
        if (modelClass.isAssignableFrom(SearchViewModel.class)) {
            return (T) new SearchViewModel(buyAroundRepository);
        }
        if (modelClass.isAssignableFrom(OrdersViewModel.class)) {
            return (T) new OrdersViewModel(buyAroundRepository);
        }
        if (modelClass.isAssignableFrom(RegisterViewModel.class)) {
            return (T) new RegisterViewModel(buyAroundRepository);
        }
        if (modelClass.isAssignableFrom(AccountViewModel.class)) {
            return (T) new AccountViewModel(buyAroundRepository);
        }
        if (modelClass.isAssignableFrom(PersonalInfoViewModel.class)) {
            return (T) new PersonalInfoViewModel(buyAroundRepository);
        }
        if (modelClass.isAssignableFrom(AddressesViewModel.class)) {
            return (T) new AddressesViewModel(buyAroundRepository);
        }
        if (modelClass.isAssignableFrom(PaymentViewModel.class)) {
            return (T) new PaymentViewModel(buyAroundRepository);
        }
        if (modelClass.isAssignableFrom(UseConditionsViewModel.class)) {
            return (T) new UseConditionsViewModel(buyAroundRepository);
        }
        if (modelClass.isAssignableFrom(AddProductViewModel.class)) {
            return (T) new AddProductViewModel(buyAroundRepository);
        }
        if (modelClass.isAssignableFrom(AddStoreViewModel.class)) {
            return (T) new AddStoreViewModel(buyAroundRepository);
        }
        if (modelClass.isAssignableFrom(AddressViewModel.class)) {
            return (T) new AddressViewModel(buyAroundRepository);
        }
        if (modelClass.isAssignableFrom(CategoriesViewModel.class)) {
            return (T) new CategoriesViewModel(buyAroundRepository);
        }
        if (modelClass.isAssignableFrom(NotificationsViewModel.class)) {
            return (T) new NotificationsViewModel(buyAroundRepository);
        }
        if (modelClass.isAssignableFrom(FavouritesViewModel.class)) {
            return (T) new FavouritesViewModel(buyAroundRepository);
        }
        if (modelClass.isAssignableFrom(CartViewModel.class)) {
            return (T) new CartViewModel(buyAroundRepository, userManager);
        }
        if (modelClass.isAssignableFrom(LocationViewModel.class)) {
            return (T) new LocationViewModel(buyAroundRepository);
        }

        throw new IllegalArgumentException("Unknown class!");
    }
}
