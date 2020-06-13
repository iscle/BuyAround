package cat.buyaround.app.factory;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import cat.buyaround.app.auth.UserManager;
import cat.buyaround.app.network.BuyAroundRepository;
import cat.buyaround.app.viewmodel.AccountViewModel;
import cat.buyaround.app.viewmodel.AddProductViewModel;
import cat.buyaround.app.viewmodel.AddStoreViewModel;
import cat.buyaround.app.viewmodel.AddressViewModel;
import cat.buyaround.app.viewmodel.AddressesViewModel;
import cat.buyaround.app.viewmodel.CartViewModel;
import cat.buyaround.app.viewmodel.CategoriesViewModel;
import cat.buyaround.app.viewmodel.FavouritesViewModel;
import cat.buyaround.app.viewmodel.HomeViewModel;
import cat.buyaround.app.viewmodel.LocationViewModel;
import cat.buyaround.app.viewmodel.LoginViewModel;
import cat.buyaround.app.viewmodel.NotificationsViewModel;
import cat.buyaround.app.viewmodel.OrdersViewModel;
import cat.buyaround.app.viewmodel.PaymentViewModel;
import cat.buyaround.app.viewmodel.PersonalInfoViewModel;
import cat.buyaround.app.viewmodel.RegisterViewModel;
import cat.buyaround.app.viewmodel.SearchViewModel;
import cat.buyaround.app.viewmodel.UseConditionsViewModel;

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
