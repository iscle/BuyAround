package com.selepdf.hackovid.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.selepdf.hackovid.callback.FailureCallback;
import com.selepdf.hackovid.callback.OrderCallback;
import com.selepdf.hackovid.model.Order;
import com.selepdf.hackovid.network.HackovidRepository;

import javax.inject.Inject;

public class OrdersViewModel extends ViewModel {

    private HackovidRepository hackovidRepository;
    private MutableLiveData<Order[]> mOrders;

    @Inject
    public OrdersViewModel(HackovidRepository hackovidRepository) {
        this.hackovidRepository = hackovidRepository;
        mOrders = new MutableLiveData<>();
    }

    public void requestLastUserOrders() {
        hackovidRepository.getLastUserOrders(new OrderCallback() {
            @Override
            public void onOrdersReceived(Order[] orders) {
                mOrders.postValue(orders);
            }

            @Override
            public void onFailure(FailureError error) {

            }
        });
    }

    public LiveData<Order[]> getLastUserOrders() {
        requestLastUserOrders();
        return mOrders;
    }

    public void requestRepeatedUserOrders() {
        hackovidRepository.getRepeatedUserOrders(new OrderCallback() {
            @Override
            public void onOrdersReceived(Order[] orders) {
                mOrders.postValue(orders);
            }

            @Override
            public void onFailure(FailureError error) {

            }
        });
    }

    public LiveData<Order[]> getRepeatedUserOrders() {
        requestRepeatedUserOrders();
        return mOrders;
    }
}
