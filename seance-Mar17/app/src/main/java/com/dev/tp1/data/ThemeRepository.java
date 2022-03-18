package com.dev.tp1.data;

import com.dev.tp1.models.Theme;

import java.util.ArrayList;
import java.util.UUID;

public class ThemeRepository {
    public static final String TAG = "ThemeRepository";

    private final ArrayList<Theme> themes;

    public ThemeRepository() {
        themes = new ArrayList<>();

        themes.add(new Theme("theme 1", "description 1"));
        themes.add(new Theme("theme 2", "description 2"));
        themes.add(new Theme("theme 3", "description 3"));
    }

    public ArrayList<Theme> getThemes() {
        return themes;
    }

    public void add(Theme theme) {
        themes.add(theme);
    }

    public Theme search(UUID themeId) {
        for (Theme theme : themes) {
            if (theme.getCode().equals(themeId)) {
                return theme;
            }
        }
        return null;
    }

    public Theme search(String name) {
        for (Theme theme : themes) {
            if (theme.getName().equals(name)) {
                return theme;
            }
        }
        return null;
    }

    public void update(UUID themeId, Theme theme) {
        if (search(themeId) != null) {
            search(themeId).setName(theme.getName());
            search(themeId).setDescription(theme.getDescription());
        }
    }

    public void delete(Theme theme) {
        themes.remove(theme);
    }

}
