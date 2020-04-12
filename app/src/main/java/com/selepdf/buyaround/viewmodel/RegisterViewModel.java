package com.selepdf.buyaround.viewmodel;

import androidx.lifecycle.ViewModel;

import com.selepdf.buyaround.callback.RegisterCallback;
import com.selepdf.buyaround.model.User;
import com.selepdf.buyaround.network.BuyAroundRepository;

import javax.inject.Inject;

public class RegisterViewModel extends ViewModel {

    private BuyAroundRepository buyAroundRepository;

    @Inject
    public RegisterViewModel(BuyAroundRepository buyAroundRepository) {
        this.buyAroundRepository = buyAroundRepository;
    }

    public void register(String username, String password, RegisterCallback registerCallback) {
        User user = new User(username, password);
        buyAroundRepository.register(user, registerCallback);
    }
}
