package cat.buyaround.app.viewmodel;

import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import cat.buyaround.app.model.Product;
import cat.buyaround.app.network.BuyAroundRepository;

public class ProductViewModel extends ViewModel {

    private BuyAroundRepository buyAroundRepository;
    private Product product;

    @Inject
    public ProductViewModel(BuyAroundRepository buyAroundRepository) {
        this.buyAroundRepository = buyAroundRepository;
        this.product = null;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getProductName() {
        return product.getName();
    }

    public float getProductRating() {
        return product.getRating();
    }

    public String getProductDescription() {
        return product.getDescription();
    }

    public int getProductPoints() {
        return product.getPoints();
    }

    public float getProductPrice() {
        return product.getPrice();
    }

    public String getProductUnit() {
        return product.getUnit().getName();
    }

    public String[] getProductImages() {
        return product.getImages();
    }
}
