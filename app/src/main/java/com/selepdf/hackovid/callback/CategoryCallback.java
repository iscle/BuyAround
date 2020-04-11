package com.selepdf.hackovid.callback;

import com.selepdf.hackovid.model.Category;

public interface CategoryCallback extends FailureCallback {
    void onCategoriesReceived(Category[] categories);
}
