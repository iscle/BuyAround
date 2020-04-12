package com.selepdf.buyaround.callback;

import com.selepdf.buyaround.model.Store;

public interface StoreCallback extends FailureCallback {
    void onStoresReceived(Store[] stores);
}
