package org.guildsa.customarrayadapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class MyCustomArrayAdapter extends ArrayAdapter<String> {

	private final Context context;
	private final String[] values;

	public MyCustomArrayAdapter(Context context, String[] values) {

		super(context, R.layout.list_item, values);
		
		this.context = context;
		this.values = values;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.list_item, parent, false);
		
		TextView textView = (TextView) rowView.findViewById(R.id.label);

		//
		// Do something custom!
		//

		String rawPhoneNumber = values[position]; // "1234567890"

		// Break the raw phone number of 12 digits into 3 sub-strings of 3, 3, and 4 digits.
		String[] phoneNumberSubStrings = {
            rawPhoneNumber.substring(0, 3), // "123"
            rawPhoneNumber.substring(3, 6), // "456"
            rawPhoneNumber.substring(6)     // "7890"
		};
		
		// Create a MessageFormat to rebuild the phone number sub strings as a more visually pleasing phone number.
		java.text.MessageFormat phoneNumberFormat = new java.text.MessageFormat( "({0}) {1}-{2}" );
		String formattedPhoneNumber = phoneNumberFormat.format( phoneNumberSubStrings ); // "(123) 456-7890"

		textView.setText(formattedPhoneNumber);
		textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 32);
		textView.setTypeface(null, Typeface.BOLD);
		textView.setTextColor(Color.BLUE);

		return rowView;
	}
}
