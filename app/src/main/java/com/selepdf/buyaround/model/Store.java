package com.selepdf.buyaround.model;

public class Store {
    private String name;
    private Direction direction;
    private String description;
    private float rating;
    private String thumbnail;
    private Category category;
    private long since;
    private Product[] products;

    public Store(String name, Direction direction, String description, float rating, String thumbnail, Category category, long since) {
        this.name = name;
        this.direction = direction;
        this.description = description;
        this.rating = rating;
        this.thumbnail = thumbnail;
        this.category = category;
        this.since = since;
    }

    public Store(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public long getSince() {
        return since;
    }

    public void setSince(long since) {
        this.since = since;
    }
}
