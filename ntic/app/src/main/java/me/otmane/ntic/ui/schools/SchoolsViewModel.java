package me.otmane.ntic.ui.schools;


import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Collections;
import java.util.List;

import me.otmane.ntic.api.Result;
import me.otmane.ntic.dtos.SchoolsDTOs;
import me.otmane.ntic.models.School;
import me.otmane.ntic.repositories.SchoolsRepository;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SchoolsViewModel extends ViewModel {
    private MutableLiveData<List<School>> mSchools;

    public LiveData<List<School>> getSchools() {
        if (mSchools == null) {
            mSchools = new MutableLiveData<>();
            loadSchools();
        }
        return mSchools;
    }

    private void loadSchools() {
        SchoolsRepository.list(new SchoolsDTOs.ListSchoolRequestDTO())
                .enqueue(new Callback<Result<SchoolsDTOs.ListSchoolResponseDTO>>() {
                    @Override
                    public void onResponse(@NonNull Call<Result<SchoolsDTOs.ListSchoolResponseDTO>> call,
                                           @NonNull Response<Result<SchoolsDTOs.ListSchoolResponseDTO>> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            mSchools.setValue(response.body().getData());
                        } else {
                            mSchools.setValue(Collections.emptyList());
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<Result<SchoolsDTOs.ListSchoolResponseDTO>> call,
                                          @NonNull Throwable t) {
                        mSchools.setValue(Collections.emptyList());
                    }
                });
    }
}
