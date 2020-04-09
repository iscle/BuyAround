package com.selepdf.hackovid.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.selepdf.hackovid.R;
import com.selepdf.hackovid.databinding.ActivityMainBinding;
import com.selepdf.hackovid.fragment.HomeFragmentDirections;

import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        DrawerLayout drawerLayout = (DrawerLayout) binding.getRoot();
        setContentView(drawerLayout);

        // Get the NavController
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);

        // Set up the toolbar
        Set<Integer> tlds = new HashSet<>();
        tlds.add(R.id.homeFragment);
        tlds.add(R.id.ordersFragment);
        tlds.add(R.id.notificationsFragment);
        tlds.add(R.id.favouritesFragment);
        tlds.add(R.id.categoriesFragment);
        tlds.add(R.id.accountFragment);
        tlds.add(R.id.jobsFragment);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(tlds)
                .setDrawerLayout(drawerLayout)
                .build();
        NavigationUI.setupWithNavController(binding.toolbar, navController, appBarConfiguration);
        // Set up the drawer
        NavigationUI.setupWithNavController(binding.navView, navController);
        // Set up bottom navigation
        NavigationUI.setupWithNavController(binding.bottomNavigationView, navController);

        binding.navView.getMenu().findItem(R.id.logout).setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.logout) {
                NavDirections action = HomeFragmentDirections.actionHomeFragmentToLoginFragment();
                Navigation.findNavController(MainActivity.this, R.id.nav_host_fragment).navigate(action);
                return true;
            }

            return false;
        });
    }

    @Override
    public void onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
