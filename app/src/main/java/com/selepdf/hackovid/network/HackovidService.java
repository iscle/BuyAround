package com.selepdf.hackovid.network;

import com.selepdf.hackovid.network.model.LoginResponse;
import com.selepdf.hackovid.network.model.RegisterResponse;
import com.selepdf.hackovid.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface HackovidService {

    @POST("auth/login")
    Call<LoginResponse> login(@Body User user);

    @POST("auth/register")
    Call<RegisterResponse> register(@Body User user);
}
