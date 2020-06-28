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

    }

    public LiveData<List<ItemGroup>> getOrderProducts() {
        requestProducts();
        return mProducts;
    }

    public void addProduct(ItemGroup product) {

    }

    public void clearProductList() {

    }

    public float getTotalCost() {
        return 0.0f;
    }
}
