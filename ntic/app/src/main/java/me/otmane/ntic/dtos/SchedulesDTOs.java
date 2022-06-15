package me.otmane.ntic.dtos;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import me.otmane.ntic.models.Schedule;

public class SchedulesDTOs {
    public static class ListSchedulesRequestDTO {
        @SerializedName("access_token")
        String accessToken;

        public String getAccessToken() {
            return accessToken;
        }

        public void setAccessToken(String accessToken) {
            this.accessToken = accessToken;
        }
    }

    public static class ListSchedulesResponseDTO extends ArrayList<Schedule> {
    }
}
