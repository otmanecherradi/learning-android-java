package com.dev.schoolmanagement.models;

import java.util.UUID;

public class Module {
    private final UUID id;

    String name;
    Double hours;

    public Module() {
        id = UUID.randomUUID();
    }

    public Module(String name, Double hours) {
        this();

        this.name = name;
        this.hours = hours;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getHours() {
        return hours;
    }

    public void setHours(Double hours) {
        this.hours = hours;
    }
}
