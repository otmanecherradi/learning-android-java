package com.dev.tp1.modelViews;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {
    private final MutableLiveData<String> title = new MutableLiveData<>();

    public LiveData<String> getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title.postValue(title);
    }
}
