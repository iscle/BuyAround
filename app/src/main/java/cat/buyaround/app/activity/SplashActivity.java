package cat.buyaround.app.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import javax.inject.Inject;

import cat.buyaround.app.R;
import cat.buyaround.app.auth.UserManager;
import cat.buyaround.app.callback.UserCallback;
import cat.buyaround.app.model.User;
import cat.buyaround.app.network.BuyAroundRepository;
import cat.buyaround.app.network.model.SimpleResponse;
import dagger.android.support.DaggerAppCompatActivity;

import static cat.buyaround.app.auth.UserManager.ACTION_USER_UPDATED;

public class SplashActivity extends DaggerAppCompatActivity {

    @Inject
    protected BuyAroundRepository repository;
    @Inject
    protected UserManager userManager;

    private boolean receiverRegistered;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        receiverRegistered = false;

        if (userManager.hasSession()) {
            setContentView(R.layout.activity_splash);

            IntentFilter filter = new IntentFilter();
            filter.addAction(ACTION_USER_UPDATED);
            LocalBroadcastManager.getInstance(this).registerReceiver(receiver, filter);
            receiverRegistered = true;

            repository.getUser(new UserCallback() {
                @Override
                public void onUserReceived(User user) {
                    userManager.setUser(user);
                }

                @Override
                public void onFailure(SimpleResponse.Status error) {
                    userManager.setUser(null);
                }
            });
        } else {
            startMainActivity();
        }
    }

    private void startMainActivity() {
        Intent i = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (receiverRegistered) {
            LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver);
            receiverRegistered = false;
        }
    }

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(ACTION_USER_UPDATED)) {
                startMainActivity();
            }
        }
    };
}
