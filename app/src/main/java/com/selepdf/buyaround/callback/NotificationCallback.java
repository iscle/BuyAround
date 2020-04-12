package com.selepdf.buyaround.callback;

import com.selepdf.buyaround.model.Notification;

public interface NotificationCallback extends FailureCallback {
    void onNotificationsReceived(Notification[] notifications);
}
