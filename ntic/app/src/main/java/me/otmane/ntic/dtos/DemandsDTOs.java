package me.otmane.ntic.dtos;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import me.otmane.ntic.models.Demand;

public class DemandsDTOs {
    public static class AddDemandsRequestDTO {
        @SerializedName("type")
        String type;
        @SerializedName("for_date")
        String forDate;
        @SerializedName("note")
        String note;
        @SerializedName("access_token")
        String accessToken;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getForDate() {
            return forDate;
        }

        public void setForDate(String forDate) {
            this.forDate = forDate;
        }

        public String getNote() {
            return note;
        }

        public void setNote(String note) {
            this.note = note;
        }

        public String getAccessToken() {
            return accessToken;
        }

        public void setAccessToken(String accessToken) {
            this.accessToken = accessToken;
        }
    }

    public static class AddDemandsResponseDTO {
    }

    public static class ListDemandsResponseDTO extends ArrayList<Demand> {
    }

    public static class ListDemandsRequestDTO {
        @SerializedName("access_token")
        String accessToken;

        public String getAccessToken() {
            return accessToken;
        }

        public void setAccessToken(String accessToken) {
            this.accessToken = accessToken;
        }
    }
}
