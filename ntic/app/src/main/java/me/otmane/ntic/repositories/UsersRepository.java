package me.otmane.ntic.repositories;

import me.otmane.ntic.BuildConfig;
import me.otmane.ntic.api.APIClient;
import me.otmane.ntic.api.Result;
import me.otmane.ntic.dtos.AuthDTOs;
import me.otmane.ntic.dtos.UsersDTOs;
import retrofit2.Call;

public class UsersRepository {
    public static Call<Result<UsersDTOs.MeResponseDTO>> me(UsersDTOs.MeRequestDTO meRequestDTO) {
        return APIClient.getInstance().getAPI()
                .me("Bearer " + BuildConfig.API_ANON_KEY, meRequestDTO);
    }
}
