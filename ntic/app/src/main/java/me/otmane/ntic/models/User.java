package me.otmane.ntic.models;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class User {
    @SerializedName("id")
    private String id;
    @SerializedName("email")
    private String email;
    @SerializedName("last_sign_in_at")
    private Date lastSignInAt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getLastSignInAt() {
        return lastSignInAt;
    }

    public void setLastSignInAt(Date lastSignInAt) {
        this.lastSignInAt = lastSignInAt;
    }

}
