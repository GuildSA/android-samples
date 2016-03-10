package org.guildsa.listview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayAdapter<String> listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find the ListView in our layout.
        listView = (ListView) findViewById( R.id.listView );

        // Create and populate a List of planet names.
        String[] myData = new String[] {
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
                "Some data 15"};

        ArrayList<String> dataList = new ArrayList<>();
        dataList.addAll( Arrays.asList(myData) );

        // Create ArrayAdapter using list of strings.
        listAdapter = new ArrayAdapter<>(this, R.layout.simple_row, dataList);

        listAdapter.add( "Some data 16" );
        listAdapter.add( "Some data 17" );
        listAdapter.add( "Some data 18" );
        listAdapter.add( "Some data 19" );
        listAdapter.add( "Some data 20" );

        // Set the ArrayAdapter as the ListView's adapter.
        listView.setAdapter( listAdapter );
    }
}
