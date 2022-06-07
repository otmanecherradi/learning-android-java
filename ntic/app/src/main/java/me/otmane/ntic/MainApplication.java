package me.otmane.ntic;

import android.app.Application;

public class MainApplication extends Application {
    public static final String TAG = "MainApplication";

    @Override
    public void onCreate() {
        super.onCreate();

        DataStore.init(getApplicationContext());
    }
}
