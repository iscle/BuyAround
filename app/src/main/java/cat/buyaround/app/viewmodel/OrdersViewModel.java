package cat.buyaround.app.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import cat.buyaround.app.callback.OrderCallback;
import cat.buyaround.app.model.Order;
import cat.buyaround.app.network.BuyAroundRepository;
import cat.buyaround.app.network.model.SimpleResponse;

public class OrdersViewModel extends ViewModel {

    private BuyAroundRepository buyAroundRepository;
    private MutableLiveData<Order[]> mOrders;

    @Inject
    public OrdersViewModel(BuyAroundRepository buyAroundRepository) {
        this.buyAroundRepository = buyAroundRepository;
        mOrders = new MutableLiveData<>();
    }

    public void requestLastUserOrders() {
        buyAroundRepository.getLastUserOrders(new OrderCallback() {
            @Override
            public void onOrdersReceived(Order[] orders) {
                mOrders.postValue(orders);
            }

            @Override
            public void onFailure(SimpleResponse.Status error) {

            }
        });
    }

    public LiveData<Order[]> getLastUserOrders() {
        requestLastUserOrders();
        return mOrders;
    }

    public void requestRepeatedUserOrders() {
        buyAroundRepository.getRepeatedUserOrders(new OrderCallback() {
            @Override
            public void onOrdersReceived(Order[] orders) {
                mOrders.postValue(orders);
            }

            @Override
            public void onFailure(SimpleResponse.Status error) {

            }
        });
    }

    public LiveData<Order[]> getRepeatedUserOrders() {
        requestRepeatedUserOrders();
        return mOrders;
    }
}
