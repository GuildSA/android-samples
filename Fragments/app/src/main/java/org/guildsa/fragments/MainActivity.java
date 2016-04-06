package org.guildsa.fragments;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements TextEditFragment.TextEditListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
