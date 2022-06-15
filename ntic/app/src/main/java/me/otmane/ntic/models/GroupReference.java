package me.otmane.ntic.models;

import com.google.gson.annotations.SerializedName;

public class GroupReference {
    @SerializedName("id")
    private String id;
    @SerializedName("group")
    private Group group;
    @SerializedName("subject")
    private Subject subject;
    @SerializedName("teacher")
    private Teacher teacher;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}
