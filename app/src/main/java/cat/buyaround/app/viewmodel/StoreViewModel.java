package cat.buyaround.app.viewmodel;

import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import cat.buyaround.app.model.Store;
import cat.buyaround.app.network.BuyAroundRepository;

public class StoreViewModel extends ViewModel {

    private BuyAroundRepository buyAroundRepository;
    private Store store;

    @Inject
    public StoreViewModel(BuyAroundRepository buyAroundRepository) {
        this.buyAroundRepository = buyAroundRepository;
        this.store = null;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public String getStoreName() {
        return store.getName();
    }

    public float getStoreRating() {
        return store.getRating();
    }

    public String getStoreDescription() {
        return store.getDescription();
    }

    public String[] getStoreImages() {
        return store.getImages();
    }
}
