package cat.buyaround.app.model;

import com.google.gson.annotations.SerializedName;

public class Notification {
    @SerializedName("_id")
    private String id;
    private int minutesLeft;

    public Notification() {
        minutesLeft = -1;
    }

    public Notification(int minutesLeft) {
        this.minutesLeft = minutesLeft;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getMinutesLeft() {
        return minutesLeft;
    }

    public void setMinutesLeft(int minutesLeft) {
        this.minutesLeft = minutesLeft;
    }
}
