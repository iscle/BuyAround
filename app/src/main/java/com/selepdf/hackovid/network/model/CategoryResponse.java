package com.selepdf.hackovid.network.model;

import com.selepdf.hackovid.model.Category;

public class CategoryResponse extends Response {
    private Category[] categories;

    public Category[] getCategories() {
        return categories;
    }
}
