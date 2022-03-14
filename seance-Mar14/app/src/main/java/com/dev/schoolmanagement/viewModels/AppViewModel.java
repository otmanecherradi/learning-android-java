package com.dev.schoolmanagement.viewModels;

import androidx.lifecycle.ViewModelProvider;

import com.dev.schoolmanagement.MainActivity;

public class AppViewModel {
    MainActivity activity;
    static AppViewModel appViewModel;

    public AppViewModel(MainActivity activity) {
        this.activity = activity;
    }

    public static AppViewModel getInstance(MainActivity activity) {
        if (appViewModel == null)
            appViewModel = new AppViewModel(activity);
        return appViewModel;
    }

    public static AppViewModel getInstance() {
        return appViewModel;
    }

    public GroupViewModel getGroupViewModel() {
        return new ViewModelProvider(activity).get(GroupViewModel.class);
    }


}
