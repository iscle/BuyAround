package com.selepdf.buyaround.viewmodel;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.selepdf.buyaround.auth.UserManager;

import com.selepdf.buyaround.model.OrderProduct;
import com.selepdf.buyaround.network.BuyAroundRepository;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

public class CartViewModel extends ViewModel {

    @Inject
    UserManager userManager;
    

    private BuyAroundRepository buyAroundRepository;

    @Inject
    public CartViewModel(BuyAroundRepository buyAroundRepository, UserManager userManager) {
        this.buyAroundRepository = buyAroundRepository;
    }

    private void requestrProducts() {
        //Get the info from elsewhere;
    }

    public LiveData<List<OrderProduct>> getOrderProducts() {
        return userManager.getProducts();
    }

    public void addProduct(OrderProduct product) {
        userManager.addProduct(product);
    }

    public void clearProductList() {
        userManager.clearProducts();
    }
}
