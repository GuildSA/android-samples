package org.guildsa.activitynavigation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate( Bundle savedInstanceState ) {

		super.onCreate( savedInstanceState );
		setContentView( R.layout.activity_second );

		TextView userNameText = (TextView) findViewById( R.id.user_name );
		TextView passwordText = (TextView) findViewById( R.id.password );
		Button closeBtn = (Button) findViewById( R.id.close );

		Intent intent = getIntent();

		// Get the data passed to us.
		String userName = intent.getStringExtra( "userName" );
		String password = intent.getStringExtra( "password" );

		// Displaying received data.
		userNameText.setText( userName );
		passwordText.setText( password );

		// Binding Click event to Button
		closeBtn.setOnClickListener( new View.OnClickListener() {

			public void onClick( View arg0 ) {

				// Package up some data and set a result to send back 
				// to the Activity that called us and close this Activity.
				
				Intent resultIntent = new Intent();
				
				// Let's pretend that we logged into the player's account and 
				// got their screen name and current high score.
				resultIntent.putExtra( "screenName", "c1pher" );
				resultIntent.putExtra( "highScore", 12456 );
				
				setResult( Activity.RESULT_OK, resultIntent );

				finish();
			}
		} );
	}
}
