package com.selepdf.hackovid.callback;

import com.selepdf.hackovid.model.UserType;

public interface LoginCallback {
    void onSuccess(UserType userType);
    void onFailure(LoginError loginError);

    enum LoginError {
        WRONG_PASSWORD,
        INTERNAL_ERROR,
        NETWORK_ERROR
    }
}
