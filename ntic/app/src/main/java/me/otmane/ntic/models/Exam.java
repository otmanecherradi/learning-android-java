package me.otmane.ntic.models;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Exam {
    @SerializedName("id")
    private String id;
    @SerializedName("type")
    private String type;
    @SerializedName("date")
    private Date date;
    @SerializedName("group_reference")
    private GroupReference groupReference;

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public GroupReference getGroupReference() {
        return groupReference;
    }

    public void setGroupReference(GroupReference groupReference) {
        this.groupReference = groupReference;
    }
}
