package org.guildsa.activityanimation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class SecondActivity extends AppCompatActivity {

	@Override
	public void onCreate( Bundle savedInstanceState ) {

		super.onCreate( savedInstanceState );
		setContentView( R.layout.activity_second );

        //
        // The MainActivity will hold or stay in place while the SecondActivity slides out
        // to the right side revealing the MainActivity, which we know should be there already.
        //

        Button finsihWithSlideOutBtn = (Button) findViewById( R.id.finsihWithSlideOutBtn );
        finsihWithSlideOutBtn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {

                finish();

                // Specify what the enter animation should be for the new Activity and
                // what the exit animation should be for the current Activity.
                overridePendingTransition(R.anim.hold, R.anim.slide_out_right);
            }
        });

        //
        // The MainActivity will be pulled in from the left side while the SecondActivity is pushed
        // out to the right side.
        //

        Button finsihWithPullOutBtn = (Button) findViewById( R.id.finsihWithPullOutBtn );
        finsihWithPullOutBtn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {

                finish();

                // Specify what the enter animation should be for the new Activity and
                // what the exit animation should be for the current Activity.
                overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
            }
        });

        //
        // The MainActivity will fade in while the SecondActivity fades out.
        //

		Button finishWithfadeBtn = (Button) findViewById( R.id.finishWithfadeBtn );
        finishWithfadeBtn.setOnClickListener( new View.OnClickListener() {

			public void onClick( View arg0 ) {

				finish();

                // Specify what the enter animation should be for the new Activity and
                // what the exit animation should be for the current Activity.
				overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
			}
		} );
	}
}
