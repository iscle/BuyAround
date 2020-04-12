package com.selepdf.hackovid.viewmodel;

import androidx.lifecycle.ViewModel;

import com.selepdf.hackovid.callback.NotificationCallback;
import com.selepdf.hackovid.network.HackovidRepository;

import javax.inject.Inject;

public class NotificationsViewModel extends ViewModel {

    private HackovidRepository hackovidRepository;

    @Inject
    public NotificationsViewModel(HackovidRepository hackovidRepository) {
        this.hackovidRepository = hackovidRepository;
    }

    public void getNotifications(NotificationCallback notificationCallback) {
        hackovidRepository.getUserNotifications(notificationCallback);
    }
}
