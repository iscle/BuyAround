package cat.buyaround.app.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import cat.buyaround.app.model.User;
import cat.buyaround.app.network.BuyAroundRepository;

import javax.inject.Inject;

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
