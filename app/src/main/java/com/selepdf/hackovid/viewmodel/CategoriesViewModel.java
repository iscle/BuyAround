package com.selepdf.hackovid.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.selepdf.hackovid.callback.CategoryCallback;
import com.selepdf.hackovid.model.Category;
import com.selepdf.hackovid.network.HackovidRepository;

import javax.inject.Inject;

public class CategoriesViewModel extends ViewModel {

    private HackovidRepository hackovidRepository;
    private MutableLiveData<Category[]> mCategories;

    @Inject
    public CategoriesViewModel(HackovidRepository hackovidRepository) {
        this.hackovidRepository = hackovidRepository;
        this.mCategories = new MutableLiveData<>();
    }

    public void requestAllProductCategories() {
        hackovidRepository.getAllProductCategories(new CategoryCallback() {
            @Override
            public void onCategoriesReceived(Category[] categories) {
                mCategories.postValue(categories);
            }

            @Override
            public void onFailure(FailureError error) {

            }
        });
    }

    public LiveData<Category[]> getAllProductCategories() {
        requestAllProductCategories();
        return mCategories;
    }
}
