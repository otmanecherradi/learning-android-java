package me.otmane.ntic.ui.home;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Collections;
import java.util.List;

import me.otmane.ntic.DataStore;
import me.otmane.ntic.api.Result;
import me.otmane.ntic.dtos.BranchesDTOs;
import me.otmane.ntic.models.Branch;
import me.otmane.ntic.models.BranchSubject;
import me.otmane.ntic.repositories.BranchesRepository;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeViewModel extends ViewModel {
    private MutableLiveData<List<Branch>> mBranches;

    private MutableLiveData<List<BranchSubject>> mBranchSubjects;

    public LiveData<List<Branch>> getBranches() {
        if (mBranches == null) {
            mBranches = new MutableLiveData<>();
            loadBranches();
        }
        return mBranches;
    }

    public LiveData<List<BranchSubject>> getBranchSubjects(String branchId) {
        if (mBranchSubjects == null) {
            mBranchSubjects = new MutableLiveData<>();
            loadBranchSubjects(branchId);
        }
        return mBranchSubjects;
    }

    private void loadBranches() {
        BranchesDTOs.ListBranchesRequestDTO listBranchesRequestDTO = new BranchesDTOs.ListBranchesRequestDTO();
        listBranchesRequestDTO.setAccessToken(DataStore.getInstance().getAccessToken());
        BranchesRepository.list(listBranchesRequestDTO)
                .enqueue(new Callback<Result<BranchesDTOs.ListBranchesResponseDTO>>() {
                    @Override
                    public void onResponse(@NonNull Call<Result<BranchesDTOs.ListBranchesResponseDTO>> call,
                                           @NonNull Response<Result<BranchesDTOs.ListBranchesResponseDTO>> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            mBranches.setValue(response.body().getData());
                        } else {
                            mBranches.setValue(Collections.emptyList());
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<Result<BranchesDTOs.ListBranchesResponseDTO>> call,
                                          @NonNull Throwable t) {
                        mBranches.setValue(Collections.emptyList());
                    }
                });
    }

    private void loadBranchSubjects(String branchId) {
        BranchesDTOs.ListBranchSubjectsRequestDTO listBranchSubjectsRequestDTO = new BranchesDTOs.ListBranchSubjectsRequestDTO();
        listBranchSubjectsRequestDTO.setAccessToken(DataStore.getInstance().getAccessToken());
        BranchesRepository.listSubjects(branchId, listBranchSubjectsRequestDTO)
                .enqueue(new Callback<Result<BranchesDTOs.ListBranchSubjectsResponseDTO>>() {
                    @Override
                    public void onResponse(@NonNull Call<Result<BranchesDTOs.ListBranchSubjectsResponseDTO>> call,
                                           @NonNull Response<Result<BranchesDTOs.ListBranchSubjectsResponseDTO>> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            mBranchSubjects.setValue(response.body().getData());
                        } else {
                            mBranchSubjects.setValue(Collections.emptyList());
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<Result<BranchesDTOs.ListBranchSubjectsResponseDTO>> call, @NonNull Throwable t) {
                        mBranchSubjects.setValue(Collections.emptyList());
                    }
                });
    }
}