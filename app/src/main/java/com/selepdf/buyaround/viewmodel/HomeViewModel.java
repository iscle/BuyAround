package com.selepdf.buyaround.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.selepdf.buyaround.callback.ProductCallback;
import com.selepdf.buyaround.callback.StoreCallback;
import com.selepdf.buyaround.model.Pack;
import com.selepdf.buyaround.model.Product;
import com.selepdf.buyaround.model.Store;
import com.selepdf.buyaround.network.BuyAroundRepository;

import javax.inject.Inject;

public class HomeViewModel extends ViewModel {

    private BuyAroundRepository buyAroundRepository;
    private MutableLiveData<Store[]> mStores;
    private MutableLiveData<Pack[]> mPacks;
    private MutableLiveData<Product[]> mProducts;

    @Inject
    public HomeViewModel(BuyAroundRepository buyAroundRepository) {
        this.buyAroundRepository = buyAroundRepository;
        this.mStores = new MutableLiveData<>();
        this.mPacks = new MutableLiveData<>();
        this.mProducts = new MutableLiveData<>();
    }

    public void requestStoresAround(String username) {
        // Handle request
        buyAroundRepository.getAllStores(new StoreCallback() {
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
        buyAroundRepository.getAllProducts(new ProductCallback() {
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
