package com.selepdf.buyaround.viewmodel;

import androidx.lifecycle.ViewModel;

import com.selepdf.buyaround.callback.SearchCallback;
import com.selepdf.buyaround.network.BuyAroundRepository;

import java.util.ArrayList;

import javax.inject.Inject;

public class SearchViewModel extends ViewModel {

    private BuyAroundRepository buyAroundRepository;

    @Inject
    public SearchViewModel(BuyAroundRepository buyAroundRepository) {
        this.buyAroundRepository = buyAroundRepository;
    }

    public void search(String input, SearchCallback searchCallback) {
        searchCallback.onSearch(new ArrayList<>());
    }

}
