package com.selepdf.hackovid.callback;

import com.selepdf.hackovid.model.UserType;

public interface LoginCallback {
    void onSuccess(UserType userType);
    void onFailure(String error);
}
