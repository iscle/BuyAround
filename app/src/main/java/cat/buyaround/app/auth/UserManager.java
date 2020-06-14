package cat.buyaround.app.auth;

import android.content.Context;
import android.content.Intent;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import cat.buyaround.app.callback.UserCallback;
import cat.buyaround.app.model.OrderProduct;
import cat.buyaround.app.model.User;
import cat.buyaround.app.network.BuyAroundRepository;

@Singleton
public class UserManager {
    public static final String ACTION_USER_UPDATED = "cat.buyaround.app.action.USER_UPDATED";

    private TokenManager tokenManager;
    private User user;

    private BuyAroundRepository buyAroundRepository;
    private LocalBroadcastManager localBroadcastManager;
    private MutableLiveData<OrderProduct[]> mProducts;

    @Inject
    public UserManager(TokenManager tokenManager, BuyAroundRepository buyAroundRepository, Context context) {
        this.tokenManager = tokenManager;
        this.buyAroundRepository = buyAroundRepository;
        this.localBroadcastManager = LocalBroadcastManager.getInstance(context);
        mProducts = new MutableLiveData<>();
        mProducts.postValue(new OrderProduct[0]);
    }

    public boolean hasSession() {
        return false;
    }

    public User getUser() {
        return user;
    }

    public void updateUser() {
        buyAroundRepository.getUser(new UserCallback() {
            @Override
            public void onUserReceived(User user) {
                updateUser(user);
            }

            @Override
            public void onFailure(FailureError error) {
                updateUser(null);
            }
        });
    }

    public void updateUser(User user) {
        this.user = user;
        localBroadcastManager.sendBroadcast(new Intent(ACTION_USER_UPDATED));
    }

    public TokenManager getTokenManager() {
        return tokenManager;
    }

    public LiveData<List<OrderProduct>> getProducts() {
        if (mProducts == null) {
            mProducts = new MutableLiveData<>();
            mProducts.postValue(new OrderProduct[0]);
        }
        List<OrderProduct> list = Arrays.asList(mProducts.getValue());
        MutableLiveData<List<OrderProduct>> liveData = new MutableLiveData<>();
        liveData.setValue(list);
        return liveData;
    }

    public void addProduct(OrderProduct product) {
        int size = mProducts.getValue().length;

        OrderProduct[] newList = new OrderProduct[size + 1];
        for (int i = 0; i < size; i++) {
            newList[i] = mProducts.getValue()[i];
        }
        newList[size] = product;
        mProducts = new MutableLiveData<>();
        mProducts.setValue(newList);
    }

    public void setProducts(OrderProduct[] products) {
        mProducts = new MutableLiveData<>();
        mProducts.setValue(products);
    }

    public void clearProducts() {
        mProducts.postValue(new OrderProduct[0]);
    }
}
