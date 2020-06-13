package cat.buyaround.app.viewmodel;

import androidx.lifecycle.ViewModel;

import cat.buyaround.app.network.BuyAroundRepository;

import javax.inject.Inject;

public class AddressViewModel extends ViewModel {

    private BuyAroundRepository buyAroundRepository;

    @Inject
    public AddressViewModel(BuyAroundRepository buyAroundRepository) {
        this.buyAroundRepository = buyAroundRepository;
    }
}
