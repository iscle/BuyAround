package com.selepdf.hackovid.auth;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Inject;

// Inspired by Glovo jeje
public class TokenManager {
    private static final String TOKEN_MANAGER_PREF_NAME = "token_manager";
    private static final String TOKEN_PREF = "token";

    private final SharedPreferences sharedPreferences;

    @Inject
    public TokenManager(Context context) {
        this.sharedPreferences = context.getSharedPreferences(TOKEN_MANAGER_PREF_NAME, Context.MODE_PRIVATE);
    }

    public void setToken(String token) {
        SharedPreferences.Editor sharedPrefEditor = sharedPreferences.edit();
        sharedPrefEditor.putString(TOKEN_PREF, token);
        sharedPrefEditor.apply();
    }

    public String getToken() {
        return sharedPreferences.getString(TOKEN_PREF, "");
    }

    public static boolean isTokenValid(String token) {
        return token != null && !token.isEmpty();
    }
}
