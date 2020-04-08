package com.selepdf.hackovid.callback;

public interface RegisterCallback {
    void onSuccess();
    void onFailure(RegisterError registerError);

    enum RegisterError {
        ALREADY_EXISTS,
        INTERNAL_ERROR,
        NETWORK_ERROR
    }
}
