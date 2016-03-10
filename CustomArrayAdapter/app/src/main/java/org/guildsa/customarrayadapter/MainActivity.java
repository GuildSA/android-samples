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

        String[] phoneNumbers = getResources().getStringArray( R.array.phone_numbers );

        MyCustomArrayAdapter customArrayAdaptor = new MyCustomArrayAdapter( this, phoneNumbers );

        // Set the ArrayAdapter as the ListView's adapter.
        listView.setAdapter( customArrayAdaptor );

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // When clicked, show a toast with the TextView text.
                Toast.makeText(getApplicationContext(), "Calling: " + ((TextView) view).getText(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
