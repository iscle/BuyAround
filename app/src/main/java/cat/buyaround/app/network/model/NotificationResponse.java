package cat.buyaround.app.network.model;

import cat.buyaround.app.model.Notification;

public class NotificationResponse extends Response {
    private Notification[] notifications;

    public Notification[] getNotifications() {
        return notifications;
    }
}
