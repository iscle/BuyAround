package com.selepdf.buyaround.viewmodel;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.selepdf.buyaround.auth.UserManager;
import com.selepdf.buyaround.model.Order;
import com.selepdf.buyaround.model.OrderProduct;
import com.selepdf.buyaround.network.BuyAroundRepository;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class CartViewModel extends ViewModel {

    @Inject
    UserManager userManager;
    
    private MutableLiveData<OrderProduct[]> mProducts;

    private BuyAroundRepository buyAroundRepository;

    @Inject
    public CartViewModel(BuyAroundRepository buyAroundRepository) {
        this.buyAroundRepository = buyAroundRepository;
        mProducts = new MutableLiveData<>();
        mProducts.setValue(new OrderProduct[0]);
    }

    private void requestrProducts() {
        //Get the info from elsewhere;
    }

    public LiveData<List<OrderProduct>> getOrderProducts() {
        return userManager.getProducts();
    }

    public void addProduct(OrderProduct product) {
        int size = mProducts.getValue().length;
        OrderProduct[] newList = new OrderProduct[size+1];
        for (int i = 0; i < size;i++) {
            newList[i] = mProducts.getValue()[i];
        }
        newList[size] = product;
        userManager.setProducts(newList);
        mProducts.setValue(newList);
    }

    public void clearProductList() {
        mProducts.postValue(new OrderProduct[0]);
    }
}
