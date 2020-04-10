package com.selepdf.hackovid.network.model;

import com.selepdf.hackovid.model.Product;

public class ProductResponse extends Response {
    private Product[] products;

    public Product[] getProducts() {
        return products;
    }
}
