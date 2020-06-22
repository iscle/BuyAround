package cat.buyaround.app.viewmodel;

import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import cat.buyaround.app.model.Direction;
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

    public Store getStore() {
        return store;
    }

    public String getStorePhone() {
        return store.getPhone();
    }

    public String getStoreCategory() {
        return store.getCategory().getName();
    }

    public Direction getStoreDirection() {
        return store.getDirection();
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
