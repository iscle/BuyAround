package com.selepdf.buyaround.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.selepdf.buyaround.R;
import com.selepdf.buyaround.auth.TokenManager;
import com.selepdf.buyaround.auth.UserManager;
import com.selepdf.buyaround.databinding.ActivityMainBinding;
import com.selepdf.buyaround.databinding.DrawerHeaderBinding;
import com.selepdf.buyaround.fragment.HomeFragmentDirections;

import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

import static android.view.View.GONE;

public class MainActivity extends DaggerAppCompatActivity {
    private ActivityMainBinding binding;
    private DrawerHeaderBinding drawerHeaderBinding;
    @Inject
    protected UserManager userManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        DrawerLayout drawerLayout = (DrawerLayout) binding.getRoot();
        setContentView(drawerLayout);

        // Get the NavController
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);

        // Set up bottom navigation
        NavigationUI.setupWithNavController(binding.bottomNavigationView, navController);

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.selepdf.buyaround.action.USER_UPDATED");
        LocalBroadcastManager.getInstance(this).registerReceiver(receiver, intentFilter);

        userManager.setUser();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver);
    }

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("com.selepdf.buyaround.action.USER_UPDATED")) {
                if (drawerHeaderBinding != null) {
                    if (TokenManager.isTokenValid(userManager.getTokenManager().getToken())) {
                        drawerHeaderBinding.button.setVisibility(GONE);
                        drawerHeaderBinding.textView.setText("Hello " + userManager.getUser().getEmail());
                    }
                }
            }
        }
    };

    @Override
    public void onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
