package com.selepdf.hackovid.viewmodel;

import androidx.lifecycle.ViewModel;

import com.selepdf.hackovid.network.HackovidRepository;

import javax.inject.Inject;

public class StoreViewModel extends ViewModel {

    private HackovidRepository hackovidRepository;

    @Inject
    public StoreViewModel(HackovidRepository hackovidRepository) {
        this.hackovidRepository = hackovidRepository;
    }
}
