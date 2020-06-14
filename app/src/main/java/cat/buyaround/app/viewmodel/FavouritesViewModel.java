package cat.buyaround.app.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import cat.buyaround.app.callback.PackCallback;
import cat.buyaround.app.callback.ProductCallback;
import cat.buyaround.app.callback.StoreCallback;
import cat.buyaround.app.model.Pack;
import cat.buyaround.app.model.Product;
import cat.buyaround.app.model.Store;
import cat.buyaround.app.network.BuyAroundRepository;
import cat.buyaround.app.network.model.SimpleResponse;

import javax.inject.Inject;

public class FavouritesViewModel extends ViewModel {

    private BuyAroundRepository buyAroundRepository;
    private MutableLiveData<Store[]> mStores;
    private MutableLiveData<Pack[]> mPacks;
    private MutableLiveData<Product[]> mProducts;

    @Inject
    public FavouritesViewModel(BuyAroundRepository buyAroundRepository) {
        this.buyAroundRepository = buyAroundRepository;
        this.mStores = new MutableLiveData<>();
        this.mPacks = new MutableLiveData<>();
        this.mProducts = new MutableLiveData<>();
    }

    public void requestFavouriteStores() {
        // Handle request
        buyAroundRepository.getFavouriteStores(new StoreCallback() {
            @Override
            public void onStoresReceived(Store[] stores) {
                mStores.postValue(stores);
            }

            @Override
            public void onFailure(SimpleResponse.Status error) {

            }
        });

    }

    public void requestFavouritePacks() {
        // Handle request
        buyAroundRepository.getFavouritePacks(new PackCallback() {
            @Override
            public void onPacksReceived(Pack[] packs) {
                mPacks.postValue(packs);
            }

            @Override
            public void onFailure(SimpleResponse.Status error) {

            }
        });
    }

    public void requestFavouriteProducts() {
        // Handle request
        buyAroundRepository.getFavouriteProducts(new ProductCallback() {
            @Override
            public void onProductsReceived(Product[] products) {
                mProducts.postValue(products);
            }

            @Override
            public void onFailure(SimpleResponse.Status error) {

            }
        });
    }

    public LiveData<Store[]> getFavouriteStores() {
        requestFavouriteStores();;
        return mStores;
    }

    public LiveData<Pack[]> getFavouritePacks() {
        requestFavouritePacks();
        return mPacks;
    }

    public LiveData<Product[]> getFavouriteProducts() {
        requestFavouriteProducts();
        return mProducts;
    }
}
