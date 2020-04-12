package com.selepdf.buyaround.viewmodel;

import androidx.lifecycle.ViewModel;

import com.selepdf.buyaround.callback.LoginCallback;
import com.selepdf.buyaround.model.User;
import com.selepdf.buyaround.network.BuyAroundRepository;

import javax.inject.Inject;

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
