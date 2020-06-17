package cat.buyaround.app.network;

import android.util.Log;

import com.google.gson.Gson;

import cat.buyaround.app.auth.UserManager;
import cat.buyaround.app.callback.CategoryCallback;
import cat.buyaround.app.callback.LoginCallback;
import cat.buyaround.app.callback.NotificationCallback;
import cat.buyaround.app.callback.OrderCallback;
import cat.buyaround.app.callback.PackCallback;
import cat.buyaround.app.callback.ProductCallback;
import cat.buyaround.app.callback.RegisterCallback;
import cat.buyaround.app.callback.StoreCallback;
import cat.buyaround.app.callback.UserCallback;
import cat.buyaround.app.model.Product;
import cat.buyaround.app.model.Store;
import cat.buyaround.app.model.User;
import cat.buyaround.app.model.UserRadius;
import cat.buyaround.app.network.model.CategoryResponse;
import cat.buyaround.app.network.model.LoginResponse;
import cat.buyaround.app.network.model.NotificationResponse;
import cat.buyaround.app.network.model.OrderResponse;
import cat.buyaround.app.network.model.PackResponse;
import cat.buyaround.app.network.model.ProductResponse;
import cat.buyaround.app.network.model.RegisterResponse;
import cat.buyaround.app.network.model.SimpleResponse;
import cat.buyaround.app.network.model.StoreResponse;
import cat.buyaround.app.network.model.UserResponse;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Singleton
public class BuyAroundRepository {
    private static final String TAG = "BuyAroundRepository";

    private BuyAroundService service;
    @Inject
    protected UserManager userManager;

    @Inject
    public BuyAroundRepository(BuyAroundService service) {
        this.service = service;
    }

    public void register(User user, RegisterCallback callback) {
        service.register(user).enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                Log.d(TAG, "onResponse: " + response.code());
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
                            userManager.setToken(loginResponse.getToken());
                            callback.onSuccess();
                            getUser(new UserCallback() {
                                @Override
                                public void onUserReceived(User user) {
                                    userManager.setUser(user);
                                }

                                @Override
                                public void onFailure(SimpleResponse.Status error) {
                                    userManager.setUser(null);
                                }
                            });

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
                            callback.onFailure(SimpleResponse.Status.INTERNAL_ERROR);
                            break;
                    }
                } else {
                    callback.onFailure(SimpleResponse.Status.INTERNAL_ERROR);
                }
            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {
                callback.onFailure(SimpleResponse.Status.NETWORK_FAILURE);
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
                            callback.onFailure(SimpleResponse.Status.INTERNAL_ERROR);
                            break;
                    }
                } else {
                    callback.onFailure(SimpleResponse.Status.INTERNAL_ERROR);
                }
            }

            @Override
            public void onFailure(Call<CategoryResponse> call, Throwable t) {
                callback.onFailure(SimpleResponse.Status.NETWORK_FAILURE);
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
                            callback.onFailure(SimpleResponse.Status.INTERNAL_ERROR);
                            break;
                    }
                } else {
                    callback.onFailure(SimpleResponse.Status.INTERNAL_ERROR);
                }
            }

            @Override
            public void onFailure(Call<CategoryResponse> call, Throwable t) {
                callback.onFailure(SimpleResponse.Status.NETWORK_FAILURE);
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
                            callback.onFailure(SimpleResponse.Status.INTERNAL_ERROR);
                            break;
                    }
                } else {
                    callback.onFailure(SimpleResponse.Status.INTERNAL_ERROR);
                }
            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {
                callback.onFailure(SimpleResponse.Status.NETWORK_FAILURE);
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
                            callback.onFailure(SimpleResponse.Status.INTERNAL_ERROR);
                            break;
                    }
                } else {
                    callback.onFailure(SimpleResponse.Status.INTERNAL_ERROR);
                }
            }

            @Override
            public void onFailure(Call<StoreResponse> call, Throwable t) {
                callback.onFailure(SimpleResponse.Status.NETWORK_FAILURE);
                t.printStackTrace();
            }
        });
    }

    public void getNearbyStores(UserRadius userRadius, StoreCallback callback) {
        service.getNearbyStores(userRadius).enqueue(new Callback<StoreResponse>() {
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
                            callback.onFailure(SimpleResponse.Status.INTERNAL_ERROR);
                            break;
                    }
                } else {
                    callback.onFailure(SimpleResponse.Status.INTERNAL_ERROR);
                }
            }

            @Override
            public void onFailure(Call<StoreResponse> call, Throwable t) {
                callback.onFailure(SimpleResponse.Status.NETWORK_FAILURE);
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
                            callback.onFailure(SimpleResponse.Status.INTERNAL_ERROR);
                            break;
                    }
                } else {
                    callback.onFailure(SimpleResponse.Status.INTERNAL_ERROR);
                }
            }

            @Override
            public void onFailure(Call<StoreResponse> call, Throwable t) {
                callback.onFailure(SimpleResponse.Status.NETWORK_FAILURE);
                t.printStackTrace();
            }
        });
    }

    public void getUserNotifications(NotificationCallback callback) {
        service.getUserNotifications().enqueue(new Callback<NotificationResponse>() {
            @Override
            public void onResponse(Call<NotificationResponse> call, Response<NotificationResponse> response) {
                if (response.isSuccessful()) {
                    NotificationResponse notificationResponse = response.body();
                    Log.d(TAG, "onResponse: " + notificationResponse.getStatus());
                    switch (notificationResponse.getStatus()) {
                        case OK:
                            callback.onNotificationsReceived(notificationResponse.getNotifications());
                            break;
                        case INTERNAL_ERROR:
                            callback.onFailure(SimpleResponse.Status.INTERNAL_ERROR);
                            break;
                    }
                } else {
                    callback.onFailure(SimpleResponse.Status.INTERNAL_ERROR);
                }
            }

            @Override
            public void onFailure(Call<NotificationResponse> call, Throwable t) {
                callback.onFailure(SimpleResponse.Status.NETWORK_FAILURE);
                t.printStackTrace();
            }
        });
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
                            callback.onFailure(SimpleResponse.Status.INTERNAL_ERROR);
                            break;
                    }
                } else {
                    callback.onFailure(SimpleResponse.Status.INTERNAL_ERROR);
                }
            }

            @Override
            public void onFailure(Call<OrderResponse> call, Throwable t) {
                callback.onFailure(SimpleResponse.Status.NETWORK_FAILURE);
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
                            callback.onFailure(SimpleResponse.Status.INTERNAL_ERROR);
                            break;
                    }
                } else {
                    callback.onFailure(SimpleResponse.Status.INTERNAL_ERROR);
                }
            }

            @Override
            public void onFailure(Call<OrderResponse> call, Throwable t) {
                callback.onFailure(SimpleResponse.Status.NETWORK_FAILURE);
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
                            callback.onFailure(SimpleResponse.Status.INTERNAL_ERROR);
                            break;
                    }
                } else {
                    callback.onFailure(SimpleResponse.Status.INTERNAL_ERROR);
                }
            }

            @Override
            public void onFailure(Call<StoreResponse> call, Throwable t) {
                callback.onFailure(SimpleResponse.Status.NETWORK_FAILURE);
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
                            callback.onFailure(SimpleResponse.Status.INTERNAL_ERROR);
                            break;
                    }
                } else {
                    callback.onFailure(SimpleResponse.Status.INTERNAL_ERROR);
                }
            }

            @Override
            public void onFailure(Call<PackResponse> call, Throwable t) {
                callback.onFailure(SimpleResponse.Status.NETWORK_FAILURE);
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
                            callback.onFailure(SimpleResponse.Status.INTERNAL_ERROR);
                            break;
                    }
                } else {
                    callback.onFailure(SimpleResponse.Status.INTERNAL_ERROR);
                }
            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {
                callback.onFailure(SimpleResponse.Status.NETWORK_FAILURE);
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
                            callback.onFailure(SimpleResponse.Status.INTERNAL_ERROR);
                            break;
                    }
                } else {
                    callback.onFailure(SimpleResponse.Status.INTERNAL_ERROR);
                }
            }

            @Override
            public void onFailure(Call<PackResponse> call, Throwable t) {
                callback.onFailure(SimpleResponse.Status.NETWORK_FAILURE);
                t.printStackTrace();
            }
        });
    }

    public void getUser(UserCallback callback) {
        service.getUser().enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.isSuccessful()) {
                    callback.onUserReceived(response.body().getUser());
                } else {
                    Log.d(TAG, "getUser: Something went wrong.");
                    SimpleResponse errorResponse = new Gson().fromJson(response.errorBody().charStream(), SimpleResponse.class);
                    callback.onFailure(errorResponse.getStatus());
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                callback.onFailure(SimpleResponse.Status.NETWORK_FAILURE);
                t.printStackTrace();
            }
        });
    }
}
