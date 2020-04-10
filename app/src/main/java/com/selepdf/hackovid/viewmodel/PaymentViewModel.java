package com.selepdf.hackovid.viewmodel;

import androidx.lifecycle.ViewModel;

import com.selepdf.hackovid.network.HackovidRepository;

import javax.inject.Inject;

public class PaymentViewModel extends ViewModel {

    private HackovidRepository hackovidRepository;

    @Inject
    public PaymentViewModel(HackovidRepository hackovidRepository) {
        this.hackovidRepository = hackovidRepository;
    }
}
