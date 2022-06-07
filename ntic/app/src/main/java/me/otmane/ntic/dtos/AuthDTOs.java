package me.otmane.ntic.dtos;

import com.google.gson.annotations.SerializedName;

import me.otmane.ntic.models.User;

public class AuthDTOs {
    public static class LoginRequestDTO {
        @SerializedName("email")
        private String email;
        @SerializedName("password")
        private String password;
//        @SerializedName("fcm_token")
//        private String fcmToken;

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

//        public String getFcmToken() {
//            return fcmToken;
//        }
//
//        public void setFcmToken(String fcmToken) {
//            this.fcmToken = fcmToken;
//        }
    }

    public static class LoginResponseDTO {
        @SerializedName("access_token")
        String accessToken;
        @SerializedName("refresh_token")
        String refreshToken;
        @SerializedName("user")
        User user;

        public String getAccessToken() {
            return accessToken;
        }

        public void setAccessToken(String accessToken) {
            this.accessToken = accessToken;
        }

        public String getRefreshToken() {
            return refreshToken;
        }

        public void setRefreshToken(String refreshToken) {
            this.refreshToken = refreshToken;
        }

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }
    }

    public static class RefreshTokenResponseDTO {
        @SerializedName("access_token")
        String accessToken;
    }

}
