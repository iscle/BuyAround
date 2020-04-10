package com.selepdf.hackovid.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.selepdf.hackovid.model.User;
import com.selepdf.hackovid.network.HackovidRepository;

import javax.inject.Inject;

public class AccountViewModel extends ViewModel {

    private HackovidRepository hackovidRepository;
    private MutableLiveData<User> mUser;

    @Inject
    public AccountViewModel(HackovidRepository hackovidRepository) {
        this.hackovidRepository = hackovidRepository;
        mUser = new MutableLiveData<>();
    }

    public void requestUserInfoByName(String name) {
        // Handle request
    }

    public LiveData<User> getUser() {
        return mUser;
    }
}
