package com.selepdf.buyaround.callback;

import com.selepdf.buyaround.model.Product;

public interface ProductCallback extends FailureCallback {
    void onProductsReceived(Product[] products);
}
