package cat.buyaround.app.viewmodel;

import androidx.lifecycle.ViewModel;

import cat.buyaround.app.callback.SearchCallback;
import cat.buyaround.app.network.BuyAroundRepository;

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
