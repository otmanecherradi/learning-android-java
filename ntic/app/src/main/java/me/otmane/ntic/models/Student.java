package me.otmane.ntic.models;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Student {
    @SerializedName("id")
    private String id;
    @SerializedName("full_name")
    private String fullName;
    @SerializedName("birthday")
    private Date birthday;
    @SerializedName("cin")
    private String CIN;
    @SerializedName("cne")
    private String CNE;
    @SerializedName("phone")
    private String phone;
    @SerializedName("bac_mark")
    private double bacMark;
    @SerializedName("bac_year")
    private int bacYear;
    @SerializedName("bac_specialty")
    private String bacSpecialty;
    @SerializedName("avatar_url")
    private String avatarURL;

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

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getCIN() {
        return CIN;
    }

    public void setCIN(String CIN) {
        this.CIN = CIN;
    }

    public String getCNE() {
        return CNE;
    }

    public void setCNE(String CNE) {
        this.CNE = CNE;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public double getBacMark() {
        return bacMark;
    }

    public void setBacMark(double bacMark) {
        this.bacMark = bacMark;
    }

    public int getBacYear() {
        return bacYear;
    }

    public void setBacYear(int bacYear) {
        this.bacYear = bacYear;
    }

    public String getBacSpecialty() {
        return bacSpecialty;
    }

    public void setBacSpecialty(String bacSpecialty) {
        this.bacSpecialty = bacSpecialty;
    }

    public String getAvatarURL() {
        return avatarURL;
    }

    public void setAvatarURL(String avatarURL) {
        this.avatarURL = avatarURL;
    }
}
