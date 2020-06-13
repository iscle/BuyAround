package cat.buyaround.app.callback;

import cat.buyaround.app.model.Category;

public interface CategoryCallback extends FailureCallback {
    void onCategoriesReceived(Category[] categories);
}
