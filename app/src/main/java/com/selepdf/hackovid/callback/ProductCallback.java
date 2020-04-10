package com.selepdf.hackovid.callback;

import com.selepdf.hackovid.model.Product;

public interface ProductCallback extends FailureCallback {
    void onProductsReceived(Product[] products);
}
