package com.selepdf.hackovid.viewmodel;

import androidx.lifecycle.ViewModel;

import com.selepdf.hackovid.repository.HackovidRepository;

import javax.inject.Inject;

public class OrdersViewModel extends ViewModel {

    private HackovidRepository hackovidRepository;

    @Inject
    public OrdersViewModel(HackovidRepository hackovidRepository) {
        this.hackovidRepository = hackovidRepository;
    }
}
