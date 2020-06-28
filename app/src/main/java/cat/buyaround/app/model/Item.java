package cat.buyaround.app.model;

import java.io.Serializable;

public abstract class Item implements Serializable {

    private String name;
    private String description;
    private float price;
    private float rating;
    private String[] images;
    private Category category;
    private Store store;
    private int points;

    public Item(String name, String description, float price, float rating, String[] images, Category category, Store store, int points) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.rating = rating;
        this.images = images;
        this.category = category;
        this.store = store;
        this.points = points;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public float getPrice() {
        return price;
    }

    public float getRating() {
        return rating;
    }

    public String[] getImages() {
        return images;
    }

    public Category getCategory() {
        return category;
    }

    public Store getStore() {
        return store;
    }

    public int getPoints() {
        return points;
    }
}
