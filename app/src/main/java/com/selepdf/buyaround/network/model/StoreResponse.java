package com.selepdf.buyaround.network.model;

import com.selepdf.buyaround.model.Store;

public class StoreResponse extends Response {
    private Store[] stores;

    public Store[] getStores() {
        return stores;
    }
}
