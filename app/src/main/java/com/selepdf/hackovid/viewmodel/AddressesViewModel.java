package com.selepdf.hackovid.viewmodel;

import androidx.lifecycle.ViewModel;

import com.selepdf.hackovid.network.HackovidRepository;

import javax.inject.Inject;

public class AddressesViewModel extends ViewModel {

    private HackovidRepository hackovidRepository;

    @Inject
    public AddressesViewModel(HackovidRepository hackovidRepository) {
        this.hackovidRepository = hackovidRepository;
    }
}
