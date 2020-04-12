package com.selepdf.hackovid.callback;

import com.selepdf.hackovid.model.Order;

public interface OrderCallback extends FailureCallback {
    void onOrdersReceived(Order[] orders);
}
