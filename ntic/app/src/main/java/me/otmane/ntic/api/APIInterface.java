package me.otmane.ntic.api;

import me.otmane.ntic.BuildConfig;
import me.otmane.ntic.dtos.AuthDTOs;
import me.otmane.ntic.dtos.BranchesDTOs;
import me.otmane.ntic.dtos.DemandsDTOs;
import me.otmane.ntic.dtos.ExamsDTOs;
import me.otmane.ntic.dtos.SchedulesDTOs;
import me.otmane.ntic.dtos.SchoolsDTOs;
import me.otmane.ntic.dtos.UsersDTOs;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface APIInterface {
    String API_URL = BuildConfig.API_URL;

    @POST("api/auth/login")
    Call<Result<AuthDTOs.LoginResponseDTO>> login(
            @Header("Authorization") String authHeader,
            @Body AuthDTOs.LoginRequestDTO loginDTO
    );

    @POST("api/auth/refresh")
    Call<Result<AuthDTOs.RefreshTokenResponseDTO>> refreshToken(
            @Header("Authorization") String authHeader,
            @Body AuthDTOs.LoginRequestDTO loginDTO
    );

    @POST("api/auth/logout")
    Call<Result<AuthDTOs.LoginResponseDTO>> logout(
            @Header("Authorization") String authHeader,
            @Body AuthDTOs.LoginRequestDTO loginDTO
    );

    @POST("api/users/me")
    Call<Result<UsersDTOs.MeResponseDTO>> me(
            @Header("Authorization") String authHeader,
            @Body UsersDTOs.MeRequestDTO meDTO
    );

    @POST("api/schools/list")
    Call<Result<SchoolsDTOs.ListSchoolResponseDTO>> listSchools(
            @Header("Authorization") String authHeader,
            @Body SchoolsDTOs.ListSchoolRequestDTO listSchoolRequestDTO
    );

    @POST("api/demands/add")
    Call<Result<DemandsDTOs.AddDemandsResponseDTO>> addDemand(
            @Header("Authorization") String authHeader,
            @Body DemandsDTOs.AddDemandsRequestDTO addDemandsRequestDTO
    );

    @POST("api/demands/list")
    Call<Result<DemandsDTOs.ListDemandsResponseDTO>> listDemands(
            @Header("Authorization") String authHeader,
            @Body DemandsDTOs.ListDemandsRequestDTO listDemandsRequestDTO
    );

    @POST("api/branches/list")
    Call<Result<BranchesDTOs.ListBranchesResponseDTO>> listBranches(
            @Header("Authorization") String authHeader,
            @Body BranchesDTOs.ListBranchesRequestDTO listBranchesRequestDTO
    );

    @POST("api/branches/{branchId}/subjects/list")
    Call<Result<BranchesDTOs.ListBranchSubjectsResponseDTO>> listBranchSubjects(
            @Header("Authorization") String authHeader,
            @Path("branchId") String branchId,
            @Body BranchesDTOs.ListBranchSubjectsRequestDTO listBranchSubjectsRequestDTO
    );

    @POST("api/exams/list")
    Call<Result<ExamsDTOs.ListExamsResponseDTO>> listExams(
            @Header("Authorization") String authHeader,
            @Body ExamsDTOs.ListExamsRequestDTO listExamsRequestDTO
    );

    @POST("api/schedules/list")
    Call<Result<SchedulesDTOs.ListSchedulesResponseDTO>> listSchedules(
            @Header("Authorization") String authHeader,
            @Body SchedulesDTOs.ListSchedulesRequestDTO listSchedulesRequestDTO
    );
}
