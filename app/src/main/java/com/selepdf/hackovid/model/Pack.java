package com.selepdf.hackovid.model;

public class Pack extends GeneralItem{

    private Product[] products;

    public Pack(String name, String description, String thumbnail, float price, float rating, String[] images, Product[] products) {
        super(name, description, thumbnail, price, rating, images);
        this.products = products;
    }

    public Product[] getProducts() {
        return products;
    }

    public void setProducts(Product[] products) {
        this.products = products;
    }
}
