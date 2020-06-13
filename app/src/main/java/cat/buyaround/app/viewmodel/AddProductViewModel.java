package cat.buyaround.app.viewmodel;

import androidx.lifecycle.ViewModel;

import cat.buyaround.app.callback.CategoryCallback;
import cat.buyaround.app.callback.ProductCallback;
import cat.buyaround.app.model.Product;
import cat.buyaround.app.network.BuyAroundRepository;

import javax.inject.Inject;

public class AddProductViewModel extends ViewModel {

    private BuyAroundRepository buyAroundRepository;

    @Inject
    public AddProductViewModel(BuyAroundRepository buyAroundRepository) {
        this.buyAroundRepository = buyAroundRepository;
    }

    public void addProduct(Product product, ProductCallback callback) {
        buyAroundRepository.addProduct(product, callback);
    }

    public void getProductCategories(CategoryCallback callback) {
        buyAroundRepository.getAllProductCategories(callback);
    }
}
