package com.selepdf.buyaround.viewmodel;

import androidx.lifecycle.ViewModel;

import com.selepdf.buyaround.callback.ProductCallback;
import com.selepdf.buyaround.model.Product;
import com.selepdf.buyaround.network.BuyAroundRepository;

import javax.inject.Inject;

public class AddProductViewModel extends ViewModel {

    private BuyAroundRepository buyAroundRepository;

    @Inject
    public AddProductViewModel(BuyAroundRepository buyAroundRepository) {
        this.buyAroundRepository = buyAroundRepository;
    }

    public void addProduct(Product product, ProductCallback productCallback) {
        buyAroundRepository.addProduct(product, productCallback);
    }
}
