package me.otmane.b3.project.models;

import java.util.Comparator;
import java.util.List;

public class Question {
    private final String id;
    private final String title;
    private final int order;
    private final List<Option> options;

    public Question(String id, String title, int order, List<Option> options) {
        this.id = id;
        this.title = title;
        this.order = order;
        this.options = options;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public List<Option> getOptions() {
        return options;
    }

    public static Comparator<Question> questionComparator = (o1, o2) -> o1.order - o2.order;
}
