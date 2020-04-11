package com.selepdf.hackovid.network;

import com.selepdf.hackovid.model.Pack;
import com.selepdf.hackovid.model.Product;
import com.selepdf.hackovid.model.Store;
import com.selepdf.hackovid.model.User;
import com.selepdf.hackovid.network.model.CategoryResponse;
import com.selepdf.hackovid.network.model.LoginResponse;
import com.selepdf.hackovid.network.model.PackResponse;
import com.selepdf.hackovid.network.model.ProductResponse;
import com.selepdf.hackovid.network.model.RegisterResponse;
import com.selepdf.hackovid.network.model.StoreResponse;
import com.selepdf.hackovid.network.model.UserResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface HackovidService {

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

    // Stores
    @GET("store")
    Call<StoreResponse> getAllStores();

    @POST("store")
    Call<StoreResponse> addStore(@Body Store store);

    // Packs
    @GET("pack")
    Call<PackResponse> getAllPacks();

    @POST("pack")
    Call<PackResponse> addPack(@Body Pack pack);

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

}
