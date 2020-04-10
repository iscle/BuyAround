package com.selepdf.hackovid.viewmodel;

import androidx.lifecycle.ViewModel;

import com.selepdf.hackovid.callback.LoginCallback;
import com.selepdf.hackovid.model.User;
import com.selepdf.hackovid.network.HackovidRepository;

import javax.inject.Inject;

public class LoginViewModel extends ViewModel {

    private HackovidRepository hackovidRepository;

    @Inject
    public LoginViewModel(HackovidRepository hackovidRepository) {
        this.hackovidRepository = hackovidRepository;
    }

    public void login(String username, String password, LoginCallback loginCallback) {
        User user = new User(username, password);
        hackovidRepository.login(user, loginCallback);
    }
}
