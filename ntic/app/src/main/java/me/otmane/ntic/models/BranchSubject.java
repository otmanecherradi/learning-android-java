package me.otmane.ntic.models;

import com.google.gson.annotations.SerializedName;

public class BranchSubject {
    @SerializedName("mass")
    private int mass;
    @SerializedName("subject")
    private Subject subject;

    public int getMass() {
        return mass;
    }

    public void setMass(int mass) {
        this.mass = mass;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }
}
