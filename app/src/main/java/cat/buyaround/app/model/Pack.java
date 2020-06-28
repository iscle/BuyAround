package cat.buyaround.app.model;

import java.io.Serializable;

public class Pack extends Item implements Serializable {

    private ItemGroup[] products;

    public Pack(String name, String description, float price, float rating, String[] images, Category category, Store store, int points, ItemGroup[] products) {
        super(name, description, price, rating, images, category, store, points);
        this.products = products;
    }

    public ItemGroup[] getProducts() {
        return products;
    }
}
