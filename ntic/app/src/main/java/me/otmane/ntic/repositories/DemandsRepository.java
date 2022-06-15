package me.otmane.ntic.repositories;

import me.otmane.ntic.BuildConfig;
import me.otmane.ntic.api.APIClient;
import me.otmane.ntic.api.Result;
import me.otmane.ntic.dtos.DemandsDTOs;
import retrofit2.Call;

public class DemandsRepository {
    public static Call<Result<DemandsDTOs.AddDemandsResponseDTO>> addDemand(DemandsDTOs.AddDemandsRequestDTO addDemandsRequestDTO) {
        return APIClient.getInstance().getAPI()
                .addDemand("Bearer " + BuildConfig.API_ANON_KEY, addDemandsRequestDTO);
    }

    public static Call<Result<DemandsDTOs.ListDemandsResponseDTO>> list(DemandsDTOs.ListDemandsRequestDTO listDemandsRequestDTO) {
        return APIClient.getInstance().getAPI()
                .listDemands("Bearer " + BuildConfig.API_ANON_KEY, listDemandsRequestDTO);
    }
}
