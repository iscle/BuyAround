package com.selepdf.hackovid.model;

import com.google.gson.annotations.SerializedName;

public abstract class Category {
    @SerializedName("_id")
    private String id;
    private String name;
    private String thumbnail;

    public Category(String name) {
        this.name = name;
    }

    public Category(String name, String thumbnail) {
        this.name = name;
        this.thumbnail = thumbnail;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}
