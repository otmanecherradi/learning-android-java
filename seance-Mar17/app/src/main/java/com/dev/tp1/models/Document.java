package com.dev.tp1.models;

import java.util.UUID;


public class Document {
    private final UUID code;

    private String description;
    private boolean state;

    private UUID themeId;

    public Document() {
        code = UUID.randomUUID();
    }

    public Document(String description, Boolean state, UUID themeId) {
        this();

        this.description = description;
        this.state = state;
        this.themeId = themeId;
    }

    public UUID getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public UUID getThemeId() {
        return themeId;
    }

    public void setThemeId(UUID themeId) {
        this.themeId = themeId;
    }
}
