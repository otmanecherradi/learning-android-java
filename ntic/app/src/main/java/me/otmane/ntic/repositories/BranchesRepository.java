package me.otmane.ntic.repositories;

import me.otmane.ntic.BuildConfig;
import me.otmane.ntic.api.APIClient;
import me.otmane.ntic.api.Result;
import me.otmane.ntic.dtos.BranchesDTOs;
import retrofit2.Call;

public class BranchesRepository {
    public static Call<Result<BranchesDTOs.ListBranchesResponseDTO>> list(BranchesDTOs.ListBranchesRequestDTO listDemandsRequestDTO) {
        return APIClient.getInstance().getAPI()
                .listBranches("Bearer " + BuildConfig.API_ANON_KEY, listDemandsRequestDTO);
    }

    public static Call<Result<BranchesDTOs.ListBranchSubjectsResponseDTO>> listSubjects(String branchId, BranchesDTOs.ListBranchSubjectsRequestDTO listBranchSubjectsRequestDTO) {
        return APIClient.getInstance().getAPI()
                .listBranchSubjects("Bearer " + BuildConfig.API_ANON_KEY, branchId, listBranchSubjectsRequestDTO);
    }
}
