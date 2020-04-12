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

    private BuyAroundRepository buyAroundRepository;
    private MutableLiveData<OrderProduct[]> mProducts;

    @Inject
    public CartViewModel(BuyAroundRepository buyAroundRepository, UserManager userManager) {
        this.buyAroundRepository = buyAroundRepository;
        mProducts = userManager.getCartProducts();
        mProducts.setValue(new OrderProduct[0]);
    }

    private void requestrProducts() {
        //Get the info from elsewhere;
    }

    public LiveData<List<OrderProduct>> getOrderProducts() {
        requestrProducts();
        List<OrderProduct> list = Arrays.asList(mProducts.getValue());
        MutableLiveData<List<OrderProduct>> liveData = new MutableLiveData<>();
        liveData.setValue(list);
        return liveData;
    }

    public void addProduct(OrderProduct product) {
        int size = mProducts.getValue().length;
        OrderProduct[] newList = new OrderProduct[size+1];
        for (int i = 0; i < size;i++) {
            newList[i] = mProducts.getValue()[i];
        }
        newList[size] = product;
        mProducts.setValue(newList);
    }

    public void clearProductList() {
        mProducts.postValue(new OrderProduct[0]);
    }
}
