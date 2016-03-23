package org.guildsa.httpwithasynctask;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {

    // We're going to use a fake Online REST API for Testing and Prototyping.
    //http://jsonplaceholder.typicode.com/
    public static final String URL = "http://jsonplaceholder.typicode.com/users";

    Button button;
    TextView outputText;
    //GetHTTPTask getHttpTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        outputText = (TextView) findViewById(R.id.outputTxt);

        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                GetHTTPTask getHttpTask = new GetHTTPTask();

                // Do this if we're going to passing an array of several URLs.
                //getHttpTask.execute( new String[] { URL } );

                // For this sample, we're just going to pass one URL.
                getHttpTask.execute( URL );
            }
        });
    }

    private class GetHTTPTask extends AsyncTask<String, Void, String>
    {
        @Override
        protected String doInBackground(String... urls) {

            String output = null;

            // AsyncTask takes an array of the specified type, but that makes less sense when
            // working on HTTP requests. If we did want to process several URLs, we could access
            // them like so:

            // for( String url : urls ) {
            //     // Use the url to get some data...
            // }

            // We know there is only one URL, so just do this for our single HTTP request.
            output = getOutputFromUrl( urls[0] );

            return output;
        }

        private String getOutputFromUrl(String url) {

            StringBuilder builder = new StringBuilder();

            try {

                InputStream stream = getHttpConnection(url);
                BufferedReader buffer = new BufferedReader(new InputStreamReader(stream));

                String line = "";

                while((line = buffer.readLine()) != null) {
                    builder.append(line);
                }

            } catch(IOException e1) {
                e1.printStackTrace();
            }

            return builder.toString();
        }

        // Makes HttpURLConnection and returns InputStream
        private InputStream getHttpConnection(String urlString) throws IOException {

            InputStream stream = null;
            URL url = new URL(urlString);

            try {

                URLConnection connection = url.openConnection();

                HttpURLConnection httpConnection = (HttpURLConnection) connection;
                httpConnection.setRequestMethod("GET");
                httpConnection.connect();

                if(httpConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    stream = httpConnection.getInputStream();
                }

            } catch(Exception ex) {
                ex.printStackTrace();
            }

            return stream;
        }

        @Override
        protected void onPostExecute(String output) {

            // If our GET request returns any data, print it out.
            outputText.setText(output);
        }
    }
}
