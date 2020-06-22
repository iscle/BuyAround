package cat.buyaround.app.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import cat.buyaround.app.model.Pack;
import cat.buyaround.app.model.Store;
import cat.buyaround.app.network.BuyAroundRepository;

public class ScreenPacksViewModel extends ViewModel {

    private BuyAroundRepository buyAroundRepository;
    private MutableLiveData<Pack[]> mPacks;

    @Inject
    public ScreenPacksViewModel(BuyAroundRepository buyAroundRepository) {
        this.buyAroundRepository = buyAroundRepository;
        this.mPacks = new MutableLiveData<>();
    }

    private void requestStorePacks(Store store) {
        // TODO: IMPLEMENT
    }

    public LiveData<Pack[]> getStorePacks(Store store) {
        requestStorePacks(store);
        return mPacks;
    }

}
