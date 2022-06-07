package me.otmane.ntic.dtos;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import me.otmane.ntic.models.Exam;

public class ExamsDTOs {
    public static class ListExamsRequestDTO {
        @SerializedName("access_token")
        String accessToken;

        public String getAccessToken() {
            return accessToken;
        }

        public void setAccessToken(String accessToken) {
            this.accessToken = accessToken;
        }
    }

    public static class ListExamsResponseDTO extends ArrayList<Exam> {
    }
}
