package org.guildsa.activityanimation

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    public override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //
        // Slide the new SecondActivity in from the right side and have it cover the MainActivity.
        // The MainActivity will hold or stay in place.
        //

        slideInNextActivityBtn.setOnClickListener {

            // Create a new Intent.
            val nextActivity = Intent(applicationContext, SecondActivity::class.java)

            // Start a new activity and ignore any results.
            startActivity(nextActivity)

            // Specify what the enter animation should be for the new Activity and
            // what the exit animation should be for the current Activity.
            overridePendingTransition(R.anim.slide_in_right, R.anim.hold)
        }

        //
        // Push the new SecondActivity in from the right side and have it push out the
        // MainActivity to the left side.
        //

        pullInNextActivityBtn.setOnClickListener {

            // Create a new Intent.
            val nextActivity = Intent(applicationContext, SecondActivity::class.java)

            // Start a new activity and ignore any results.
            startActivity(nextActivity)

            // Specify what the enter animation should be for the new Activity and
            // what the exit animation should be for the current Activity.
            overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left)
        }

        //
        // Fade in the new SecondActivity while fading out the MainActivity.
        //

        fadeToNextActivityBtn.setOnClickListener {

            // Create a new Intent.
            val nextActivity = Intent(applicationContext, SecondActivity::class.java)

            // NOTE: If you can't get access to overridePendingTransition, you can create a
            // Bundle that specifies the desired animations and then you can pass the Bundle to
            // startActivity.
            val bundle = ActivityOptions.makeCustomAnimation(applicationContext,
                    R.anim.fade_in, R.anim.fade_out).toBundle()

            startActivity(nextActivity, bundle)
        }
    }
}