package me.otmane.ntic.models;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Demand {
    @SerializedName("id")
    private String id;
    @SerializedName("type")
    private String type;
    @SerializedName("state")
    private String state;
    @SerializedName("for_date")
    private Date forDate;
    @SerializedName("note")
    private String note;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Date getForDate() {
        return forDate;
    }

    public void setForDate(Date forDate) {
        this.forDate = forDate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
