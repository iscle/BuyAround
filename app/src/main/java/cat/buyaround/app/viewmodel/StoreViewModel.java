package cat.buyaround.app.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import cat.buyaround.app.model.Direction;
import cat.buyaround.app.model.Pack;
import cat.buyaround.app.model.Product;
import cat.buyaround.app.model.Store;
import cat.buyaround.app.network.BuyAroundRepository;

public class StoreViewModel extends ViewModel {

    private BuyAroundRepository buyAroundRepository;
    private Store store;
    private MutableLiveData<List<Product>> mProducts;
    private MutableLiveData<List<Pack>> mPacks;

    @Inject
    public StoreViewModel(BuyAroundRepository buyAroundRepository) {
        this.buyAroundRepository = buyAroundRepository;
        this.store = null;
        this.mProducts = new MutableLiveData<>();
        this.mPacks = new MutableLiveData<>();
    }

    public void setStore(Store store) {
        this.store = store;
    }

    private void requestStoreProducts() {

    }

    private void requestStorePacks() {

    }

    public String getStoreCategory() {
        return store.getCategory().getName();
    }

    public Direction getStoreDirection() {
        return store.getDirection();
    }

    public LiveData<List<Product>> getStoreProducts() {
        requestStoreProducts();
        return mProducts;
    }

    public LiveData<List<Pack>> getStorePacks() {
        requestStorePacks();
        return mPacks;
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
