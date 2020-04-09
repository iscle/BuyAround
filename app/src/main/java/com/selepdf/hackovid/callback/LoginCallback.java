package com.selepdf.hackovid.callback;

public interface LoginCallback {
    void onSuccess();
    void onFailure(LoginError loginError);

    enum LoginError {
        WRONG_PASSWORD,
        INTERNAL_ERROR,
        NETWORK_ERROR
    }
}
