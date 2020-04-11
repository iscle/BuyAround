package com.selepdf.hackovid.network;

import android.util.Log;

import com.selepdf.hackovid.auth.TokenManager;
import com.selepdf.hackovid.callback.FailureCallback;
import com.selepdf.hackovid.callback.LoginCallback;
import com.selepdf.hackovid.callback.ProductCallback;
import com.selepdf.hackovid.callback.RegisterCallback;
import com.selepdf.hackovid.callback.StoreCallback;
import com.selepdf.hackovid.model.Product;
import com.selepdf.hackovid.model.Store;
import com.selepdf.hackovid.model.User;
import com.selepdf.hackovid.network.model.LoginResponse;
import com.selepdf.hackovid.network.model.ProductResponse;
import com.selepdf.hackovid.network.model.RegisterResponse;
import com.selepdf.hackovid.network.model.StoreResponse;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Singleton
public class HackovidRepository {
    private static final String TAG = "HackovidRepository";

    private HackovidService service;
    private TokenManager tokenManager;

    @Inject
    public HackovidRepository(HackovidService service, TokenManager tokenManager) {
        this.service = service;
        this.tokenManager = tokenManager;
    }

    public void register(User user, RegisterCallback callback) {
        service.register(user).enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                if (response.isSuccessful()) {
                    RegisterResponse registerResponse = response.body();
                    switch (registerResponse.getStatus()) {
                        case OK:
                            callback.onSuccess();
                            break;
                        case INTERNAL_ERROR:
                            callback.onFailure(RegisterCallback.RegisterError.INTERNAL_ERROR);
                            break;
                        case EXISTING_EMAIL:
                            callback.onFailure(RegisterCallback.RegisterError.EXISTING_EMAIL);
                            break;
                        case MISSING_PARAMETERS:
                            callback.onFailure(RegisterCallback.RegisterError.MISSING_PARAMETERS);
                            break;
                        case WEAK_PASSWORD:
                            callback.onFailure(RegisterCallback.RegisterError.WEAK_PASSWORD);
                            break;
                    }
                } else {
                    callback.onFailure(RegisterCallback.RegisterError.INTERNAL_ERROR);
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                callback.onFailure(RegisterCallback.RegisterError.NETWORK_ERROR);
            }
        });
    }

    public void login(User user, LoginCallback callback) {
        service.login(user).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()) {
                    LoginResponse loginResponse = response.body();
                    switch (loginResponse.getStatus()) {
                        case OK:
                            tokenManager.setToken(loginResponse.getToken());
                            callback.onSuccess();
                            break;
                        case INTERNAL_ERROR:
                            callback.onFailure(LoginCallback.LoginError.INTERNAL_ERROR);
                            break;
                        case WRONG_PASSWORD:
                            callback.onFailure(LoginCallback.LoginError.WRONG_PASSWORD);
                            break;
                        case MISSING_PARAMETERS:
                            callback.onFailure(LoginCallback.LoginError.MISSING_PARAMETERS);
                            break;
                    }
                } else {
                    callback.onFailure(LoginCallback.LoginError.INTERNAL_ERROR);
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                callback.onFailure(LoginCallback.LoginError.NETWORK_ERROR);
            }
        });
    }

    public void getAllProducts(ProductCallback callback) {
        service.getAllProducts().enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                if (response.isSuccessful()) {
                    ProductResponse productResponse = response.body();
                    switch (productResponse.getStatus()) {
                        case OK:
                            callback.onProductsReceived(response.body().getProducts());
                            break;
                    }
                } else {
                    callback.onFailure(FailureCallback.FailureError.INTERNAL_ERROR);
                }
            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {
                callback.onFailure(FailureCallback.FailureError.NETWORK_ERROR);
            }
        });
    }

    public void addProduct(Product product, ProductCallback callback) {
        service.addProduct(product).enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "onResponse: " + response.raw().toString());
                    ProductResponse productResponse = response.body();
                    switch (productResponse.getStatus()) {
                        case OK:
                            callback.onProductsReceived(productResponse.getProducts());
                            break;
                    }
                } else {
                    callback.onFailure(FailureCallback.FailureError.INTERNAL_ERROR);
                }
            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {
                callback.onFailure(FailureCallback.FailureError.NETWORK_ERROR);
            }
        });
    }

    public void getAllStores(StoreCallback callback) {
        service.getAllStores().enqueue(new Callback<StoreResponse>() {
            @Override
            public void onResponse(Call<StoreResponse> call, Response<StoreResponse> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "onResponse: " + response.raw().toString());
                    StoreResponse storeResponse = response.body();
                    switch (storeResponse.getStatus()) {
                        case OK:
                            callback.onStoresReceived(storeResponse.getStores());
                            break;
                    }
                } else {
                    callback.onFailure(FailureCallback.FailureError.INTERNAL_ERROR);
                }
            }

            @Override
            public void onFailure(Call<StoreResponse> call, Throwable t) {
                callback.onFailure(FailureCallback.FailureError.NETWORK_ERROR);
            }
        });
    }

    public void addStore(Store store, StoreCallback callback) {
        service.addStore(store).enqueue(new Callback<StoreResponse>() {
            @Override
            public void onResponse(Call<StoreResponse> call, Response<StoreResponse> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "onResponse: " + response.raw().toString());
                    StoreResponse storeResponse = response.body();
                    switch (storeResponse.getStatus()) {
                        case OK:
                            callback.onStoresReceived(storeResponse.getStores());
                            break;
                    }
                } else {
                    callback.onFailure(FailureCallback.FailureError.INTERNAL_ERROR);
                }
            }

            @Override
            public void onFailure(Call<StoreResponse> call, Throwable t) {
                callback.onFailure(FailureCallback.FailureError.NETWORK_ERROR);
            }
        });
    }
}
