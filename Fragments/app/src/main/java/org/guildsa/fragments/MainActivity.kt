package org.guildsa.fragments

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import org.guildsa.fragments.TextEditFragment.TextEditListener

class MainActivity : AppCompatActivity(), TextEditListener {
    private val counter = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnLoad = findViewById<View?>(R.id.loadBtn) as Button?
        val listener = View.OnClickListener {
            //
// Create a new dynamic fragment on every button push.
//
            val fragmentManager = fragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            val dynamicFragment = DynamicFragment()
            fragmentTransaction.add(R.id.fragmentContainer, dynamicFragment, "DYNAMIC")
            fragmentTransaction.commit()
            //
// Create a new dynamic fragment and replace the existing one
// on every button push.
//
//                // Create new fragment and transaction
//                DynamicFragment dynamicFragment = new DynamicFragment();
//                FragmentTransaction transaction = getFragmentManager().beginTransaction();
//
//                // Replace whatever is in the fragmentContainer view with this fragment,
//                // and add the transaction to the back stack if needed
//                transaction.replace(R.id.fragmentContainer, dynamicFragment);
//                transaction.addToBackStack(null);
//
//                // Commit the transaction!
//                transaction.commit();
//
// Create a new dynamic fragment if one can't be found, but if we can find one -
// just change its text.
//
//                FragmentManager fragmentManager = getFragmentManager();
//
//                DynamicFragment dynamicFragment = (DynamicFragment)fragmentManager.findFragmentByTag("DYNAMIC");
//
//                if(dynamicFragment == null) {
//
//                    // We couldn't find it - so create a new one!
//                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//
//                    dynamicFragment = new DynamicFragment();
//                    fragmentTransaction.add(R.id.fragmentContainer, dynamicFragment, "DYNAMIC");
//                    fragmentTransaction.commit();
//
//                } else {
//
//                    // We did find it - so just change the existing one!
//                    ++counter;
//                    dynamicFragment.setText("Change to exisiting fragment: " + String.valueOf(counter));
//                }
        }
        btnLoad.setOnClickListener(listener)
    }

    override fun onTextEditButtonClick(fontSize: Int, text: String?) { // If the TextEditFragment, which our layout is using, has its button clicked,
// this method will be called and we can then get access to the other fragment
// of type TextFragment, so we can change its text properties.
        val textFragment = supportFragmentManager.findFragmentById(R.id.textFragment) as TextFragment?
        textFragment.changeTextProperties(fontSize, text)
    }
}