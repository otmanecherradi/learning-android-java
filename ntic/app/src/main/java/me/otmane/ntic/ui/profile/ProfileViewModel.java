package me.otmane.ntic.ui.profile;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import me.otmane.ntic.DataStore;
import me.otmane.ntic.api.Result;
import me.otmane.ntic.dtos.GroupsDTOs;
import me.otmane.ntic.dtos.UsersDTOs;
import me.otmane.ntic.repositories.GroupsRepository;
import me.otmane.ntic.repositories.UsersRepository;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileViewModel extends ViewModel {
    private MutableLiveData<UsersDTOs.MeResponseDTO> mUser;
    private MutableLiveData<GroupsDTOs.GroupStudentsResponseDTO> mColleagues;

    public LiveData<UsersDTOs.MeResponseDTO> getUser() {
        if (mUser == null) {
            mUser = new MutableLiveData<>();
            loadUser();
        }
        return mUser;
    }

    public LiveData<GroupsDTOs.GroupStudentsResponseDTO> getColleagues() {
        if (mColleagues == null) {
            mColleagues = new MutableLiveData<>();
            loadColleagues();
        }
        return mColleagues;
    }

    private void loadUser() {
        UsersDTOs.MeRequestDTO meRequestDTO = new UsersDTOs.MeRequestDTO();
        meRequestDTO.setAccessToken(DataStore.getInstance().getAccessToken());
        UsersRepository.me(meRequestDTO).enqueue(new Callback<Result<UsersDTOs.MeResponseDTO>>() {
            @Override
            public void onResponse(@NonNull Call<Result<UsersDTOs.MeResponseDTO>> call,
                                   @NonNull Response<Result<UsersDTOs.MeResponseDTO>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    mUser.setValue(response.body().getData());
                } else {
                    mUser.setValue(null);
                }
            }

            @Override
            public void onFailure(@NonNull Call<Result<UsersDTOs.MeResponseDTO>> call, @NonNull Throwable t) {
                mUser.setValue(null);
            }
        });

    }

    private void loadColleagues() {
        GroupsDTOs.GroupStudentsRequestDTO groupStudentsRequestDTO = new GroupsDTOs.GroupStudentsRequestDTO();
        groupStudentsRequestDTO.setAccessToken(DataStore.getInstance().getAccessToken());
        GroupsRepository.listGroupStudents(groupStudentsRequestDTO).enqueue(new Callback<Result<GroupsDTOs.GroupStudentsResponseDTO>>() {
            @Override
            public void onResponse(@NonNull Call<Result<GroupsDTOs.GroupStudentsResponseDTO>> call,
                                   @NonNull Response<Result<GroupsDTOs.GroupStudentsResponseDTO>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    mColleagues.setValue(response.body().getData());
                } else {
                    mColleagues.setValue(null);
                }
            }

            @Override
            public void onFailure(@NonNull Call<Result<GroupsDTOs.GroupStudentsResponseDTO>> call, @NonNull Throwable t) {
                mColleagues.setValue(null);
            }
        });
    }
}