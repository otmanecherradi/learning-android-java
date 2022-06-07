package me.otmane.ntic.repositories;

import me.otmane.ntic.BuildConfig;
import me.otmane.ntic.api.APIClient;
import me.otmane.ntic.api.Result;
import me.otmane.ntic.dtos.DemandsDTOs;
import me.otmane.ntic.dtos.SchedulesDTOs;
import retrofit2.Call;

public class SchedulesRepository {
    public static Call<Result<SchedulesDTOs.ListSchedulesResponseDTO>> list(SchedulesDTOs.ListSchedulesRequestDTO listSchedulesRequestDTO) {
        return APIClient.getInstance().getAPI()
                .listSchedules("Bearer " + BuildConfig.API_ANON_KEY, listSchedulesRequestDTO);
    }
}
