package me.otmane.ntic.models;

import com.google.gson.annotations.SerializedName;

public class Teacher {
    @SerializedName("id")
    private String id;
    @SerializedName("full_name")
    private String fullName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
