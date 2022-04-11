package me.otmane.myapplication.models;

public class Intern {
    private static int counter = 0;
    private final int num;

    private String fullName;
    private String group;

    public Intern() {
        counter += 1;
        this.num = counter;
    }

    public Intern(String fullName, String group) {
        this();

        this.fullName = fullName;
        this.group = group;
    }

    public int getNum() {
        return num;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
}
