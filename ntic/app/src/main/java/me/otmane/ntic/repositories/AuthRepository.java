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
}
