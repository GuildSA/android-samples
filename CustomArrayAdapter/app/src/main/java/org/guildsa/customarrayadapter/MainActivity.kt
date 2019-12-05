package org.guildsa.customarrayadapter

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.list_item.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        // Get our test phone numbers which are stored in the res/values/strings.xml file.
        val phoneNumbers = resources.getStringArray(R.array.phone_numbers)

        // Create an instance of our custom array adaptor to fill our ListView.
        val customArrayAdaptor = MyCustomArrayAdapter(this, phoneNumbers)

        // Set our custom array adaptor as the ListView's adapter.
        test_lv.adapter = customArrayAdaptor

        test_lv.setOnItemClickListener{ parent, view, position, id ->

            // When clicked, show a toast using the TextView text in our row's layout.
            Toast.makeText(applicationContext, "Calling: " + label_tv.text, Toast.LENGTH_SHORT).show()
        }
    }
}
