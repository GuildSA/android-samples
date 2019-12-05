package org.guildsa.activitynavigation

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {

    public override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val intent = intent
        // Get the data passed to us.
        val userName = intent.getStringExtra("userName")
        val password = intent.getStringExtra("password")

        // Displaying received data.
        user_name_tv.text = userName
        password_tv.text = password

        // Binding Click event to Button
        close_btn.setOnClickListener {

            // Package up some data and set a result to send back
            // to the Activity that called us and close this Activity.
            val resultIntent = Intent()

            // Let's pretend that we logged into the player's account and
            // got their screen name and current high score.
            resultIntent.putExtra("screenName", "c1pher")
            resultIntent.putExtra("highScore", 12456)
            setResult(Activity.RESULT_OK, resultIntent)

            finish()
        }
    }
}