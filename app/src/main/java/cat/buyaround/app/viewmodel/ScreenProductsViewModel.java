package cat.buyaround.app.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import cat.buyaround.app.callback.ProductCallback;
import cat.buyaround.app.model.Product;
import cat.buyaround.app.model.Store;
import cat.buyaround.app.network.BuyAroundRepository;
import cat.buyaround.app.network.model.SimpleResponse;

public class ScreenProductsViewModel extends ViewModel {

    private BuyAroundRepository buyAroundRepository;
    private MutableLiveData<Product[]> mProducts;

    @Inject
    public ScreenProductsViewModel(BuyAroundRepository buyAroundRepository) {
        this.buyAroundRepository = buyAroundRepository;
        this.mProducts = new MutableLiveData<>();
    }

    private void requestStoreProducts(Store store) {
        buyAroundRepository.getStoreProducts(store, new ProductCallback() {
            @Override
            public void onProductsReceived(Product[] products) {
                mProducts.postValue(products);
            }

            @Override
            public void onFailure(SimpleResponse.Status error) {

            }
        });
    }

    public LiveData<Product[]> getStoreProducts(Store store) {
        requestStoreProducts(store);
        return mProducts;
    }

}
