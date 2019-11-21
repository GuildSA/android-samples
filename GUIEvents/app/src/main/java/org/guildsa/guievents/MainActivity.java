package org.guildsa.guievents;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // If we want to hang on to certain UI widgets for a while, we can create
    // Instance Variables to hold them.
    ToggleButton toggleButton;
    TextView textView;

    /** Called when the activity is first created. */
    @Override
    public void onCreate( Bundle savedInstanceState ) {

        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        // Wire up button 1 the manual way using its id.
        // The id "R.id.button1" will not exist until after a built since
        // it lives in the auto-generated file /gen/org.guildsa.R.java
        final Button button = (Button) findViewById( R.id.button1 );

        button.setOnClickListener( new View.OnClickListener() {

            public void onClick( View v ) {
                showAlert( "This is button1!" );
            }
        });

        // Wire up check-box 1 the manual way using its id.
        final CheckBox checkBox = (CheckBox) findViewById( R.id.checkBox1 );

        checkBox.setOnCheckedChangeListener( new OnCheckedChangeListener() {

            public void onCheckedChanged( CompoundButton buttonView, boolean isChecked ) {

                TextView txt = (TextView) findViewById( R.id.textView1 );

                if(isChecked) {
                    txt.setText("Checked");
                } else {
                    txt.setText("Unchecked");
                }
            }
        });

        // Initialize our instance variables for the TextView and the ToggleButton
        // so we only have to call findViewById once for each.
        textView = (TextView) findViewById( R.id.textView2 );
        textView.setBackgroundColor( 0xfff00000 );

        toggleButton = (ToggleButton) findViewById( R.id.toggleButton1 );
        toggleButton.setOnClickListener( this );
    }

    // Wire up button 2 through the Layout Editor by specifying the method's name.
    // Look in /res/layout/main.xml
    public void onClickButton2( View v ) {
        showAlert( "This is button2!" );
    }

    //
    // Our Activity class implements View.OnClickListener, so we can
    // override onClick from View.OnClickListener and listen for click
    // and check for events such as whether or not our toggle button has
    // changed.
    @Override
    public void onClick( View v ) {

        if( toggleButton.isChecked() ) {

            textView.setText( "Toggle On" );
            textView.setBackgroundColor( Color.GREEN );
            textView.setTextColor( Color.BLACK );

        } else {

            textView.setText("Toggle Off");
            textView.setBackgroundColor( Color.RED );
            textView.setTextColor( Color.WHITE );
        }
    }

    // This is a simple method that shows an AlertDialog for testing purposes.
    public void showAlert( String message ) {

        AlertDialog.Builder alert = new AlertDialog.Builder( this );
        alert.setTitle( "Alert!" ).setMessage( message ).setNeutralButton( "OK", null ).show();
    }
}
