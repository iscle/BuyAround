package com.selepdf.buyaround.callback;

public interface RegisterCallback {
    void onSuccess();
    void onFailure(RegisterError registerError);

    enum RegisterError {
        EXISTING_EMAIL,
        INTERNAL_ERROR,
        NETWORK_ERROR,
        MISSING_PARAMETERS,
        WEAK_PASSWORD
    }
}
