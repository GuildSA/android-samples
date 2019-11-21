package org.guildsa.activityanimation;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    public void onCreate( Bundle savedInstanceState ) {

        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        //
        // Slide the new SecondActivity in from the right side and have it cover the MainActivity.
        // The MainActivity will hold or stay in place.
        //

        Button slideInNextActivityBtn = (Button) findViewById( R.id.slideInNextActivityBtn );
        slideInNextActivityBtn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {

                // Create a new Intent.
                Intent nextActivity = new Intent(getApplicationContext(), SecondActivity.class);

                // Start a new activity and ignore any results.
                startActivity(nextActivity);

                // Specify what the enter animation should be for the new Activity and
                // what the exit animation should be for the current Activity.
                overridePendingTransition(R.anim.slide_in_right, R.anim.hold);
            }
        });

        //
        // Push the new SecondActivity in from the right side and have it push out the
        // MainActivity to the left side.
        //

        Button pullInNextActivityBtn = (Button) findViewById( R.id.pullInNextActivityBtn );
        pullInNextActivityBtn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {

                // Create a new Intent.
                Intent nextActivity = new Intent(getApplicationContext(), SecondActivity.class);

                // Start a new activity and ignore any results.
                startActivity(nextActivity);

                // Specify what the enter animation should be for the new Activity and
                // what the exit animation should be for the current Activity.
                overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
            }
        });

        //
        // Fade in the new SecondActivity while fading out the MainActivity.
        //

        Button fadeToNextActivityBtn = (Button) findViewById( R.id.fadeToNextActivityBtn );
        fadeToNextActivityBtn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {

                // Create a new Intent.
                Intent nextActivity = new Intent(getApplicationContext(), SecondActivity.class);

                // NOTE: If you can't get access to overridePendingTransition, you can create a
                // Bundle that specifies the desired animations and then you can pass the Bundle to
                // startActivity.

                Bundle bundle = ActivityOptions.makeCustomAnimation(getApplicationContext(),
                        R.anim.fade_in, R.anim.fade_out).toBundle();

                startActivity(nextActivity, bundle);
            }
        });
    }
}
