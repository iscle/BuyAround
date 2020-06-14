package cat.buyaround.app.activity;

import android.os.Bundle;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;

import cat.buyaround.app.R;
import cat.buyaround.app.auth.UserManager;
import cat.buyaround.app.databinding.ActivityMainBinding;
import dagger.android.support.DaggerAppCompatActivity;

public class MainActivity extends DaggerAppCompatActivity {
    private ActivityMainBinding binding;

    @Inject
    protected UserManager userManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Get the NavController
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);

        // Set up toolbar
        Set<Integer> tlds = new HashSet<>();
        tlds.add(R.id.homeFragment);
        tlds.add(R.id.categoriesFragment);
        tlds.add(R.id.favouritesFragment);
        tlds.add(R.id.accountFragment);
        NavigationUI.setupWithNavController(binding.toolbar, navController,
                new AppBarConfiguration.Builder(tlds).build());

        // Set up bottom navigation
        NavigationUI.setupWithNavController(binding.bottomNavigationView, navController);
    }
}
