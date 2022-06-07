package me.otmane.ntic.dtos;

import com.google.gson.annotations.SerializedName;

import me.otmane.ntic.models.Student;
import me.otmane.ntic.models.User;

public class UsersDTOs {
    public static class MeRequestDTO {
        @SerializedName("access_token")
        String accessToken;

        public String getAccessToken() {
            return accessToken;
        }

        public void setAccessToken(String accessToken) {
            this.accessToken = accessToken;
        }
    }

    public static class MeResponseDTO extends User {
        @SerializedName("student")
        Student student;

        public Student getStudent() {
            return student;
        }

        public void setStudent(Student student) {
            this.student = student;
        }
    }


}
