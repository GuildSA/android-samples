package org.guildsa.activitynavigation

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val MY_CHILD_ACTIVITY = 1

    public override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        // Listening to button event
        next_screen_btn.setOnClickListener {

            // Starting a new Intent
            val nextScreen = Intent(applicationContext, SecondActivity::class.java)

            // Sending data to another Activity
            nextScreen.putExtra("userName", user_name_et.text.toString())
            nextScreen.putExtra("password", password_et.text.toString())

            // Start a new activity and ignore any results.
            // startActivity( nextScreen );
            // Start a new activity and catch the results.
            startActivityForResult(nextScreen, MY_CHILD_ACTIVITY)
        }
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        super.onActivityResult(requestCode, resultCode, data)

        when(requestCode) {

            MY_CHILD_ACTIVITY -> if(resultCode == Activity.RESULT_OK) {

                Log.d(TAG, "Activity.RESULT_OK")

                // Get the data passed to us.
                val screenName = data?.getStringExtra("screenName")
                val highScore = data?.getIntExtra("highScore", 0)

                Log.d(TAG, "screenName = $screenName")
                Log.d(TAG, "highScore = $highScore")

                screen_name_tv.text = "Screen Name: $screenName"
                high_score_tv.text = "High Score: $highScore"
            }
        }
    }

    companion object {
        private val TAG: String? = "MainActivity"
    }
}