package me.otmane.ntic.ui.profile;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import me.otmane.ntic.DataStore;
import me.otmane.ntic.api.Result;
import me.otmane.ntic.dtos.UsersDTOs;
import me.otmane.ntic.models.User;
import me.otmane.ntic.repositories.UsersRepository;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileViewModel extends ViewModel {
    private MutableLiveData<UsersDTOs.MeResponseDTO> mUser;

    public LiveData<UsersDTOs.MeResponseDTO> getUser() {
        if (mUser == null) {
            mUser = new MutableLiveData<>();
            loadUser();
        }
        return mUser;
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
}