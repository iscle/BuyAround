package cat.buyaround.app.viewmodel;

import androidx.lifecycle.ViewModel;

import cat.buyaround.app.network.BuyAroundRepository;

import javax.inject.Inject;

public class AddressesViewModel extends ViewModel {

    private BuyAroundRepository buyAroundRepository;

    @Inject
    public AddressesViewModel(BuyAroundRepository buyAroundRepository) {
        this.buyAroundRepository = buyAroundRepository;
    }
}
