package cat.buyaround.app.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.util.HashSet;
import java.util.Set;

import cat.buyaround.app.R;
import cat.buyaround.app.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

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
