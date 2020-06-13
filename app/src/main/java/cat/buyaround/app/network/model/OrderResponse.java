package cat.buyaround.app.network.model;

import cat.buyaround.app.model.Order;

public class OrderResponse extends Response {
    private Order[] orders;

    public Order[] getOrders() {
        return orders;
    }
}
