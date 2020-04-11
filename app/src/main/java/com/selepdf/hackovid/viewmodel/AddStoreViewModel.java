package com.selepdf.hackovid.viewmodel;

import androidx.lifecycle.ViewModel;

import com.selepdf.hackovid.callback.StoreCallback;
import com.selepdf.hackovid.model.Store;
import com.selepdf.hackovid.network.HackovidRepository;

import javax.inject.Inject;

public class AddStoreViewModel extends ViewModel {

    private HackovidRepository hackovidRepository;

    @Inject
    public AddStoreViewModel(HackovidRepository hackovidRepository) {
        this.hackovidRepository = hackovidRepository;
    }

    public void addStore(String name, String description, StoreCallback callback) {
        Store store = new Store(name, description);
        hackovidRepository.addStore(store, callback);
    }
}
