package me.otmane.b3.project.models;


import java.io.Serializable;

public class Quiz implements Serializable {
    private final String id;

    private final String title;

    public Quiz(String id, String title) {
        this.id = id;
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
}
