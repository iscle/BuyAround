package cat.buyaround.app.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import cat.buyaround.app.callback.NotificationCallback;
import cat.buyaround.app.model.Notification;
import cat.buyaround.app.network.BuyAroundRepository;

import javax.inject.Inject;

public class NotificationsViewModel extends ViewModel {

    private BuyAroundRepository buyAroundRepository;
    private MutableLiveData<Notification[]> mNotifications;

    @Inject
    public NotificationsViewModel(BuyAroundRepository buyAroundRepository) {
        this.buyAroundRepository = buyAroundRepository;
        this.mNotifications = new MutableLiveData<>();
    }

    public void requestUserNotifications() {
        buyAroundRepository.getUserNotifications(new NotificationCallback() {
            @Override
            public void onNotificationsReceived(Notification[] notifications) {
                mNotifications.postValue(notifications);
            }

            @Override
            public void onFailure(FailureError error) {

            }
        });
    }

    public LiveData<Notification[]> getUserNotifications() {
        requestUserNotifications();
        return mNotifications;
    }
}
