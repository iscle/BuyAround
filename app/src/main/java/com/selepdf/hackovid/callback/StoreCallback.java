package com.selepdf.hackovid.callback;

import com.selepdf.hackovid.model.Store;

public interface StoreCallback extends FailureCallback {
    void onStoresReceived(Store[] stores);
}
