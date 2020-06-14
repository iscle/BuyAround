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
import dagger.android.support.DaggerAppCompatActivity;

import static cat.buyaround.app.auth.UserManager.ACTION_USER_UPDATED;

public class SplashActivity extends DaggerAppCompatActivity {

    @Inject
    protected UserManager userManager;

    private boolean receiverRegistered;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        receiverRegistered = false;
        setContentView(R.layout.activity_splash);

        if (userManager.hasSession()) {

            IntentFilter filter = new IntentFilter();
            filter.addAction(ACTION_USER_UPDATED);
            LocalBroadcastManager.getInstance(this).registerReceiver(receiver, filter);
            receiverRegistered = true;

            userManager.updateUser();
        } else {
            //startMainActivity();
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
