package com.selepdf.hackovid.callback;

import android.app.Notification;

public interface NotificationCallback extends FailureCallback {
    void onNotificationsReceived(Notification[] notifications);
}
