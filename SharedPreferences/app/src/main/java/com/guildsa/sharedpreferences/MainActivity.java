package com.guildsa.sharedpreferences;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // Here's how we can read some data out of our app's Default Shared Preferences.
    public void onClickReadSharedPrefsButton( View v ) {

        Log.d(TAG, "Manually reading our app's Default Shared Preferences!");

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        Integer age = sharedPreferences.getInt("AGE", 0);
        Boolean signedIntoFacebook = sharedPreferences.getBoolean("SIGNED_INTO_FACEBOOK", false);
        String phoneNumber = sharedPreferences.getString("PHONE_NUMBER", "");
        String homeTown = sharedPreferences.getString("HOME_TOWN", "");

        Log.d(TAG, "age = " + String.valueOf(age));
        Log.d(TAG, "signedIntoFacebook = " + String.valueOf(signedIntoFacebook));
        Log.d(TAG, "homeTown = " + homeTown);
        Log.d(TAG, "phoneNumber = " + phoneNumber);
    }

    // Here's how we can write some data to our app's Default Shared Preferences.
    public void onClickWriteSharedPrefsButton( View v ) {

        Log.d(TAG, "Manually writing out data to our app's Default Shared Preferences!");

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt("AGE", 24);
        editor.putBoolean("SIGNED_INTO_FACEBOOK", true);
        editor.putString("HOME_TOWN", "Dallas");
        editor.putString("PHONE_NUMBER", "555-123-4567");

        // Commit changes to SharedPreferences.
        editor.commit();
    }

    // Because there's no convenient way to write other types of data such as Lists, Objects or
    // Images to our app's Default Shared Preferences, some developers use utility wrapper classes
    // such as TinyDB to store a wider variety of data.
    //
    // https://github.com/kcochibili/TinyDB--Android-Shared-Preferences-Turbo
    //
    // With the help of TinyDB, we can store a List of strings under the key "SEARCH_TERMS".

    public void onClickReadTinyDBButton( View v ) {

        Log.d(TAG, "Using TinyDB to read our app's Default Shared Preferences!");

        TinyDB tinydb = new TinyDB(this);

        Integer age = tinydb.getInt("AGE");
        Boolean signedIntoFacebook = tinydb.getBoolean("SIGNED_INTO_FACEBOOK");
        String phoneNumber = tinydb.getString("PHONE_NUMBER");
        String homeTown = tinydb.getString("HOME_TOWN");

        Log.d(TAG, "age = " + String.valueOf(age));
        Log.d(TAG, "signedIntoFacebook = " + String.valueOf(signedIntoFacebook));
        Log.d(TAG, "homeTown = " + homeTown);
        Log.d(TAG, "phoneNumber = " + phoneNumber);

        ArrayList<String> searchTerms = tinydb.getListString("SEARCH_TERMS");

        if(searchTerms == null || searchTerms.isEmpty()) {
            Log.d(TAG, "SEARCH_TERMS has never been set!");
        } else {
            for(String searchTerm : searchTerms) {
                Log.d(TAG, "searchTerm = " + searchTerm);
            }
        }
    }

    public void onClickWriteTinyDBButton( View v ) {

        Log.d(TAG, "Using TinyDB to write out data to our app's Default Shared Preferences!");

        TinyDB tinydb = new TinyDB(this);

        tinydb.putInt("AGE", 42);
        tinydb.putBoolean("SIGNED_INTO_FACEBOOK", true);
        tinydb.putString("HOME_TOWN", "Frisco");
        tinydb.putString("PHONE_NUMBER", "555-555-4567");

        ArrayList<String> searchTerms = new ArrayList<>();
        searchTerms.add("Irish Pubs");
        searchTerms.add("Concerts Frisco");
        searchTerms.add("Coffee Shops");

        tinydb.putListString("SEARCH_TERMS", searchTerms);
    }
}
