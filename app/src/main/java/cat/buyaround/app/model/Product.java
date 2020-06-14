package cat.buyaround.app.model;

import android.text.TextUtils;

import java.io.Serializable;

public class Product implements Serializable {

    private String name;
    private String description;
    private String thumbnail;
    private float price;
    private float rating;
    private String[] images;
    private Category category;
    private Store store;
    private int points;

    public Product(String name, String description, String thumbnail, float price, float rating, String[] images, Category category, Store store, int points) {
        this.name = name;
        this.description = description;
        this.thumbnail = thumbnail;
        this.price = price;
        this.rating = rating;
        this.images = images;
        this.category = category;
        this.store = store;
        this.points = points;
    }

    public Product(String name, String description, String price) {
        this.name = name;
        this.description = description;
        if (!TextUtils.isEmpty(price)) {
            this.price = Float.parseFloat(price);
        }
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String[] getImages() {
        return images;
    }

    public void setImages(String[] images) {
        this.images = images;
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
