package com.selepdf.buyaround.callback;

import com.selepdf.buyaround.model.Pack;

import java.util.List;

public interface PackCallback extends FailureCallback {
    void onPacksReceived(List<Pack> packs);
}
