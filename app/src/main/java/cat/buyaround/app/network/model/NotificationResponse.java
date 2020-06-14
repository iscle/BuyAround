package cat.buyaround.app.network.model;

import cat.buyaround.app.model.Notification;

public class NotificationResponse extends SimpleResponse {
    private Notification[] notifications;

    public Notification[] getNotifications() {
        return notifications;
    }
}
