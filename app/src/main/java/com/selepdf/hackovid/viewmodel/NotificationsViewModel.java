package com.selepdf.hackovid.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.selepdf.hackovid.callback.NotificationCallback;
import com.selepdf.hackovid.model.Notification;
import com.selepdf.hackovid.network.HackovidRepository;

import javax.inject.Inject;

public class NotificationsViewModel extends ViewModel {

    private HackovidRepository hackovidRepository;
    private MutableLiveData<Notification[]> mNotifications;

    @Inject
    public NotificationsViewModel(HackovidRepository hackovidRepository) {
        this.hackovidRepository = hackovidRepository;
        this.mNotifications = new MutableLiveData<>();
    }

    public void requestUserNotifications() {
        hackovidRepository.getUserNotifications(new NotificationCallback() {
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
