package cat.buyaround.app.viewmodel;

import android.text.TextUtils;

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

    public boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    public boolean isValidPassword(String text) {
        return text.matches("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9]).{8,}$");
    }
}
