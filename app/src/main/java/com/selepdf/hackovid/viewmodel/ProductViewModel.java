package com.selepdf.hackovid.viewmodel;

import androidx.lifecycle.ViewModel;

import com.selepdf.hackovid.callback.ProductCallback;
import com.selepdf.hackovid.model.Product;
import com.selepdf.hackovid.network.HackovidRepository;

import javax.inject.Inject;

public class ProductViewModel extends ViewModel {

    private HackovidRepository hackovidRepository;

    @Inject
    public ProductViewModel(HackovidRepository hackovidRepository) {
        this.hackovidRepository = hackovidRepository;
    }

    public void addProduct(Product product, ProductCallback productCallback) {
        hackovidRepository.addProduct(product, productCallback);
    }
}
