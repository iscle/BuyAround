package com.selepdf.hackovid.callback;

import com.selepdf.hackovid.model.Store;

import java.util.List;

public interface StoreCallback {
    void onStoresReceived(List<Store> stores);
    void onStore(Store store);
}
