package me.otmane.b3.project;

import android.app.Application;

import com.google.android.material.color.DynamicColors;

public class QuizApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        DynamicColors.applyToActivitiesIfAvailable(this);
    }
}
