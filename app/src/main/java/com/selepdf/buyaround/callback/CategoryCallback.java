package com.selepdf.buyaround.callback;

import com.selepdf.buyaround.model.Category;

public interface CategoryCallback extends FailureCallback {
    void onCategoriesReceived(Category[] categories);
}
