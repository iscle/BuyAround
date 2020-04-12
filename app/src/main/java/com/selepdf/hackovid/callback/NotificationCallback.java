package com.selepdf.hackovid.callback;

import com.selepdf.hackovid.model.Notification;

public interface NotificationCallback extends FailureCallback {
    void onNotificationsReceived(Notification[] notifications);
}
