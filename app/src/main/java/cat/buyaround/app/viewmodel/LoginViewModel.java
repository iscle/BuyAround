package cat.buyaround.app.viewmodel;

import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import cat.buyaround.app.callback.LoginCallback;
import cat.buyaround.app.model.User;
import cat.buyaround.app.network.BuyAroundRepository;

public class LoginViewModel extends ViewModel {

    private BuyAroundRepository buyAroundRepository;

    @Inject
    public LoginViewModel(BuyAroundRepository buyAroundRepository) {
        this.buyAroundRepository = buyAroundRepository;
    }

    public void login(String username, String password, LoginCallback loginCallback) {
        User user = new User(username, password);
        buyAroundRepository.login(user, loginCallback);
    }
}
