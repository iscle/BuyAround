package cat.buyaround.app.callback;

import cat.buyaround.app.model.Product;

public interface ProductCallback extends FailureCallback {
    void onProductsReceived(Product[] products);
}
