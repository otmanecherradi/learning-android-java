package me.otmane.ntic.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import me.otmane.ntic.DataStore;
import me.otmane.ntic.R;
import me.otmane.ntic.api.Result;
import me.otmane.ntic.databinding.ActivityMainBinding;
import me.otmane.ntic.dtos.AuthDTOs;
import me.otmane.ntic.repositories.AuthRepository;
import me.otmane.ntic.ui.login.LoginActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private AppBarConfiguration appBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.materialToolbar);

        appBarConfiguration = new AppBarConfiguration
                .Builder(R.id.navigationHome, R.id.navigationSchedule, R.id.navigationExams, R.id.navigationDemands, R.id.navigationProfile)
                .build();

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment_activity_main);
        assert navHostFragment != null;
        NavController navController = navHostFragment.getNavController();

        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

        AuthDTOs.RefreshTokenRequestDTO refreshTokenRequestDTO = new AuthDTOs.RefreshTokenRequestDTO();
        refreshTokenRequestDTO.setRefreshToken(DataStore.getInstance().getRefreshToken());
        AuthRepository.refreshToken(refreshTokenRequestDTO).enqueue(new Callback<Result<AuthDTOs.RefreshTokenResponseDTO>>() {
            @Override
            public void onResponse(@NonNull Call<Result<AuthDTOs.RefreshTokenResponseDTO>> call,
                                   @NonNull Response<Result<AuthDTOs.RefreshTokenResponseDTO>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    DataStore.getInstance().setAccessToken(response.body().getData().getAccessToken());
                    DataStore.getInstance().setRefreshToken(response.body().getData().getRefreshToken());

                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                    finish();
                }
            }

            @Override
            public void onFailure(@NonNull Call<Result<AuthDTOs.RefreshTokenResponseDTO>> call,
                                  @NonNull Throwable t) {
                DataStore.getInstance().setAccessToken(null);
                DataStore.getInstance().setRefreshToken(null);

                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                finish();
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration) || super.onSupportNavigateUp();
    }
}