package com.selepdf.buyaround.auth;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.selepdf.buyaround.model.OrderProduct;
import com.selepdf.buyaround.model.User;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class UserManager {

    private TokenManager tokenManager;
    private User user;
    private MutableLiveData<OrderProduct[]> mProducts;

    @Inject
    public UserManager(TokenManager tokenManager) {
        this.tokenManager = tokenManager;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public LiveData<List<OrderProduct>> getProducts() {
        List<OrderProduct> list = Arrays.asList(mProducts.getValue());
        MutableLiveData<List<OrderProduct>> liveData = new MutableLiveData<>();
        liveData.setValue(list);
        return liveData;
    }

    public void setProducts(OrderProduct[] products) {
        mProducts = new MutableLiveData<>();
        mProducts.setValue(products);
    }
}
