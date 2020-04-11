package com.selepdf.hackovid.network.model;

import com.selepdf.hackovid.model.Store;

public class StoreResponse extends Response {
    private Store[] stores;

    public Store[] getStores() {
        return stores;
    }
}
