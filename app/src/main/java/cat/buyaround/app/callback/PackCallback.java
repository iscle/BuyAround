package cat.buyaround.app.callback;

import cat.buyaround.app.model.Pack;

public interface PackCallback extends FailureCallback {
    void onPacksReceived(Pack[] packs);
}
