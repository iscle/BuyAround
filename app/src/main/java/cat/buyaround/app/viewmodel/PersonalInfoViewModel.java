package cat.buyaround.app.viewmodel;

import androidx.lifecycle.ViewModel;

import cat.buyaround.app.network.BuyAroundRepository;

import javax.inject.Inject;

public class PersonalInfoViewModel extends ViewModel {

    private BuyAroundRepository buyAroundRepository;

    @Inject
    public PersonalInfoViewModel(BuyAroundRepository buyAroundRepository) {
        this.buyAroundRepository = buyAroundRepository;
    }
}
