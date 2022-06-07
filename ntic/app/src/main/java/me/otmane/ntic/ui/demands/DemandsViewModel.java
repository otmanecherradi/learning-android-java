package me.otmane.ntic.ui.demands;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Collections;
import java.util.List;

import me.otmane.ntic.DataStore;
import me.otmane.ntic.api.Result;
import me.otmane.ntic.dtos.DemandsDTOs;
import me.otmane.ntic.models.Demand;
import me.otmane.ntic.repositories.DemandsRepository;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DemandsViewModel extends ViewModel {
    public static final String TAG = "DemandsViewModel";

    private final MutableLiveData<List<Demand>> mDemands = new MutableLiveData<>();

    public LiveData<List<Demand>> getDemands() {
        loadDemands();
        return mDemands;
    }

    private void loadDemands() {
        DemandsDTOs.ListDemandsRequestDTO listDemandsRequestDTO = new DemandsDTOs.ListDemandsRequestDTO();
        listDemandsRequestDTO.setAccessToken(DataStore.getInstance().getAccessToken());
        DemandsRepository.list(listDemandsRequestDTO)
                .enqueue(new Callback<Result<DemandsDTOs.ListDemandsResponseDTO>>() {
                    @Override
                    public void onResponse(@NonNull Call<Result<DemandsDTOs.ListDemandsResponseDTO>> call,
                                           @NonNull Response<Result<DemandsDTOs.ListDemandsResponseDTO>> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            mDemands.setValue(response.body().getData());
                        } else {
                            mDemands.setValue(Collections.emptyList());
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<Result<DemandsDTOs.ListDemandsResponseDTO>> call,
                                          @NonNull Throwable t) {
                        mDemands.setValue(Collections.emptyList());
                    }
                });
    }
}