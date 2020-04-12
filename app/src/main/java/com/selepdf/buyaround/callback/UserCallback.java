package com.selepdf.buyaround.callback;

import com.selepdf.buyaround.model.User;

public interface UserCallback extends FailureCallback {
    void onUserReceived(User user);
}
