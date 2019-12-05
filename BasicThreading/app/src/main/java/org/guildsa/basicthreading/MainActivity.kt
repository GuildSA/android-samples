package org.guildsa.basicthreading

import android.os.AsyncTask
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.atomic.AtomicBoolean


class MainActivity : AppCompatActivity() {

    // A thread-safe boolean value that may be updated atomically.
    private val mComputePrimes: AtomicBoolean = AtomicBoolean(false)

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun startProgress(view: View?) {

        mComputePrimes.set(true)
        textView1.text = "Start"

        //
        // Without concurrency...
        //

        // Do something that will take a long time!
        for(i in 0..10) {

            if(!mComputePrimes.get()) {
                break
            }

            generatePrimes(5000000)
            textView1.text = "Progress $i"
            progressBar1.progress = i
        }

        textView1.text = "Done!"
    }

    fun startProgressRunnable(view: View?) {

        mComputePrimes.set(true)
        textView1.text = "Start"

        //
        // Concurrency using a Runnable...
        //

        val runnable = Runnable {
            // Do something that will take a long time!
            for(i in 0..10) {

                if(!mComputePrimes.get()) {
                    break
                }

                generatePrimes(5000000)

                // Since you can only safely modify a UI element on the Main/UI Thread, we'll
                // push the work back to the Main/UI Thread by posting a Runnable using the
                // method runOnUiThread which is on our Activity.
                runOnUiThread {
                    textView1.text = "Progress $i"
                    progressBar1.progress = i
                }

//                // It's also possible to push work back to the Main/UI Thread by posting a
//                // Runnable using the post() method on any View.
//                progressBar1.post {
//                    textView1.text = "Progress $i"
//                    progressBar1.progress = 1
//                }

            }
            runOnUiThread { textView1.text = "Done!" }
        }
        Thread(runnable).start()
    }

    fun startProgressAsyncTask(view: View?) {

        mComputePrimes.set(true)
        MyAsyncTask().execute(10)
    }

    // AsyncTask<Params, Progress, Result>
    private inner class MyAsyncTask : AsyncTask<Int?, Int?, String?>() {

        override fun doInBackground(vararg params: Int?): String? {

            // Do something that will take a long time!
            for(i in 0..10) {
                if(!mComputePrimes.get()) {
                    break
                }
                generatePrimes(5000000)
                publishProgress(i)
            }
            return "Executed"
        }

        override fun onPostExecute(result: String?) {
            textView1.text = "Done!"
        }

        override fun onPreExecute() {
            textView1.text = "Start"
        }

        override fun onProgressUpdate(vararg values: Int?) {

            textView1.text = "Progress " + values[0].toString()
            progressBar1.progress = values[0]!!
        }
    }

    fun stopProgress(view: View?) {
        mComputePrimes.set(false)
    }

    companion object {

        private fun generatePrimes(max: Int): IntArray? {

            val isComposite = BooleanArray(max + 1)

            run {
                var i = 2
                while(i * i <= max) {
                    if(!isComposite[i]) {
                        var j = i
                        while(i * j <= max) {
                            isComposite[i * j] = true
                            j++
                        }
                    }
                    i++
                }
            }

            var numPrimes = 0
            for(i in 2..max) {
                if(!isComposite[i]) {
                    numPrimes++
                }
            }

            val primes = IntArray(numPrimes)
            var index = 0
            for(i in 2..max) {
                if(!isComposite[i]) {
                    primes[index++] = i
                }
            }

            return primes
        }
    }
}