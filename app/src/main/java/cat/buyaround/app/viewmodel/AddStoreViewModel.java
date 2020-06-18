package cat.buyaround.app.viewmodel;

import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import cat.buyaround.app.callback.CategoryCallback;
import cat.buyaround.app.callback.StoreCallback;
import cat.buyaround.app.model.Store;
import cat.buyaround.app.network.BuyAroundRepository;

public class AddStoreViewModel extends ViewModel {

    private BuyAroundRepository buyAroundRepository;

    @Inject
    public AddStoreViewModel(BuyAroundRepository buyAroundRepository) {
        this.buyAroundRepository = buyAroundRepository;
    }

    public void addStore(String name, String description, StoreCallback callback) {
        Store store = new Store(name, description);
        buyAroundRepository.addStore(store, callback);
    }

    public void getStoreCategories(CategoryCallback callback) {
        buyAroundRepository.getAllStoreCategories(callback);
    }
}
