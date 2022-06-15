package me.otmane.ntic.dtos;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import me.otmane.ntic.models.Subject;

public class SubjectsDTOs {
    public static class ListSubjectsRequestDTO {
        @SerializedName("access_token")
        String accessToken;

        public String getAccessToken() {
            return accessToken;
        }

        public void setAccessToken(String accessToken) {
            this.accessToken = accessToken;
        }
    }

    public static class ListSubjectsResponseDTO extends ArrayList<Subject> {
    }
}
