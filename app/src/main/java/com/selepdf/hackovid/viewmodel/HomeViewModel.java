package com.selepdf.hackovid.viewmodel;

import androidx.lifecycle.ViewModel;

import com.selepdf.hackovid.callback.LoginCallback;
import com.selepdf.hackovid.callback.PackCallback;
import com.selepdf.hackovid.callback.ProductCallback;
import com.selepdf.hackovid.callback.StoreCallback;
import com.selepdf.hackovid.factory.ViewModelFactory;
import com.selepdf.hackovid.model.Product;
import com.selepdf.hackovid.model.Store;
import com.selepdf.hackovid.repository.HackovidRepository;

import java.util.ArrayList;

import javax.inject.Inject;

public class HomeViewModel extends ViewModel {

    private HackovidRepository hackovidRepository;

    @Inject
    public HomeViewModel(HackovidRepository hackovidRepository) {
        this.hackovidRepository = hackovidRepository;
    }

    public void getShopsAround(String username, StoreCallback storeCallback) {
        // Handle petition here
        storeCallback.onStoresReceived(new ArrayList<>());
    }

    public void getPacks(PackCallback packCallback) {
        packCallback.onPacksReceived(new ArrayList<>());
    }

    public void getProducts(ProductCallback productCallback) {
        productCallback.onProductsReceived(new ArrayList<>());
    }
}
