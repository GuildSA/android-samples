package org.guildsa.activityanimation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {

    public override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        //
        // The MainActivity will hold or stay in place while the SecondActivity slides out
        // to the right side revealing the MainActivity, which we know should be there already.
        //

        finsihWithSlideOutBtn.setOnClickListener {

            finish()

            // Specify what the enter animation should be for the new Activity and
            // what the exit animation should be for the current Activity.
            overridePendingTransition(R.anim.hold, R.anim.slide_out_right)
        }

        //
        // The MainActivity will be pulled in from the left side while the SecondActivity is pushed
        // out to the right side.
        //

        finsihWithPullOutBtn.setOnClickListener {

            finish()

            // Specify what the enter animation should be for the new Activity and
            // what the exit animation should be for the current Activity.
            overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right)
        }

        //
        // The MainActivity will fade in while the SecondActivity fades out.
        //

        finishWithfadeBtn.setOnClickListener {

            finish()

            // Specify what the enter animation should be for the new Activity and
            // what the exit animation should be for the current Activity.
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        }
    }
}