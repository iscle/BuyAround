package com.selepdf.hackovid.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.selepdf.hackovid.callback.LoginCallback;
import com.selepdf.hackovid.callback.PackCallback;
import com.selepdf.hackovid.callback.ProductCallback;
import com.selepdf.hackovid.callback.StoreCallback;
import com.selepdf.hackovid.factory.ViewModelFactory;
import com.selepdf.hackovid.model.Pack;
import com.selepdf.hackovid.model.Product;
import com.selepdf.hackovid.model.Store;
import com.selepdf.hackovid.repository.HackovidRepository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class HomeViewModel extends ViewModel {

    private HackovidRepository hackovidRepository;
    private MutableLiveData<List<Store>> mStores;
    private MutableLiveData<List<Pack>> mPacks;
    private MutableLiveData<List<Product>> mProducts;

    @Inject
    public HomeViewModel(HackovidRepository hackovidRepository) {
        this.hackovidRepository = hackovidRepository;
        this.mStores = new MutableLiveData<>();
        this.mPacks = new MutableLiveData<>();
        this.mProducts = new MutableLiveData<>();
    }

    public void requestShopsAround(String username, StoreCallback storeCallback) {
        // Handle petition here
        storeCallback.onStoresReceived(new ArrayList<>());
    }

    public void requestPacks(PackCallback packCallback) {
        packCallback.onPacksReceived(new ArrayList<>());
    }

    public void requestProducts(ProductCallback productCallback) {
        productCallback.onProductsReceived(new ArrayList<>());
    }

    public LiveData<List<Store>> getStores() {
        return mStores;
    }

    public LiveData<List<Pack>> getPacks() {
        return mPacks;
    }

    public LiveData<List<Product>> getProducts() {
        return mProducts;
    }
}
