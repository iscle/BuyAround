package cat.buyaround.app.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import cat.buyaround.app.callback.UserCallback;
import cat.buyaround.app.model.User;
import cat.buyaround.app.network.BuyAroundRepository;
import cat.buyaround.app.network.model.SimpleResponse;

public class PersonalInfoViewModel extends ViewModel {

    private BuyAroundRepository buyAroundRepository;
    private MutableLiveData<User> mUser;

    @Inject
    public PersonalInfoViewModel(BuyAroundRepository buyAroundRepository) {
        this.buyAroundRepository = buyAroundRepository;
        this.mUser = new MutableLiveData<>();
    }

    private void requestCurrentUser() {
        buyAroundRepository.getUser(new UserCallback() {
            @Override
            public void onUserReceived(User user) {
                mUser.postValue(user);
            }

            @Override
            public void onFailure(SimpleResponse.Status error) {

            }
        });
    }

    public LiveData<User> getCurrentUser() {
        requestCurrentUser();
        return mUser;
    }
}
