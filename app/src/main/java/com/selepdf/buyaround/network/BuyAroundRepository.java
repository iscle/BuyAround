package com.selepdf.buyaround.network;

import android.util.Log;

import com.selepdf.buyaround.auth.TokenManager;
import com.selepdf.buyaround.callback.CategoryCallback;
import com.selepdf.buyaround.callback.FailureCallback;
import com.selepdf.buyaround.callback.LoginCallback;
import com.selepdf.buyaround.callback.NotificationCallback;
import com.selepdf.buyaround.callback.OrderCallback;
import com.selepdf.buyaround.callback.PackCallback;
import com.selepdf.buyaround.callback.ProductCallback;
import com.selepdf.buyaround.callback.RegisterCallback;
import com.selepdf.buyaround.callback.StoreCallback;
import com.selepdf.buyaround.callback.UserCallback;
import com.selepdf.buyaround.model.Product;
import com.selepdf.buyaround.model.Store;
import com.selepdf.buyaround.model.User;
import com.selepdf.buyaround.network.model.CategoryResponse;
import com.selepdf.buyaround.network.model.LoginResponse;
import com.selepdf.buyaround.network.model.OrderResponse;
import com.selepdf.buyaround.network.model.PackResponse;
import com.selepdf.buyaround.network.model.ProductResponse;
import com.selepdf.buyaround.network.model.RegisterResponse;
import com.selepdf.buyaround.network.model.StoreResponse;
import com.selepdf.buyaround.network.model.UserResponse;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Singleton
public class BuyAroundRepository {
    private static final String TAG = "BuyAroundRepository";

    private BuyAroundService service;
    private TokenManager tokenManager;

    @Inject
    public BuyAroundRepository(BuyAroundService service, TokenManager tokenManager) {
        this.service = service;
        this.tokenManager = tokenManager;
    }

    public void register(User user, RegisterCallback callback) {
        service.register(user).enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                if (response.isSuccessful()) {
                    RegisterResponse registerResponse = response.body();
                    Log.d(TAG, "onResponse: " + registerResponse.getStatus());
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
                t.printStackTrace();
            }
        });
    }

    public void login(User user, LoginCallback callback) {
        service.login(user).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()) {
                    LoginResponse loginResponse = response.body();
                    Log.d(TAG, "onResponse: " + loginResponse.getStatus());
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
                t.printStackTrace();
            }
        });
    }

    public void getAllProducts(ProductCallback callback) {
        service.getAllProducts().enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                if (response.isSuccessful()) {
                    ProductResponse productResponse = response.body();
                    Log.d(TAG, "onResponse: " + productResponse.getStatus());
                    switch (productResponse.getStatus()) {
                        case OK:
                            callback.onProductsReceived(productResponse.getProducts());
                            break;
                        case INTERNAL_ERROR:
                            callback.onFailure(FailureCallback.FailureError.INTERNAL_ERROR);
                            break;
                    }
                } else {
                    callback.onFailure(FailureCallback.FailureError.INTERNAL_ERROR);
                }
            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {
                callback.onFailure(FailureCallback.FailureError.NETWORK_ERROR);
                t.printStackTrace();
            }
        });
    }

    public void getAllProductCategories(CategoryCallback callback) {
        service.getAllProductCategories().enqueue(new Callback<CategoryResponse>() {
            @Override
            public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {
                if (response.isSuccessful()) {
                    CategoryResponse categoryResponse = response.body();
                    Log.d(TAG, "onResponse: " + categoryResponse.getStatus());
                    switch (categoryResponse.getStatus()) {
                        case OK:
                            callback.onCategoriesReceived(categoryResponse.getCategories());
                            break;
                        case INTERNAL_ERROR:
                            callback.onFailure(FailureCallback.FailureError.INTERNAL_ERROR);
                            break;
                    }
                } else {
                    callback.onFailure(FailureCallback.FailureError.INTERNAL_ERROR);
                }
            }

            @Override
            public void onFailure(Call<CategoryResponse> call, Throwable t) {
                t.printStackTrace();
                callback.onFailure(FailureCallback.FailureError.NETWORK_ERROR);
                t.printStackTrace();
            }
        });
    }

    public void getAllStoreCategories(CategoryCallback callback) {
        service.getAllStoreCategories().enqueue(new Callback<CategoryResponse>() {
            @Override
            public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {
                if (response.isSuccessful()) {
                    CategoryResponse categoryResponse = response.body();
                    Log.d(TAG, "onResponse: " + categoryResponse.getStatus());
                    switch (categoryResponse.getStatus()) {
                        case OK:
                            callback.onCategoriesReceived(categoryResponse.getCategories());
                            break;
                        case INTERNAL_ERROR:
                            callback.onFailure(FailureCallback.FailureError.INTERNAL_ERROR);
                            break;
                    }
                } else {
                    callback.onFailure(FailureCallback.FailureError.INTERNAL_ERROR);
                }
            }

            @Override
            public void onFailure(Call<CategoryResponse> call, Throwable t) {
                t.printStackTrace();
                callback.onFailure(FailureCallback.FailureError.NETWORK_ERROR);
                t.printStackTrace();
            }
        });
    }

    public void addProduct(Product product, ProductCallback callback) {
        service.addProduct(product).enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                if (response.isSuccessful()) {
                    ProductResponse productResponse = response.body();
                    Log.d(TAG, "onResponse: " + productResponse.getStatus());
                    switch (productResponse.getStatus()) {
                        case OK:
                            callback.onProductsReceived(productResponse.getProducts());
                            break;
                        case INTERNAL_ERROR:
                            callback.onFailure(FailureCallback.FailureError.INTERNAL_ERROR);
                            break;
                    }
                } else {
                    callback.onFailure(FailureCallback.FailureError.INTERNAL_ERROR);
                }
            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {
                callback.onFailure(FailureCallback.FailureError.NETWORK_ERROR);
                t.printStackTrace();
            }
        });
    }

    public void getAllStores(StoreCallback callback) {
        service.getAllStores().enqueue(new Callback<StoreResponse>() {
            @Override
            public void onResponse(Call<StoreResponse> call, Response<StoreResponse> response) {
                if (response.isSuccessful()) {
                    StoreResponse storeResponse = response.body();
                    Log.d(TAG, "onResponse: " + storeResponse.getStatus());
                    switch (storeResponse.getStatus()) {
                        case OK:
                            callback.onStoresReceived(storeResponse.getStores());
                            break;
                        case INTERNAL_ERROR:
                            callback.onFailure(FailureCallback.FailureError.INTERNAL_ERROR);
                            break;
                    }
                } else {
                    callback.onFailure(FailureCallback.FailureError.INTERNAL_ERROR);
                }
            }

            @Override
            public void onFailure(Call<StoreResponse> call, Throwable t) {
                callback.onFailure(FailureCallback.FailureError.NETWORK_ERROR);
                t.printStackTrace();
            }
        });
    }

    public void addStore(Store store, StoreCallback callback) {
        service.addStore(store).enqueue(new Callback<StoreResponse>() {
            @Override
            public void onResponse(Call<StoreResponse> call, Response<StoreResponse> response) {
                if (response.isSuccessful()) {
                    StoreResponse storeResponse = response.body();
                    Log.d(TAG, "onResponse: " + storeResponse.getStatus());
                    switch (storeResponse.getStatus()) {
                        case OK:
                            callback.onStoresReceived(storeResponse.getStores());
                            break;
                        case INTERNAL_ERROR:
                            callback.onFailure(FailureCallback.FailureError.INTERNAL_ERROR);
                            break;
                    }
                } else {
                    callback.onFailure(FailureCallback.FailureError.INTERNAL_ERROR);
                }
            }

            @Override
            public void onFailure(Call<StoreResponse> call, Throwable t) {
                callback.onFailure(FailureCallback.FailureError.NETWORK_ERROR);
                t.printStackTrace();
            }
        });
    }

    public void getUserNotifications(NotificationCallback callback) {
        // TODO
    }

    public void getLastUserOrders(OrderCallback callback) {
        service.getLastUserOrders().enqueue(new Callback<OrderResponse>() {
            @Override
            public void onResponse(Call<OrderResponse> call, Response<OrderResponse> response) {
                if (response.isSuccessful()) {
                    OrderResponse orderResponse = response.body();
                    Log.d(TAG, "onResponse: " + orderResponse.getStatus());
                    switch (orderResponse.getStatus()) {
                        case OK:
                            callback.onOrdersReceived(orderResponse.getOrders());
                            break;
                        case INTERNAL_ERROR:
                            callback.onFailure(FailureCallback.FailureError.INTERNAL_ERROR);
                            break;
                    }
                } else {
                    callback.onFailure(FailureCallback.FailureError.INTERNAL_ERROR);
                }
            }

            @Override
            public void onFailure(Call<OrderResponse> call, Throwable t) {
                callback.onFailure(FailureCallback.FailureError.NETWORK_ERROR);
                t.printStackTrace();
            }
        });
    }

    public void getRepeatedUserOrders(OrderCallback callback) {
        service.getRepeatedUserOrders().enqueue(new Callback<OrderResponse>() {
            @Override
            public void onResponse(Call<OrderResponse> call, Response<OrderResponse> response) {
                if (response.isSuccessful()) {
                    OrderResponse orderResponse = response.body();
                    Log.d(TAG, "onResponse: " + orderResponse.getStatus());
                    switch (orderResponse.getStatus()) {
                        case OK:
                            callback.onOrdersReceived(orderResponse.getOrders());
                            break;
                        case INTERNAL_ERROR:
                            callback.onFailure(FailureCallback.FailureError.INTERNAL_ERROR);
                            break;
                    }
                } else {
                    callback.onFailure(FailureCallback.FailureError.INTERNAL_ERROR);
                }
            }

            @Override
            public void onFailure(Call<OrderResponse> call, Throwable t) {
                callback.onFailure(FailureCallback.FailureError.NETWORK_ERROR);
                t.printStackTrace();
            }
        });
    }

    public void getFavouriteStores(StoreCallback callback) {
        service.getFavouriteStores().enqueue(new Callback<StoreResponse>() {
            @Override
            public void onResponse(Call<StoreResponse> call, Response<StoreResponse> response) {
                if (response.isSuccessful()) {
                    StoreResponse storeResponse = response.body();
                    Log.d(TAG, "onResponse: " + storeResponse.getStatus());
                    switch (storeResponse.getStatus()) {
                        case OK:
                            callback.onStoresReceived(storeResponse.getStores());
                            break;
                        case INTERNAL_ERROR:
                            callback.onFailure(FailureCallback.FailureError.INTERNAL_ERROR);
                            break;
                    }
                } else {
                    callback.onFailure(FailureCallback.FailureError.INTERNAL_ERROR);
                }
            }

            @Override
            public void onFailure(Call<StoreResponse> call, Throwable t) {
                callback.onFailure(FailureCallback.FailureError.NETWORK_ERROR);
                t.printStackTrace();
            }
        });
    }

    public void getFavouritePacks(PackCallback callback) {
        service.getFavouritePacks().enqueue(new Callback<PackResponse>() {
            @Override
            public void onResponse(Call<PackResponse> call, Response<PackResponse> response) {
                if (response.isSuccessful()) {
                    PackResponse packResponse = response.body();
                    Log.d(TAG, "onResponse: " + packResponse.getStatus());
                    switch (packResponse.getStatus()) {
                        case OK:
                            callback.onPacksReceived(packResponse.getPacks());
                            break;
                        case INTERNAL_ERROR:
                            callback.onFailure(FailureCallback.FailureError.INTERNAL_ERROR);
                            break;
                    }
                } else {
                    callback.onFailure(FailureCallback.FailureError.INTERNAL_ERROR);
                }
            }

            @Override
            public void onFailure(Call<PackResponse> call, Throwable t) {
                callback.onFailure(FailureCallback.FailureError.NETWORK_ERROR);
                t.printStackTrace();
            }
        });
    }

    public void getFavouriteProducts(ProductCallback callback) {
        service.getFavouriteProducts().enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                if (response.isSuccessful()) {
                    ProductResponse productResponse = response.body();
                    Log.d(TAG, "onResponse: " + productResponse.getStatus());
                    switch (productResponse.getStatus()) {
                        case OK:
                            callback.onProductsReceived(productResponse.getProducts());
                            break;
                        case INTERNAL_ERROR:
                            callback.onFailure(FailureCallback.FailureError.INTERNAL_ERROR);
                            break;
                    }
                } else {
                    callback.onFailure(FailureCallback.FailureError.INTERNAL_ERROR);
                }
            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {
                callback.onFailure(FailureCallback.FailureError.NETWORK_ERROR);
                t.printStackTrace();
            }
        });
    }

    public void getAllPacks(PackCallback callback) {
        service.getAllPacks().enqueue(new Callback<PackResponse>() {
            @Override
            public void onResponse(Call<PackResponse> call, Response<PackResponse> response) {
                if (response.isSuccessful()) {
                    PackResponse packResponse = response.body();
                    Log.d(TAG, "onResponse: " + packResponse.getStatus());
                    switch (packResponse.getStatus()) {
                        case OK:
                            callback.onPacksReceived(packResponse.getPacks());
                            break;
                        case INTERNAL_ERROR:
                            callback.onFailure(FailureCallback.FailureError.INTERNAL_ERROR);
                            break;
                    }
                } else {
                    callback.onFailure(FailureCallback.FailureError.INTERNAL_ERROR);
                }
            }

            @Override
            public void onFailure(Call<PackResponse> call, Throwable t) {
                callback.onFailure(FailureCallback.FailureError.NETWORK_ERROR);
                t.printStackTrace();
            }
        });
    }

    public void getUser(UserCallback callback) {
        service.getUser().enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.isSuccessful()) {
                    UserResponse userResponse = response.body();
                    Log.d(TAG, "onResponse: " + userResponse.getStatus());
                    switch (userResponse.getStatus()) {
                        case OK:
                            callback.onUserReceived(userResponse.getUser());
                            break;
                        case INTERNAL_ERROR:
                            callback.onFailure(FailureCallback.FailureError.INTERNAL_ERROR);
                            break;
                    }
                } else {
                    callback.onFailure(FailureCallback.FailureError.INTERNAL_ERROR);
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                callback.onFailure(FailureCallback.FailureError.NETWORK_ERROR);
                t.printStackTrace();
            }
        });
    }
}
