package com.selepdf.hackovid.network;

import com.selepdf.hackovid.model.Product;
import com.selepdf.hackovid.model.User;
import com.selepdf.hackovid.network.model.LoginResponse;
import com.selepdf.hackovid.network.model.ProductResponse;
import com.selepdf.hackovid.network.model.RegisterResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

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

}
