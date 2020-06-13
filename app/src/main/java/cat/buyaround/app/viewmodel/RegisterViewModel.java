package cat.buyaround.app.viewmodel;

import android.text.TextUtils;

import androidx.lifecycle.ViewModel;

import cat.buyaround.app.callback.RegisterCallback;
import cat.buyaround.app.model.User;
import cat.buyaround.app.network.BuyAroundRepository;

import javax.inject.Inject;

public class RegisterViewModel extends ViewModel {

    private BuyAroundRepository buyAroundRepository;

    @Inject
    public RegisterViewModel(BuyAroundRepository buyAroundRepository) {
        this.buyAroundRepository = buyAroundRepository;
    }

    public void register(String name, String email, String password, RegisterCallback registerCallback) {
        User user = new User(name, email, password);
        buyAroundRepository.register(user, registerCallback);
    }

    public boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    public boolean isValidPassword(String text) {
        return text.matches("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9]).{8,}$");
    }
}
