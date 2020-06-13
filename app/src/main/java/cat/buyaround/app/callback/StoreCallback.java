package cat.buyaround.app.callback;

import cat.buyaround.app.model.Store;

public interface StoreCallback extends FailureCallback {
    void onStoresReceived(Store[] stores);
}
