package cat.buyaround.app.viewmodel;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import cat.buyaround.app.auth.UserManager;
import cat.buyaround.app.model.ItemGroup;
import cat.buyaround.app.network.BuyAroundRepository;

public class CartViewModel extends ViewModel {

    @Inject
    UserManager userManager;

    private BuyAroundRepository buyAroundRepository;
    private MutableLiveData<List<ItemGroup>> mProducts;

    @Inject
    public CartViewModel(BuyAroundRepository buyAroundRepository, UserManager userManager) {
        this.buyAroundRepository = buyAroundRepository;
        this.userManager = userManager;
        mProducts = new MutableLiveData<>();
    }

    private void requestProducts() {
        // TODO: API CALL
    }

    public LiveData<List<ItemGroup>> getOrderProducts() {
        requestProducts();
        return mProducts;
    }

    public void addProduct(ItemGroup product) {
        // TODO: API CALL
    }

    public void clearProductList() {
        mProducts.postValue(null);
        // TODO: API CALL
    }

    public float getTotalCost() {
        float totalPrice = 0.0f;

        for (ItemGroup prod : mProducts.getValue()) {
            totalPrice += prod.getTotalPrice();
        }

        return totalPrice;
    }
}
