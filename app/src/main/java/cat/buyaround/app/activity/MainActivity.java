package cat.buyaround.app.activity;

import android.os.Bundle;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

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
        NavigationUI.setupWithNavController(binding.toolbar, navController);

        // Set up bottom navigation
        NavigationUI.setupWithNavController(binding.contentMain.bottomNavigationView, navController);
    }
}
