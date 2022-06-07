package me.otmane.ntic.dtos;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import me.otmane.ntic.models.Branch;
import me.otmane.ntic.models.BranchSubject;
import me.otmane.ntic.models.Demand;

public class BranchesDTOs {
    public static class ListBranchesRequestDTO {
        @SerializedName("access_token")
        String accessToken;

        public String getAccessToken() {
            return accessToken;
        }

        public void setAccessToken(String accessToken) {
            this.accessToken = accessToken;
        }
    }

    public static class ListBranchesResponseDTO extends ArrayList<Branch> {
    }

    public static class ListBranchSubjectsRequestDTO {
        @SerializedName("access_token")
        String accessToken;

        public String getAccessToken() {
            return accessToken;
        }

        public void setAccessToken(String accessToken) {
            this.accessToken = accessToken;
        }
    }

    public static class ListBranchSubjectsResponseDTO extends ArrayList<BranchSubject> {
    }
}
