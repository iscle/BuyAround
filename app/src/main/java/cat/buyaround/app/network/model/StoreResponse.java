package cat.buyaround.app.network.model;

import cat.buyaround.app.model.Store;

public class StoreResponse extends SimpleResponse {
    private Store[] stores;

    public Store[] getStores() {
        return stores;
    }
}
