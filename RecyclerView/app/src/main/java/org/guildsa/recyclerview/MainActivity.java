package org.guildsa.recyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // Use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // Use a linear layout manager.
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // Create and populate an array of test data.
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

        // Set our custom RecyclerView.Adapter
        mAdapter = new MyAdapter(myData);
        mRecyclerView.setAdapter(mAdapter);
    }
}
