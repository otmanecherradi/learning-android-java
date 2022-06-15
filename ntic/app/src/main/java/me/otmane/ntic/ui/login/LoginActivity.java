package me.otmane.ntic.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

import me.otmane.ntic.DataStore;
import me.otmane.ntic.api.Result;
import me.otmane.ntic.databinding.ActivityLoginBinding;
import me.otmane.ntic.dtos.AuthDTOs;
import me.otmane.ntic.ui.MainActivity;

public class LoginActivity extends AppCompatActivity {
    public static final String TAG = "LoginActivity";

    private ActivityLoginBinding binding;
    private LoginViewModel loginViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        final TextInputLayout emailTextInput = binding.email;
        final TextInputLayout passwordTextInput = binding.password;
        final Button loginButton = binding.login;
        final ProgressBar loadingProgressBar = binding.loading;

        loginViewModel.getLoginFormState().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if (s == null) {
                    emailTextInput.setError(null);
                    passwordTextInput.setError(null);

                    return;
                }

                if (s.equals("email"))
                    emailTextInput.setError("Email not valid");

                if (s.equals("password"))
                    passwordTextInput.setError("Password not valid");

                loadingProgressBar.setVisibility(View.GONE);
                loginButton.setVisibility(View.VISIBLE);
            }
        });

        loginViewModel.getLoginResponse().observe(this, new Observer<Result<AuthDTOs.LoginResponseDTO>>() {
            @Override
            public void onChanged(Result<AuthDTOs.LoginResponseDTO> loginResponse) {
                loadingProgressBar.setVisibility(View.GONE);
                loginButton.setVisibility(View.VISIBLE);

                if (loginResponse == null) {
                    showLoginFailed("Server error");
                    return;
                }

                if (loginResponse.getStatus().equals("ERROR"))
                    showLoginFailed("Invalid credentials");

                if (loginResponse.getStatus().equals("SUCCESS"))
                    performLogin(loginResponse.getData());
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingProgressBar.setVisibility(View.VISIBLE);
                loginButton.setVisibility(View.GONE);

                loginViewModel.login(
                        Objects.requireNonNull(emailTextInput.getEditText()).getText().toString(),
                        Objects.requireNonNull(passwordTextInput.getEditText()).getText().toString());
            }
        });
    }

    private void showLoginFailed(String error) {
        Toast.makeText(getApplicationContext(), error, Toast.LENGTH_LONG)
                .show();
    }

    private void performLogin(AuthDTOs.LoginResponseDTO loginResponse) {
        DataStore.getInstance().setAccessToken(loginResponse.getAccessToken());
        DataStore.getInstance().setRefreshToken(loginResponse.getRefreshToken());

        startActivity(new Intent(LoginActivity.this, MainActivity.class));
        finish();
    }
}