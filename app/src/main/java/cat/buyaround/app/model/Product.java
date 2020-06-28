package cat.buyaround.app.model;

import java.io.Serializable;

public class Product extends Item implements Serializable {

    private Unit unit;


    public Product(String name, String description, float price, float rating, String[] images, Category category, Store store, int points, Unit unit) {
        super(name, description, price, rating, images, category, store, points);
        this.unit = unit;
    }

    public Unit getUnit() {
        return unit;
    }
}
