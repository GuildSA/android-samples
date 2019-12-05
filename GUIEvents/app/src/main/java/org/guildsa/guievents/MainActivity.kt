package org.guildsa.guievents

import android.app.AlertDialog
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    // If we want to hang on to certain UI widgets for a while, we can create
    // Instance Variables to hold them.
    //var toggleButton: ToggleButton? = null
    //var textView: TextView? = null

    public override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Wire up button 1 the manual way using its id.
        // The id "R.id.button1" will not exist until after a built since
        // it lives in the auto-generated file /gen/org.guildsa.R.java
        button1.setOnClickListener { showAlert("This is button1!") }

        // Wire up check-box 1 the manual way using its id.
        checkBox1.setOnCheckedChangeListener { buttonView, isChecked ->

            if(isChecked) {
                textView1.text = "Checked"
            } else {
                textView1.text = "Unchecked"
            }
        }

        // Initialize our instance variables for the TextView and the ToggleButton
        // so we only have to call findViewById once for each.
        textView2.setBackgroundColor(-0x100000)
        toggleButton1.setOnClickListener(this)
    }

    // Wire up button 2 through the Layout Editor by specifying the method's name.
    // Look in /res/layout/main.xml
    fun onClickButton2(v: View?) {
        showAlert("This is button2!")
    }

    //
    // Our Activity class implements View.OnClickListener, so we can
    // override onClick from View.OnClickListener and listen for click
    // and check for events such as whether or not our toggle button has
    // changed.
    override fun onClick(v: View?) {

        if(toggleButton1.isChecked) {
            textView1.text = "Toggle On"
            textView1.setBackgroundColor(Color.GREEN)
            textView1.setTextColor(Color.BLACK)
        } else {
            textView1.text = "Toggle Off"
            textView1.setBackgroundColor(Color.RED)
            textView1.setTextColor(Color.WHITE)
        }
    }

    // This is a simple method that shows an AlertDialog for testing purposes.
    private fun showAlert(message: String?) {

        val alert = AlertDialog.Builder(this)
        alert.setTitle("Alert!").setMessage(message).setNeutralButton("OK", null).show()
    }
}