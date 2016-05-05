package org.guildsa.fragments;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements TextEditFragment.TextEditListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnLoad = (Button) findViewById(R.id.loadBtn);

        View.OnClickListener listener = new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                //
                // Create a new dynamic fragment on every button push.
                //

                FragmentManager fragmentManager = getFragmentManager();

                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                DynamicFragment dynamicFragment = new DynamicFragment();
                fragmentTransaction.add(R.id.fragmentContainer, dynamicFragment, "DYNAMIC");
                fragmentTransaction.commit();

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
            }
        };

        btnLoad.setOnClickListener(listener);
    }

    @Override
    public void onTextEditButtonClick(int fontSize, String text) {

        // If the TextEditFragment, which our layout is using, has its button clicked,
        // this method will be called and we can then get access to the other fragment
        // of type TextFragment, so we can change its text properties.
        TextFragment textFragment = (TextFragment) getSupportFragmentManager().findFragmentById(R.id.textFragment);

        textFragment.changeTextProperties(fontSize, text);
    }
}
