package com.selepdf.buyaround.auth;

import com.selepdf.buyaround.model.User;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class UserManager {

    private TokenManager tokenManager;
    private User user;

    @Inject
    public UserManager(TokenManager tokenManager) {
        this.tokenManager = tokenManager;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
