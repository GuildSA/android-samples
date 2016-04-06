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
    public void onTextEditButtonClick(int fontsize, String text) {

        TextFragment textFragment = (TextFragment) getSupportFragmentManager().findFragmentById(R.id.textFragment);

        textFragment.changeTextProperties(fontsize, text);
    }
}
