package com.selepdf.buyaround.viewmodel;

import androidx.lifecycle.ViewModel;

import com.selepdf.buyaround.callback.CategoryCallback;
import com.selepdf.buyaround.callback.StoreCallback;
import com.selepdf.buyaround.model.Store;
import com.selepdf.buyaround.network.BuyAroundRepository;

import javax.inject.Inject;

public class AddStoreViewModel extends ViewModel {

    private BuyAroundRepository buyAroundRepository;

    @Inject
    public AddStoreViewModel(BuyAroundRepository buyAroundRepository) {
        this.buyAroundRepository = buyAroundRepository;
    }

    public void addStore(String name, String description, StoreCallback callback) {
        Store store = new Store(name, description);
        buyAroundRepository.addStore(store, callback);
    }

    public void getStoreCategories(CategoryCallback callback) {
        buyAroundRepository.getAllStoreCategories(callback);
    }
}
