package com.selepdf.buyaround.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.selepdf.buyaround.model.User;
import com.selepdf.buyaround.network.BuyAroundRepository;

import javax.inject.Inject;

public class AccountViewModel extends ViewModel {

    private BuyAroundRepository buyAroundRepository;
    private MutableLiveData<User> mUser;

    @Inject
    public AccountViewModel(BuyAroundRepository buyAroundRepository) {
        this.buyAroundRepository = buyAroundRepository;
        mUser = new MutableLiveData<>();
    }

    public void requestUserInfoByName(String name) {
        // Handle request
    }

    public LiveData<User> getUser() {
        return mUser;
    }
}
