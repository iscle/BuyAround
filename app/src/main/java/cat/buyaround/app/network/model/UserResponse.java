package cat.buyaround.app.network.model;

import cat.buyaround.app.model.User;

public class UserResponse extends SimpleResponse {
    private User user;

    public User getUser() {
        return user;
    }
}
