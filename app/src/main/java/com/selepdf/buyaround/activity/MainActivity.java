package com.selepdf.buyaround.activity;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.selepdf.buyaround.R;
import com.selepdf.buyaround.databinding.ActivityMainBinding;
import com.selepdf.buyaround.databinding.DrawerHeaderBinding;
import com.selepdf.buyaround.fragment.HomeFragmentDirections;

import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
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

    private void setupDrawerHeader() {
        View headerView = binding.navView.getHeaderView(0);
        if (headerView != null) {
            DrawerHeaderBinding drawerHeaderBinding = DrawerHeaderBinding.bind(headerView);

        }
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
