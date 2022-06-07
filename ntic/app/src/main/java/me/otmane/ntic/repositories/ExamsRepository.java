package me.otmane.ntic.repositories;

import me.otmane.ntic.BuildConfig;
import me.otmane.ntic.api.APIClient;
import me.otmane.ntic.api.Result;
import me.otmane.ntic.dtos.ExamsDTOs;
import retrofit2.Call;

public class ExamsRepository {
    public static Call<Result<ExamsDTOs.ListExamsResponseDTO>> list(ExamsDTOs.ListExamsRequestDTO listExamsRequestDTO) {
        return APIClient.getInstance().getAPI()
                .listExams("Bearer " + BuildConfig.API_ANON_KEY, listExamsRequestDTO);
    }
}
