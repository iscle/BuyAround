package cat.buyaround.app.viewmodel;

import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import cat.buyaround.app.network.BuyAroundRepository;

public class ScreenPacksViewModel extends ViewModel {

    private BuyAroundRepository buyAroundRepository;

    @Inject
    public ScreenPacksViewModel(BuyAroundRepository buyAroundRepository) {
        this.buyAroundRepository = buyAroundRepository;
    }
}
