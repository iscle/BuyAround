package cat.buyaround.app.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import cat.buyaround.app.callback.PackCallback;
import cat.buyaround.app.callback.ProductCallback;
import cat.buyaround.app.callback.StoreCallback;
import cat.buyaround.app.model.Pack;
import cat.buyaround.app.model.Product;
import cat.buyaround.app.model.Store;
import cat.buyaround.app.network.BuyAroundRepository;
import cat.buyaround.app.network.model.SimpleResponse;

public class HomeViewModel extends ViewModel {

    private BuyAroundRepository buyAroundRepository;
    private MutableLiveData<Store[]> mStores;
    private MutableLiveData<Pack[]> mPacks;
    private MutableLiveData<Product[]> mProducts;

    @Inject
    public HomeViewModel(BuyAroundRepository buyAroundRepository) {
        this.buyAroundRepository = buyAroundRepository;
        this.mStores = new MutableLiveData<>();
        this.mPacks = new MutableLiveData<>();
        this.mProducts = new MutableLiveData<>();
    }

    private void requestStoresAround() {
        // Handle request
        buyAroundRepository.getAllStores(new StoreCallback() {
            @Override
            public void onStoresReceived(Store[] stores) {
                mStores.postValue(stores);
            }

            @Override
            public void onFailure(SimpleResponse.Status error) {

            }
        });
    }

    private void requestPacks() {
        // Handle request
        buyAroundRepository.getAllPacks(new PackCallback() {
            @Override
            public void onPacksReceived(Pack[] packs) {
                mPacks.postValue(packs);
            }

            @Override
            public void onFailure(SimpleResponse.Status error) {

            }
        });
    }

    private void requestProducts() {
        // Handle request
        buyAroundRepository.getAllProducts(new ProductCallback() {
            @Override
            public void onProductsReceived(Product[] products) {
                mProducts.postValue(products);
            }

            @Override
            public void onFailure(SimpleResponse.Status error) {

            }
        });
    }

    public LiveData<Store[]> getStores() {
        requestStoresAround();
        return mStores;
    }

    public LiveData<Pack[]> getPacks() {
        requestPacks();
        return mPacks;
    }

    public LiveData<Product[]> getProducts() {
        requestProducts();
        return mProducts;
    }
}
