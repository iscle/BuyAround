package cat.buyaround.app.callback;

import cat.buyaround.app.model.Order;

public interface OrderCallback extends FailureCallback {
    void onOrdersReceived(Order[] orders);
}
