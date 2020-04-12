package com.selepdf.buyaround.callback;

import com.selepdf.buyaround.model.Pack;

public interface PackCallback extends FailureCallback {
    void onPacksReceived(Pack[] packs);
}
