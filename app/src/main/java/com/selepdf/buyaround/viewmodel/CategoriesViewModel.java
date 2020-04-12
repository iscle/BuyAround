package com.selepdf.buyaround.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.selepdf.buyaround.callback.CategoryCallback;
import com.selepdf.buyaround.model.Category;
import com.selepdf.buyaround.network.BuyAroundRepository;

import javax.inject.Inject;

public class CategoriesViewModel extends ViewModel {

    private BuyAroundRepository buyAroundRepository;
    private MutableLiveData<Category[]> mCategories;

    @Inject
    public CategoriesViewModel(BuyAroundRepository buyAroundRepository) {
        this.buyAroundRepository = buyAroundRepository;
        this.mCategories = new MutableLiveData<>();
    }

    public void requestAllProductCategories() {
        buyAroundRepository.getAllProductCategories(new CategoryCallback() {
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
