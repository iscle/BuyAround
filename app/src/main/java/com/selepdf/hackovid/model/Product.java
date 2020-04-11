package com.selepdf.hackovid.model;

public class Product extends GeneralItem {

    private Category category;
    private Store store;

    public Product(String name, String description, String thumbnail, float price, float rating, String[] images, Category category, Store store) {
        super(name, description, thumbnail, price, rating, images);
        this.category = category;
        this.store = store;
    }

    public Product(String name, String description, String price) {
        super(name, description, price);
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }
}
