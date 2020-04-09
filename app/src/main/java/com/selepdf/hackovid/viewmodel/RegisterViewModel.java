package com.selepdf.hackovid.viewmodel;

import androidx.lifecycle.ViewModel;

import com.selepdf.hackovid.callback.LoginCallback;
import com.selepdf.hackovid.callback.RegisterCallback;
import com.selepdf.hackovid.model.User;
import com.selepdf.hackovid.repository.HackovidRepository;

import javax.inject.Inject;

public class RegisterViewModel extends ViewModel {

    private HackovidRepository hackovidRepository;

    @Inject
    public RegisterViewModel(HackovidRepository hackovidRepository) {
        this.hackovidRepository = hackovidRepository;
    }

    public void register(String username, String password, RegisterCallback registerCallback) {
        User user = new User(username, password);
        hackovidRepository.register(user, registerCallback);
    }
}
