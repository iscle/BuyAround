package com.selepdf.hackovid.model;

public class RegisterResponse {
    private boolean auth;
    private String token;

    public boolean isAuth() {
        return auth;
    }

    public String getToken() {
        return token;
    }
}
