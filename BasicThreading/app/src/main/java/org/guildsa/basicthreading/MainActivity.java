package org.guildsa.basicthreading;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.concurrent.atomic.AtomicBoolean;

public class MainActivity extends AppCompatActivity {

    private ProgressBar mProgress;
    private TextView mText;

    // A thread-safe boolean value that may be updated atomically.
    private final AtomicBoolean mComputePrimes = new AtomicBoolean(false);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mProgress = (ProgressBar) findViewById(R.id.progressBar1);
        mText = (TextView) findViewById(R.id.textView1);
    }

    public void startProgress(View view) {

        mComputePrimes.set(true);

        mText.setText("Start");

        //
        // Without concurrency...
        //

        // Do something that will take a long time!
        for(int i = 0; i <= 10; i++) {

            if(!mComputePrimes.get()) {
                break;
            }

            generatePrimes(5000000);

            final int value = i;

            mText.setText("Progress " + String.valueOf(value));
            mProgress.setProgress(value);
        }

        mText.setText("Done!");
    }

    public void startProgressRunnable(View view) {

        mComputePrimes.set(true);

        mText.setText("Start");

        //
        // Concurrency using a Runnable...
        //

        Runnable runnable = new Runnable() {
            @Override
            public void run() {

                // Do something that will take a long time!
                for(int i = 0; i <= 10; i++) {

                    if(!mComputePrimes.get()) {
                        break;
                    }

                    generatePrimes(5000000);

                    final int value = i;

                    // Since you can only safely modify a UI element on the Main/UI Thread, we'll
                    // push the work back to the Main/UI Thread by posting a Runnable using the
                    // method runOnUiThread which is on our Activity.

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mText.setText("Progress " + String.valueOf(value));
                            mProgress.setProgress(value);
                        }
                    });

                    // It's also possible to push work back to the Main/UI Thread by posting a
                    // Runnable using the post() method on any View.

//                    progress.post(new Runnable() {
//                        @Override
//                        public void run() {
//                            text.setText("Progress " + String.valueOf(value));
//                            progress.setProgress(value);
//                        }
//                    });
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mText.setText("Done!");
                    }
                });
            }
        };

        new Thread(runnable).start();
    }

    public void startProgressAsyncTask(View view) {

        mComputePrimes.set(true);

        new MyAsyncTask().execute(10);
    }

    // AsyncTask<Params, Progress, Result>

    private class MyAsyncTask extends AsyncTask<Integer, Integer, String> {

        @Override
        protected String doInBackground(Integer... params) {

            // Do something that will take a long time!
            for(int i = 0; i <= 10; i++) {

                if(!mComputePrimes.get()) {
                    break;
                }

                generatePrimes(5000000);

                publishProgress(i);
            }

            return "Executed";
        }

        @Override
        protected void onPostExecute(String result) {
            mText.setText("Done!");
        }

        @Override
        protected void onPreExecute() {
            mText.setText("Start");
        }

        @Override
        protected void onProgressUpdate(Integer... values) {

            mText.setText("Progress " + String.valueOf(values[0]));
            mProgress.setProgress(values[0]);
        }
    }

    public void stopProgress(View view) {

        mComputePrimes.set(false);
    }

    private static int [] generatePrimes(int max) {

        boolean[] isComposite = new boolean[max + 1];

        for(int i = 2; i * i <= max; i++) {
            if(!isComposite[i]) {
                for(int j = i; i * j <= max; j++) {
                    isComposite [i*j] = true;
                }
            }
        }

        int numPrimes = 0;

        for(int i = 2; i <= max; i++) {
            if(!isComposite[i]) {
                numPrimes++;
            }
        }

        int [] primes = new int [numPrimes];
        int index = 0;

        for(int i = 2; i <= max; i++) {
            if(!isComposite[i]) {
                primes[index++] = i;
            }
        }

        return primes;
    }
}
