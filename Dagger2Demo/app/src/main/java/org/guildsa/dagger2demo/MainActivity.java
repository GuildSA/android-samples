package org.guildsa.dagger2demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import javax.inject.Inject;

//
// Note: To use Dagger correctly, we need to add annotations to our existing classes
//       and write a few new classes to help Dagger answer 3 big questions:
//
// 1. Which object do we want to inject?
//
//    Use @Provides to create the method that returns it.
//
// 2. Where do we want to inject it?
//
//    Use @Inject in the place where you want to use this and create an interface using
//    @Componnet that contains all the places where you will use this object.
//
// 3. How will we construct the object?
//
//    Use @Module to create a class that contains your method that uses @Provides
//    to return it.

public class MainActivity extends AppCompatActivity {

    private final static String TAG = MainActivity.class.getSimpleName();

    // Our MainActivity class wants the UserPrefs and CloudDatabase objects injected into it!
    @Inject UserPrefs mUserPrefs;
    @Inject CloudDatabase mCloudDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyApplication myApp = ((MyApplication) getApplication());

        //
        // Our first example is fairly simple; we'll inject the UserPrefs object held by
        // MyApplication, into our class MainActivity.
        //

        myApp.getUserPrefsComponent().inject(this);

        boolean injected = mUserPrefs == null ? false : true;
        Log.d(TAG, "mUserPrefs injected = " + String.valueOf(injected));

        String zipCode = mUserPrefs.getZipCode();
        Log.d(TAG, "zipCode = " + zipCode);

        //
        // The second example is a bit more complicated; we'll use Dagger to inject the
        // CloudDatabase object held by MyApplication, into our class MainActivity, and the
        // CloudDatabase itself has an internal dependency on HttpClient.
        //

        myApp.getCloudDatabaseComponent().inject(this);

        injected = mCloudDatabase == null ? false : true;
        Log.d(TAG, "mCloudDatabase injected = " + String.valueOf(injected));

        String userData = mCloudDatabase.getUserData("42");
        Log.d(TAG, "userData = " + userData);
    }
}
