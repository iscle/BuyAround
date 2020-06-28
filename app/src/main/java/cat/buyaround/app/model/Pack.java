package cat.buyaround.app.model;

import java.io.Serializable;

public class Pack implements Serializable {

    private String name;
    private String description;
    private String thumbnail;
    private float price;
    private float rating;
    private String[] images;
    private OrderProduct[] products;
    private int points;
    private Store store;

    public Pack(String name, String description, String thumbnail, float price, float rating, String[] images, OrderProduct[] products, int points, Store store) {
        this.name = name;
        this.description = description;
        this.thumbnail = thumbnail;
        this.price = price;
        this.rating = rating;
        this.images = images;
        this.products = products;
        this.points = points;
        this.store = store;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getPoints() {
        return points;
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

    public OrderProduct[] getProducts() {
        return products;
    }

    public void setProducts(OrderProduct[] products) {
        this.products = products;
    }
}
