package com.dev.schoolmanagement.ui.group;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.dev.schoolmanagement.R;
import com.dev.schoolmanagement.databinding.ActivityMainGroupBinding;

public class MainGroupActivity extends AppCompatActivity {
    public static final String TAG = "MainGroupActivity";

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainGroupBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainGroupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_group_content);
        assert navHostFragment != null;
        NavController navController = navHostFragment.getNavController();

        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_group_content);
        return NavigationUI.navigateUp(navController, appBarConfiguration) || super.onSupportNavigateUp();
    }
}