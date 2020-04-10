package com.selepdf.hackovid.viewmodel;

import androidx.lifecycle.ViewModel;

import com.selepdf.hackovid.callback.SearchCallback;
import com.selepdf.hackovid.network.HackovidRepository;

import java.util.ArrayList;

import javax.inject.Inject;

public class SearchViewModel extends ViewModel {

    private HackovidRepository hackovidRepository;

    @Inject
    public SearchViewModel(HackovidRepository hackovidRepository) {
        this.hackovidRepository = hackovidRepository;
    }

    public void search(String input, SearchCallback searchCallback) {
        searchCallback.onSearch(new ArrayList<>());
    }

}
