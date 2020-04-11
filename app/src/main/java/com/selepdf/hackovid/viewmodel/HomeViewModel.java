package com.selepdf.hackovid.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.selepdf.hackovid.callback.ProductCallback;
import com.selepdf.hackovid.model.Pack;
import com.selepdf.hackovid.model.Product;
import com.selepdf.hackovid.model.Store;
import com.selepdf.hackovid.network.HackovidRepository;

import java.util.List;

import javax.inject.Inject;

public class HomeViewModel extends ViewModel {

    private HackovidRepository hackovidRepository;
    private MutableLiveData<List<Store>> mStores;
    private MutableLiveData<List<Pack>> mPacks;
    private MutableLiveData<Product[]> mProducts;

    @Inject
    public HomeViewModel(HackovidRepository hackovidRepository) {
        this.hackovidRepository = hackovidRepository;
        this.mStores = new MutableLiveData<>();
        this.mPacks = new MutableLiveData<>();
        this.mProducts = new MutableLiveData<>();
    }

    public void requestShopsAround(String username) {
        // Handle request

    }

    public void requestPacks() {
        // Handle request
    }

    public void requestProducts() {
        // Handle request
        hackovidRepository.getAllProducts(new ProductCallback() {
            @Override
            public void onProductsReceived(Product[] products) {
                mProducts.postValue(products);
            }

            @Override
            public void onFailure(FailureError error) {

            }
        });
    }

    public LiveData<List<Store>> getStores() {
        return mStores;
    }

    public LiveData<List<Pack>> getPacks() {
        return mPacks;
    }

    public LiveData<Product[]> getProducts() {
        requestProducts();
        return mProducts;
    }
}
