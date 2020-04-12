package com.selepdf.buyaround.callback;

import com.selepdf.buyaround.model.Order;

public interface OrderCallback extends FailureCallback {
    void onOrdersReceived(Order[] orders);
}
