package com.selepdf.buyaround.network.model;

import com.selepdf.buyaround.model.Category;

public class CategoryResponse extends Response {
    private Category[] categories;

    public Category[] getCategories() {
        return categories;
    }
}
