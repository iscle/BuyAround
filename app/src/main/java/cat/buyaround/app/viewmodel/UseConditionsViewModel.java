package cat.buyaround.app.viewmodel;

import androidx.lifecycle.ViewModel;

import cat.buyaround.app.network.BuyAroundRepository;

import javax.inject.Inject;

public class UseConditionsViewModel extends ViewModel {

    private BuyAroundRepository buyAroundRepository;

    @Inject
    public UseConditionsViewModel(BuyAroundRepository buyAroundRepository) {
        this.buyAroundRepository = buyAroundRepository;
    }
}
