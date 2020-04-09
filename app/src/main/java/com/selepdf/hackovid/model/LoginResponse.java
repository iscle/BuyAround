package com.selepdf.hackovid.model;

public class LoginResponse {
    private boolean auth;
    private String token;

    public boolean isAuth() {
        return auth;
    }

    public String getToken() {
        return token;
    }

    @Override
    public String toString() {
        return "LoginResponse{" +
                "auth=" + auth +
                ", token='" + token + '\'' +
                '}';
    }
}
