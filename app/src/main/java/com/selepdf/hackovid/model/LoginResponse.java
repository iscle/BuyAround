package com.selepdf.hackovid.model;

public class LoginResponse {
    private ResponseStatus status;
    private String token;

    public ResponseStatus getStatus() {
        return status;
    }

    public String getToken() {
        return token;
    }
}
