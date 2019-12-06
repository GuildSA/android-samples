package org.guildsa.listview

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private var listAdapter: ArrayAdapter<String?>? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Create and populate a List of test data.
        val myData = arrayOf<String?>(
                "Some data 1",
                "Some data 2",
                "Some data 3",
                "Some data 4",
                "Some data 5",
                "Some data 6",
                "Some data 7",
                "Some data 8",
                "Some data 9",
                "Some data 10",
                "Some data 11",
                "Some data 12",
                "Some data 13",
                "Some data 14",
                "Some data 15")

        val dataList = ArrayList<String?>()
        dataList.addAll(Arrays.asList(*myData))

        // Create an ArrayAdapter using our list of strings.
        // The second arg "android.R.layout.simple_list_item_1" is the resource id of the default
        // layout provided by the Android SDK. Useful for a quick demo or test of a ListView but
        // not really used very often in real code.
        listAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, dataList)
        listAdapter?.add("Some data 16")
        listAdapter?.add("Some data 17")
        listAdapter?.add("Some data 18")
        listAdapter?.add("Some data 19")
        listAdapter?.add("Some data 20")

        // Set our listAdapter as the ListView's adapter. This is how it knows where to get the
        // data for the ListView's rows.
        listView.adapter = listAdapter
    }
}