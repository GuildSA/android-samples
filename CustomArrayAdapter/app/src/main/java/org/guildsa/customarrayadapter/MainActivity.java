package org.guildsa.customarrayadapter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById( R.id.listView );

        // Get our test phone numbers which are stored in the res/values/strings.xml file.
        String[] phoneNumbers = getResources().getStringArray( R.array.phone_numbers );

        // Create an instance of our custom array adaptor to fill our ListView.
        MyCustomArrayAdapter customArrayAdaptor = new MyCustomArrayAdapter( this, phoneNumbers );

        // Set our custom array adaptor as the ListView's adapter.
        listView.setAdapter( customArrayAdaptor );

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // When clicked, show a toast using the TextView text in our row's layout.
                TextView textView = (TextView) view.findViewById(R.id.label);
                Toast.makeText(getApplicationContext(), "Calling: " + textView.getText(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
