package me.otmane.b3.project.models;

import java.util.HashMap;

public class Option {
    private final String title;
    private final boolean isCorrect;

    public Option(String title, boolean isCorrect) {
        this.title = title;
        this.isCorrect = isCorrect;
    }

    public String getTitle() {
        return title;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public static Option mapToObject(HashMap<String, ?> map) {
        return new Option((String) map.get("title"), (boolean) map.get("isCorrect"));
    }
}
