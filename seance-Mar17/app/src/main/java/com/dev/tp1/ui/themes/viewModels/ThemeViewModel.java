package com.dev.tp1.ui.themes.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.dev.tp1.data.DataRepository;
import com.dev.tp1.models.Theme;

import java.util.ArrayList;
import java.util.UUID;

public class ThemeViewModel extends ViewModel {

    private final MutableLiveData<ArrayList<Theme>> themes = new MutableLiveData<>(DataRepository.getThemeRepository().getThemes());

    public LiveData<ArrayList<Theme>> getThemes() {
        return themes;
    }

    public void add(Theme theme) {
        DataRepository.getThemeRepository().add(theme);
        refresh();
    }

    public void update(UUID themeId, Theme theme) {
        DataRepository.getThemeRepository().update(themeId, theme);
        refresh();
    }

    public void remove(Theme theme) {
        DataRepository.getThemeRepository().delete(theme);
        refresh();
    }

    private void refresh() {
        this.themes.postValue(DataRepository.getThemeRepository().getThemes());
    }

}
