package com.selepdf.buyaround.auth;

import android.content.Context;
import android.content.Intent;

import androidx.lifecycle.MutableLiveData;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.selepdf.buyaround.callback.UserCallback;
import com.selepdf.buyaround.model.OrderProduct;
import com.selepdf.buyaround.model.User;
import com.selepdf.buyaround.network.BuyAroundRepository;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class UserManager {

    private TokenManager tokenManager;
    private User user;
    private BuyAroundRepository buyAroundRepository;
    private LocalBroadcastManager localBroadcastManager;
    private MutableLiveData<OrderProduct[]> cartProducts;

    @Inject
    public UserManager(TokenManager tokenManager, BuyAroundRepository buyAroundRepository, Context context) {
        this.tokenManager = tokenManager;
        this.buyAroundRepository = buyAroundRepository;
        this.localBroadcastManager = LocalBroadcastManager.getInstance(context);
    }

    public User getUser() {
        return user;
    }

    public void setUser() {
        buyAroundRepository.getUser(new UserCallback() {
            @Override
            public void onUserReceived(User user) {
                setUser(user);
            }

            @Override
            public void onFailure(FailureError error) {
                setUser(null);
            }
        });
    }

    public void setUser(User user) {
        this.user = user;
        localBroadcastManager.sendBroadcast(new Intent("com.selepdf.buyaround.action.USER_UPDATED"));
    }

    public TokenManager getTokenManager() {
        return tokenManager;
    }

    public MutableLiveData<OrderProduct[]> getCartProducts() {
        if (cartProducts == null) {
            cartProducts = new MutableLiveData<>();
        }

        return cartProducts;
    }

    public void setCartProducts(MutableLiveData<OrderProduct[]> cartProducts) {
        this.cartProducts = cartProducts;
    }
}
