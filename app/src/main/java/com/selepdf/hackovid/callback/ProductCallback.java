package com.selepdf.hackovid.callback;

import com.selepdf.hackovid.model.Product;

import java.util.List;

public interface ProductCallback extends FailureCallback {
    void onProductsReceived(List<Product> products);
}
