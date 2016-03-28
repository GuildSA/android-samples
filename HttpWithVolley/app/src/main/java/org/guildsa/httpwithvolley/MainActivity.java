package org.guildsa.httpwithvolley;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {

    // We're going to use a fake Online REST API for Testing and Prototyping.
    //http://jsonplaceholder.typicode.com/
    public static final String URL = "http://jsonplaceholder.typicode.com/users";

    Button button;
    TextView outputText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        outputText = (TextView) findViewById(R.id.outputTxt);

        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Clear out any old data!
                outputText.setText("");

                // Instantiate the RequestQueue.
                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);

                // Request a string response from the provided URL.
                StringRequest stringRequest = new StringRequest(Request.Method.GET, URL,
                        new Response.Listener<String>() {

                            @Override
                            public void onResponse(String response) {
                                // Display the response string.
                                outputText.setText("Response is: " + response);
                            }
                        }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        outputText.setText("That didn't work!");
                    }
                });

                // Add the request to the RequestQueue.
                queue.add(stringRequest);
            }
        });
    }
}
