package cat.buyaround.app.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import cat.buyaround.app.callback.CategoryCallback;
import cat.buyaround.app.model.Category;
import cat.buyaround.app.network.BuyAroundRepository;

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
