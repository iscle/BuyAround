package cat.buyaround.app.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import cat.buyaround.app.callback.CategoryCallback;
import cat.buyaround.app.model.Category;
import cat.buyaround.app.network.BuyAroundRepository;
import cat.buyaround.app.network.model.SimpleResponse;

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
            public void onFailure(SimpleResponse.Status error) {

            }
        });
    }

    public LiveData<Category[]> getAllProductCategories() {
        requestAllProductCategories();
        return mCategories;
    }
}
