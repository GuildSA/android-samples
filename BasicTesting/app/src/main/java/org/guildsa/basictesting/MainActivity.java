package org.guildsa.basictesting;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * The preferred way of organizing Android tests can be seen by simply creating a new Android app
 * through Android Studio's new project wizard. If we create a new app called "myApp", we should
 * end up with a directory structure like this:
 *
 *  myApp/app/src/main/java - This is the location of our app's source code.
 *
 *  myApp/app/src/test/java - This is the location of our Unit Tests which can run on the JVM
 *                            and do not require an Android device or Android simulator to run.
 *
 *  myApp/app/src/androidTest/java - This is the location of any unit tests that must run on an
 *                                   Android device to tested correctly. Unit tests of this
 *                                   nature are called "Android Instrumentation Tests".
 *
 * As long as we use this folder structure, the Android build system can automatically run the unit
 * tests on the JVM and the Android tests on an Android device.
 *
 * By default, the "Android Instrumentation Tests", which are located in
 * "myApp/app/src/androidTest/java" will be set as the default and will show up in the File Explorer
 * under the app's regular Java source folder. You can switch from "Android Instrumentation Tests"
 * to "Unit Tests" by opening the "Build Variants" view on the left-hand side of Android Studio and
 * switching active test via the drop down called "Test Artifact".
 *
 * Once you switch the type of unit tests you want to use, return to the File Explorer and
 * right-click on the test Class file that you want to run and select "Run".
 *
 * More advanced samples dealing with Android Testing within Android Studio can be fond here:
 * https://github.com/googlesamples/android-testing
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
