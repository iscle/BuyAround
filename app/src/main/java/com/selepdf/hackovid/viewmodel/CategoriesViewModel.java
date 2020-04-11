package com.selepdf.hackovid.viewmodel;

import androidx.lifecycle.ViewModel;

import com.selepdf.hackovid.callback.CategoryCallback;
import com.selepdf.hackovid.network.HackovidRepository;

import javax.inject.Inject;

public class CategoriesViewModel extends ViewModel {

    private HackovidRepository hackovidRepository;

    @Inject
    public CategoriesViewModel(HackovidRepository hackovidRepository) {
        this.hackovidRepository = hackovidRepository;
    }

    public void getAllProductCategories(CategoryCallback categoryCallback) {
        hackovidRepository.getAllProductCategories(categoryCallback);
    }
}
