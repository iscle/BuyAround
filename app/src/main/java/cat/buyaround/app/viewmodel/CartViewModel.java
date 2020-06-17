package cat.buyaround.app.viewmodel;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import cat.buyaround.app.auth.UserManager;
import cat.buyaround.app.model.OrderProduct;
import cat.buyaround.app.network.BuyAroundRepository;

import java.util.List;

import javax.inject.Inject;

public class CartViewModel extends ViewModel {

    @Inject
    UserManager userManager;

    private BuyAroundRepository buyAroundRepository;

    @Inject
    public CartViewModel(BuyAroundRepository buyAroundRepository, UserManager userManager) {
        this.buyAroundRepository = buyAroundRepository;
        this.userManager = userManager;
    }

    private void requestrProducts() {
        //Get the info from elsewhere;
    }

    public LiveData<List<OrderProduct>> getOrderProducts() {
        return null;
    }

    public void addProduct(OrderProduct product) {

    }

    public void clearProductList() {

    }
}
