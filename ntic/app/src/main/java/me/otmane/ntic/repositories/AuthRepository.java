package me.otmane.ntic.repositories;

import me.otmane.ntic.BuildConfig;
import me.otmane.ntic.api.APIClient;
import me.otmane.ntic.api.Result;
import me.otmane.ntic.dtos.AuthDTOs;
import retrofit2.Call;

public class AuthRepository {
    public static Call<Result<AuthDTOs.LoginResponseDTO>> login(AuthDTOs.LoginRequestDTO loginRequestDTO) {
        return APIClient.getInstance().getAPI()
                .login("Bearer " + BuildConfig.API_ANON_KEY, loginRequestDTO);
    }

    public static Call<Result<AuthDTOs.RefreshTokenResponseDTO>> refreshToken(AuthDTOs.RefreshTokenRequestDTO refreshTokenRequestDTO) {
        return APIClient.getInstance().getAPI()
                .refreshToken("Bearer " + BuildConfig.API_ANON_KEY, refreshTokenRequestDTO);
    }

    public static Call<Result<AuthDTOs.LogoutResponseDTO>> logout(AuthDTOs.LogoutRequestDTO logoutRequestDTO) {
        return APIClient.getInstance().getAPI()
                .logout("Bearer " + BuildConfig.API_ANON_KEY, logoutRequestDTO);
    }
}
