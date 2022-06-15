package me.otmane.ntic.ui.exams;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Collections;
import java.util.List;

import me.otmane.ntic.DataStore;
import me.otmane.ntic.api.Result;
import me.otmane.ntic.dtos.ExamsDTOs;
import me.otmane.ntic.models.Exam;
import me.otmane.ntic.repositories.ExamsRepository;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExamsViewModel extends ViewModel {
    private MutableLiveData<List<Exam>> mExams;

    public LiveData<List<Exam>> getExams() {
        if (mExams == null) {
            mExams = new MutableLiveData<>();
            loadExams();
        }
        return mExams;
    }

    private void loadExams() {
        ExamsDTOs.ListExamsRequestDTO listExamsRequestDTO = new ExamsDTOs.ListExamsRequestDTO();
        listExamsRequestDTO.setAccessToken(DataStore.getInstance().getAccessToken());
        ExamsRepository.list(listExamsRequestDTO)
                .enqueue(new Callback<Result<ExamsDTOs.ListExamsResponseDTO>>() {
                    @Override
                    public void onResponse(@NonNull Call<Result<ExamsDTOs.ListExamsResponseDTO>> call,
                                           @NonNull Response<Result<ExamsDTOs.ListExamsResponseDTO>> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            mExams.setValue(response.body().getData());
                        } else {
                            mExams.setValue(Collections.emptyList());
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<Result<ExamsDTOs.ListExamsResponseDTO>> call,
                                          @NonNull Throwable t) {
                        mExams.setValue(Collections.emptyList());
                    }
                });
    }
}