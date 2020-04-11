package com.selepdf.hackovid.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.selepdf.hackovid.callback.ProductCallback;
import com.selepdf.hackovid.callback.StoreCallback;
import com.selepdf.hackovid.model.Pack;
import com.selepdf.hackovid.model.Product;
import com.selepdf.hackovid.model.Store;
import com.selepdf.hackovid.network.HackovidRepository;

import javax.inject.Inject;

public class HomeViewModel extends ViewModel {

    private HackovidRepository hackovidRepository;
    private MutableLiveData<Store[]> mStores;
    private MutableLiveData<Pack[]> mPacks;
    private MutableLiveData<Product[]> mProducts;

    @Inject
    public HomeViewModel(HackovidRepository hackovidRepository) {
        this.hackovidRepository = hackovidRepository;
        this.mStores = new MutableLiveData<>();
        this.mPacks = new MutableLiveData<>();
        this.mProducts = new MutableLiveData<>();
    }

    public void requestStoresAround(String username) {
        // Handle request
        hackovidRepository.getAllStores(new StoreCallback() {
            @Override
            public void onStoresReceived(Store[] stores) {
                mStores.postValue(stores);
            }

            @Override
            public void onFailure(FailureError error) {

            }
        });

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

    public LiveData<Store[]> getStores() {
        requestStoresAround(null);
        return mStores;
    }

    public LiveData<Pack[]> getPacks() {
        requestPacks();
        return mPacks;
    }

    public LiveData<Product[]> getProducts() {
        requestProducts();
        return mProducts;
    }
}
