package com.selepdf.hackovid.viewmodel;

import androidx.lifecycle.ViewModel;

import com.selepdf.hackovid.network.HackovidRepository;

import javax.inject.Inject;

public class UseConditionsViewModel extends ViewModel {

    private HackovidRepository hackovidRepository;

    @Inject
    public UseConditionsViewModel(HackovidRepository hackovidRepository) {
        this.hackovidRepository = hackovidRepository;
    }
}
