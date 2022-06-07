package me.otmane.ntic.models;

import com.google.gson.annotations.SerializedName;

public class Branch {
    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;
    @SerializedName("level")
    private String level;
    @SerializedName("description")
    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
