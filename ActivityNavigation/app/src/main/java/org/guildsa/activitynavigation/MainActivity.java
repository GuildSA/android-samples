package org.guildsa.activitynavigation;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private EditText userNameEdit;
    private EditText passswordEdit;

    private TextView screenNameText;
    private TextView highScoreText;

    private final int MY_CHILD_ACTIVITY = 1;

    @Override
    public void onCreate( Bundle savedInstanceState ) {

        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        userNameEdit = (EditText) findViewById( R.id.user_name );
        passswordEdit = (EditText) findViewById( R.id.password );
        Button btnNextScreen = (Button) findViewById( R.id.next_screen );
        screenNameText = (TextView) findViewById( R.id.screen_name );
        highScoreText = (TextView) findViewById( R.id.high_score );

        // Listening to button event
        btnNextScreen.setOnClickListener( new View.OnClickListener() {

            public void onClick( View arg0 ) {

                // Starting a new Intent
                Intent nextScreen = new Intent( getApplicationContext(), SecondActivity.class );

                // Sending data to another Activity
                nextScreen.putExtra( "userName", userNameEdit.getText().toString() );
                nextScreen.putExtra( "password", passswordEdit.getText().toString() );

                // Start a new activity and ignore any results.
                // startActivity( nextScreen );

                // Start a new activity and catch the results.
                startActivityForResult( nextScreen, MY_CHILD_ACTIVITY );
            }
        } );
    }

    @Override
    public void onActivityResult( int requestCode, int resultCode, Intent data ) {

        super.onActivityResult( requestCode, resultCode, data );

        switch( requestCode ) {

            case MY_CHILD_ACTIVITY:

                if( resultCode == Activity.RESULT_OK ) {

                    Log.d(TAG, "Activity.RESULT_OK");

                    // Get the data passed to us.
                    String screenName = data.getStringExtra( "screenName" );
                    int highScore = data.getIntExtra( "highScore", 0 );

                    Log.d( TAG, "screenName = " + screenName );
                    Log.d( TAG, "highScore = " + String.valueOf( highScore ) );

                    screenNameText.setText("Screen Name: " + screenName);
                    highScoreText.setText( "High Score: " + String.valueOf( highScore ) );

                break;
            }
        }
    }
}
