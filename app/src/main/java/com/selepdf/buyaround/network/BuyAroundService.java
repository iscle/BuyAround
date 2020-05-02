package com.selepdf.buyaround.network;

import com.selepdf.buyaround.model.Pack;
import com.selepdf.buyaround.model.Product;
import com.selepdf.buyaround.model.Store;
import com.selepdf.buyaround.model.User;
import com.selepdf.buyaround.model.UserRadius;
import com.selepdf.buyaround.network.model.CategoryResponse;
import com.selepdf.buyaround.network.model.LoginResponse;
import com.selepdf.buyaround.network.model.OrderResponse;
import com.selepdf.buyaround.network.model.PackResponse;
import com.selepdf.buyaround.network.model.ProductResponse;
import com.selepdf.buyaround.network.model.RegisterResponse;
import com.selepdf.buyaround.network.model.StoreResponse;
import com.selepdf.buyaround.network.model.UserResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface BuyAroundService {

    // Auth
    @POST("auth/login")
    Call<LoginResponse> login(@Body User user);

    @POST("auth/register")
    Call<RegisterResponse> register(@Body User user);

    // Product
    @GET("product")
    Call<ProductResponse> getAllProducts();

    @POST("product")
    Call<ProductResponse> addProduct(@Body Product product);

    @POST("user/favouriteProducts")
    Call<UserResponse> addFavouriteProduct(@Body Product product);

    @GET("user/favouriteProducts")
    Call<ProductResponse> getFavouriteProducts();

    // Stores
    @GET("store")
    Call<StoreResponse> getAllStores();

    @POST("store")
    Call<StoreResponse> addStore(@Body Store store);

    @POST("user/favouriteStore")
    Call<UserResponse> addFavouriteStore(@Body Store store);

    @GET("user/favouriteStores")
    Call<StoreResponse> getFavouriteStores();

    @GET("store")
    Call<StoreResponse> getNearbyStores(@Body UserRadius userRadius);

    // Packs
    @GET("pack")
    Call<PackResponse> getAllPacks();

    @POST("pack")
    Call<PackResponse> addPack(@Body Pack pack);

    @POST("user/favouritePacks")
    Call<UserResponse> addFavouritePack(@Body Pack pack);

    @GET("user/favouritePacks")
    Call<PackResponse> getFavouritePacks();

    // Users
    @GET("user")
    Call<UserResponse> getUser();

    @GET("user/{id}")
    Call<UserResponse> getUser(@Path(value = "id", encoded = true) String id);

    // Category
    @GET("category/product")
    Call<CategoryResponse> getAllProductCategories();

    @POST("category/product")
    Call<CategoryResponse> addProductCategory();

    @GET("category/store")
    Call<CategoryResponse> getAllStoreCategories();

    @POST("category/store")
    Call<CategoryResponse> addStoreCategory();

    // Orders
    @GET("user/orders")
    Call<OrderResponse> getLastUserOrders();

    @GET("user/orders/repeated")
    Call<OrderResponse> getRepeatedUserOrders();

}
