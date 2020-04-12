package com.selepdf.buyaround.network.model;

import com.selepdf.buyaround.model.Order;

public class OrderResponse extends Response {
    private Order[] orders;

    public Order[] getOrders() {
        return orders;
    }
}
