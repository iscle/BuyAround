package cat.buyaround.app.callback;

import cat.buyaround.app.model.Notification;

public interface NotificationCallback extends FailureCallback {
    void onNotificationsReceived(Notification[] notifications);
}
