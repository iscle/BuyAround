package cat.buyaround.app.network.model;

import cat.buyaround.app.model.Category;

public class CategoryResponse extends SimpleResponse {
    private Category[] categories;

    public Category[] getCategories() {
        return categories;
    }
}
