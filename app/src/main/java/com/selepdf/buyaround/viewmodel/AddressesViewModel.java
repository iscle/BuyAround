package com.selepdf.buyaround.viewmodel;

import androidx.lifecycle.ViewModel;

import com.selepdf.buyaround.network.BuyAroundRepository;

import javax.inject.Inject;

public class AddressesViewModel extends ViewModel {

    private BuyAroundRepository buyAroundRepository;

    @Inject
    public AddressesViewModel(BuyAroundRepository buyAroundRepository) {
        this.buyAroundRepository = buyAroundRepository;
    }
}
