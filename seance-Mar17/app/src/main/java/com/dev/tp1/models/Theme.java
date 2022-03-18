package com.dev.tp1.models;

import java.util.UUID;

public class Theme {
    private final UUID code;

    private String name;
    private String description;

    public Theme() {
        code = UUID.randomUUID();
    }

    public Theme(String nom, String description) {
        this();

        this.name = nom;
        this.description = description;
    }

    public UUID getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
