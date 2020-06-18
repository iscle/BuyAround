package cat.buyaround.app.auth;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.TextUtils;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import javax.inject.Inject;
import javax.inject.Singleton;

import cat.buyaround.app.model.User;

@Singleton
public class UserManager {
    public static final String ACTION_USER_UPDATED = "cat.buyaround.app.action.USER_UPDATED";

    private static final String USER_MANAGER_PREF_NAME = "user_manager";
    private static final String TOKEN_PREF = "token";

    private SharedPreferences sharedPreferences;
    private String token;
    private User user;
    private LocalBroadcastManager localBroadcastManager;

    @Inject
    public UserManager(Context context) {
        this.sharedPreferences = context.getSharedPreferences(USER_MANAGER_PREF_NAME, Context.MODE_PRIVATE);
        this.token = sharedPreferences.getString(TOKEN_PREF, "");
        this.localBroadcastManager = LocalBroadcastManager.getInstance(context);
    }

    public static boolean isTokenValid(String token) {
        return !TextUtils.isEmpty(token);
    }

    public boolean hasSession() {
        return isTokenValid(token);
    }

    public boolean isLoggedIn() {
        return user != null;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
        localBroadcastManager.sendBroadcast(new Intent(ACTION_USER_UPDATED));
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
        SharedPreferences.Editor sharedPrefEditor = sharedPreferences.edit();
        sharedPrefEditor.putString(TOKEN_PREF, token);
        sharedPrefEditor.apply();
    }
}
