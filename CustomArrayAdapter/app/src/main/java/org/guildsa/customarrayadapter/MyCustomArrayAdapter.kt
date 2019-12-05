package org.guildsa.customarrayadapter

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.list_item.view.*
import java.text.MessageFormat

class MyCustomArrayAdapter(context: Context, private val values: Array<String?>) : ArrayAdapter<String?>(context, R.layout.list_item, values) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {

        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val rowView = inflater.inflate(R.layout.list_item, parent, false)

        //
        // Do something custom!
        //

        val rawPhoneNumber = values[position] // "1234567890"

        // Break the raw phone number of 12 digits into 3 sub-strings of 3, 3, and 4 digits.
        val phoneNumberSubStrings = arrayOf(
                rawPhoneNumber?.substring(0, 3),  // "123"
                rawPhoneNumber?.substring(3, 6),  // "456"
                rawPhoneNumber?.substring(6) // "7890"
        )

        // Create a MessageFormat to rebuild the phone number sub strings as a more visually pleasing phone number.
        val phoneNumberFormat = MessageFormat("({0}) {1}-{2}")
        val formattedPhoneNumber = phoneNumberFormat.format(phoneNumberSubStrings) // "(123) 456-7890"

        rowView.label_tv.setText(formattedPhoneNumber)
        rowView.label_tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 32f)
        rowView.label_tv.setTypeface(null, Typeface.BOLD)
        rowView.label_tv.setTextColor(Color.BLUE)

        return rowView
    }

}