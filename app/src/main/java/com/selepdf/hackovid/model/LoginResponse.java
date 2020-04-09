package com.selepdf.hackovid.model;

public class LoginResponse {
    private Status status;
    private String token;

    public Status getStatus() {
        return status;
    }

    public String getToken() {
        return token;
    }

    public enum Status {
        OK,
        INTERNAL_ERROR,
        WRONG_PASSWORD,
    }
}
