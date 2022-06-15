package me.otmane.ntic.repositories;

import me.otmane.ntic.BuildConfig;
import me.otmane.ntic.api.APIClient;
import me.otmane.ntic.api.Result;
import me.otmane.ntic.dtos.SchoolsDTOs;
import retrofit2.Call;

public class SchoolsRepository {
    public static Call<Result<SchoolsDTOs.ListSchoolResponseDTO>> list(SchoolsDTOs.ListSchoolRequestDTO listSchoolRequestDTO) {
        return APIClient.getInstance().getAPI()
                .listSchools("Bearer " + BuildConfig.API_ANON_KEY, listSchoolRequestDTO);
    }
}
