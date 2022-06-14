package me.otmane.ntic.repositories;

import me.otmane.ntic.BuildConfig;
import me.otmane.ntic.api.APIClient;
import me.otmane.ntic.api.Result;
import me.otmane.ntic.dtos.AuthDTOs;
import me.otmane.ntic.dtos.GroupsDTOs;
import retrofit2.Call;

public class GroupsRepository {
    public static Call<Result<GroupsDTOs.GroupStudentsResponseDTO>> listGroupStudents(GroupsDTOs.GroupStudentsRequestDTO groupStudentsRequestDTO) {
        return APIClient.getInstance().getAPI()
                .listGroupStudents("Bearer " + BuildConfig.API_ANON_KEY, groupStudentsRequestDTO);
    }
}
