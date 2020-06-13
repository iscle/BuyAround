package cat.buyaround.app.callback;

import cat.buyaround.app.model.User;

public interface UserCallback extends FailureCallback {
    void onUserReceived(User user);
}
