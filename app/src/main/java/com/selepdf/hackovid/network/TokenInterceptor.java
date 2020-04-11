package com.selepdf.hackovid.network;

import android.util.Log;

import com.selepdf.hackovid.auth.TokenManager;

import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

@Singleton
public class TokenInterceptor implements Interceptor {
    private static final String TAG = "TokenInterceptor";
    private TokenManager tokenManager;

    @Inject
    public TokenInterceptor(TokenManager tokenManager) {
        this.tokenManager = tokenManager;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Log.d(TAG, "intercept: getting token...");
        String token = tokenManager.getToken();
        if (TokenManager.isTokenValid(token)) {
            Log.d(TAG, "intercept: adding header...");
            Request request = chain.request()
                    .newBuilder()
                    .addHeader("Authorization", token)
                    .build();

            Log.d(TAG, "intercept: proceeding...");
            return chain.proceed(request);
        }

        Log.d(TAG, "intercept: token wasn't valid, ignoring...");
        return chain.proceed(chain.request());
    }
}
