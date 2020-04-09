package com.selepdf.hackovid.callback;

import com.selepdf.hackovid.model.Pack;

import java.util.List;

public interface PackCallback extends FailureCallback {
    void onPacksReceived(List<Pack> packs);
}
