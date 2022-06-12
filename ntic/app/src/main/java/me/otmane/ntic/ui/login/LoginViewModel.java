package me.otmane.ntic.ui.login;

import android.util.Patterns;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import me.otmane.ntic.DataStore;
import me.otmane.ntic.api.Result;
import me.otmane.ntic.dtos.AuthDTOs;
import me.otmane.ntic.repositories.AuthRepository;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends ViewModel {
    public static final String TAG = "LoginViewModel";

    private final MutableLiveData<String> loginFormState = new MutableLiveData<>();
    private final MutableLiveData<Result<AuthDTOs.LoginResponseDTO>> loginResponse = new MutableLiveData<>();

    LiveData<String> getLoginFormState() {
        return loginFormState;
    }

    LiveData<Result<AuthDTOs.LoginResponseDTO>> getLoginResponse() {
        return loginResponse;
    }

    public void login(String email, String password) {
        if (!isEmailValid(email)) {
            loginFormState.setValue("email");
            return;
        }
        if (!isPasswordValid(password)) {
            loginFormState.setValue("password");
            return;
        }

        loginFormState.setValue(null);

        AuthDTOs.LoginRequestDTO loginRequestDTO = new AuthDTOs.LoginRequestDTO();
        loginRequestDTO.setEmail(email);
        loginRequestDTO.setPassword(password);
        loginRequestDTO.setFcmToken(DataStore.getInstance().getFCMToken());

        AuthRepository.login(loginRequestDTO)
                .enqueue(new Callback<Result<AuthDTOs.LoginResponseDTO>>() {
                    @Override
                    public void onResponse(@NonNull Call<Result<AuthDTOs.LoginResponseDTO>> call,
                                           @NonNull Response<Result<AuthDTOs.LoginResponseDTO>> response) {
                        if (response.isSuccessful()) {
                            loginResponse.setValue(response.body());
                        } else {
                            loginResponse.setValue(null);
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<Result<AuthDTOs.LoginResponseDTO>> call, @NonNull Throwable t) {
                        loginResponse.setValue(null);
                    }
                });
    }

    private boolean isEmailValid(String email) {
        if (email == null) {
            return false;
        }

        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean isPasswordValid(String password) {
        if (password == null) {
            return false;
        }

        return password.length() > 3;
    }

}