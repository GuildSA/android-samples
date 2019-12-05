package org.guildsa.httpwithasynctask

import android.os.AsyncTask
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        get_data_btn.setOnClickListener {

            val getHttpTask = GetHTTPTask()
            // Do this if we're going to passing an array of several URLs.
            //getHttpTask.execute( new String[] { URL } );
            // For this sample, we're just going to pass one URL.
            getHttpTask.execute(URL)
        }
    }

    private inner class GetHTTPTask : AsyncTask<String?, Void?, String?>() {

        override fun doInBackground(vararg urls: String?): String? {

            var output: String? = null
            // AsyncTask takes an array of the specified type, but that makes less sense when
            // working on HTTP requests. If we did want to process several URLs, we could access
            // them like so:
            // for( String url : urls ) {
            //     // Use the url to get some data...
            // }
            // We know there is only one URL, so just do this for our single HTTP request.
            output = getOutputFromUrl(urls[0])
            return output
        }

        private fun getOutputFromUrl(url: String?): String? {

            val builder = StringBuilder()

            try {
                val stream = getHttpConnection(url)
                val buffer = BufferedReader(InputStreamReader(stream))
                var line: String? = ""
                while(buffer.readLine().also { line = it } != null) {
                    builder.append(line)
                }
            } catch(e1: IOException) {
                e1.printStackTrace()
            }

            return builder.toString()
        }

        // Makes HttpURLConnection and returns InputStream
        @Throws(IOException::class)
        private fun getHttpConnection(urlString: String?): InputStream? {

            var stream: InputStream? = null
            val url = URL(urlString)

            try {
                val connection = url.openConnection()
                val httpConnection = connection as HttpURLConnection
                httpConnection.requestMethod = "GET"
                httpConnection.connect()
                if(httpConnection.responseCode == HttpURLConnection.HTTP_OK) {
                    stream = httpConnection.inputStream
                }
            } catch(ex: Exception) {
                ex.printStackTrace()
            }

            return stream
        }

        override fun onPostExecute(output: String?) {

            // If our GET request returns any data, print it out.
            output_tv.text = output
        }
    }

    companion object {
        // We're going to use a fake Online REST API for Testing and Prototyping.
        //https://jsonplaceholder.typicode.com/
        val URL: String? = "https://jsonplaceholder.typicode.com/users"
    }
}