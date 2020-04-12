package com.selepdf.buyaround.network.model;

import com.selepdf.buyaround.model.Product;

public class ProductResponse extends Response {
    private Product[] products;

    public Product[] getProducts() {
        return products;
    }
}
