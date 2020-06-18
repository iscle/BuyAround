package cat.buyaround.app.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import cat.buyaround.app.model.User;
import cat.buyaround.app.network.BuyAroundRepository;

public class AccountViewModel extends ViewModel {

    private BuyAroundRepository buyAroundRepository;
    private MutableLiveData<User> mUser;

    @Inject
    public AccountViewModel(BuyAroundRepository buyAroundRepository) {
        this.buyAroundRepository = buyAroundRepository;
        mUser = new MutableLiveData<>();
    }

    public void requestUserInfoByName(String name) {
        // Handle request
    }

    public LiveData<User> getUser() {
        return mUser;
    }
}
