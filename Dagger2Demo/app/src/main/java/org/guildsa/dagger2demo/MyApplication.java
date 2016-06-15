package org.guildsa.dagger2demo;

import android.app.Application;

public class MyApplication extends Application {

    private UserPrefsComponent mUserPrefsComponent;
    private CloudDatabaseComponent mCloudDatabaseComponent;

    @Override
    public void onCreate() {

        super.onCreate();

        // Since our app only wants one instance of UserPrefs and CloudDatabase, we'll
        // have our custom Application object build a UserPrefsComponent and a
        // CloudDatabaseComponent and hold onto to them for the life of the app.
        // Later, If another object, such as an Activity, wants to use them it can
        // access the Component it wants and inject it.

        mUserPrefsComponent = DaggerUserPrefsComponent.builder().build();
        mCloudDatabaseComponent = DaggerCloudDatabaseComponent.builder().build();
    }

    public UserPrefsComponent getUserPrefsComponent() {

        return mUserPrefsComponent;
    }

    public CloudDatabaseComponent getCloudDatabaseComponent() {

        return mCloudDatabaseComponent;
    }
}