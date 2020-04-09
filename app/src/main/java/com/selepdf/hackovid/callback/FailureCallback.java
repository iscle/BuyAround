package com.selepdf.hackovid.callback;

public interface FailureCallback {
    void onFailure(FailureError error);

    enum FailureError {
        REQUEST_ERROR,
        INTERNAL_ERROR,
        NETWORK_ERROR
    }
}
