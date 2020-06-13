package cat.buyaround.app.network.model;

import cat.buyaround.app.model.Category;

public class CategoryResponse extends Response {
    private Category[] categories;

    public Category[] getCategories() {
        return categories;
    }
}
