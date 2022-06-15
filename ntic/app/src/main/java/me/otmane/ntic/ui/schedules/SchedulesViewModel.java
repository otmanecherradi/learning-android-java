package me.otmane.ntic.ui.schedules;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Collections;
import java.util.List;

import me.otmane.ntic.DataStore;
import me.otmane.ntic.api.Result;
import me.otmane.ntic.dtos.SchedulesDTOs;
import me.otmane.ntic.models.Schedule;
import me.otmane.ntic.repositories.SchedulesRepository;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SchedulesViewModel extends ViewModel {
    public static final String TAG = "SchedulesViewModel";

    private final MutableLiveData<List<Schedule>> mSchedules = new MutableLiveData<>();

    public LiveData<List<Schedule>> getSchedules() {
        loadSchedules();
        return mSchedules;
    }

    private void loadSchedules() {
        SchedulesDTOs.ListSchedulesRequestDTO listSchedulesRequestDTO = new SchedulesDTOs.ListSchedulesRequestDTO();
        listSchedulesRequestDTO.setAccessToken(DataStore.getInstance().getAccessToken());
        SchedulesRepository.list(listSchedulesRequestDTO)
                .enqueue(new Callback<Result<SchedulesDTOs.ListSchedulesResponseDTO>>() {
                    @Override
                    public void onResponse(@NonNull Call<Result<SchedulesDTOs.ListSchedulesResponseDTO>> call,
                                           @NonNull Response<Result<SchedulesDTOs.ListSchedulesResponseDTO>> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            mSchedules.setValue(response.body().getData());
                        } else {
                            mSchedules.setValue(Collections.emptyList());
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<Result<SchedulesDTOs.ListSchedulesResponseDTO>> call,
                                          @NonNull Throwable t) {
                        mSchedules.setValue(Collections.emptyList());
                    }
                });
    }
}