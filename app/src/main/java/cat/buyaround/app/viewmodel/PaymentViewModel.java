package cat.buyaround.app.viewmodel;

import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import cat.buyaround.app.network.BuyAroundRepository;

public class PaymentViewModel extends ViewModel {

    private BuyAroundRepository buyAroundRepository;

    @Inject
    public PaymentViewModel(BuyAroundRepository buyAroundRepository) {
        this.buyAroundRepository = buyAroundRepository;
    }
}
