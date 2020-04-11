package com.selepdf.hackovid.model;

import com.google.gson.annotations.SerializedName;

public abstract class Category {
    @SerializedName("_id")
    private String id;
    private String name;

    public Category(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
