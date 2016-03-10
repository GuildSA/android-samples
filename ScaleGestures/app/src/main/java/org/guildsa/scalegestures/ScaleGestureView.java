package org.guildsa.scalegestures;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;

public class ScaleGestureView extends View {

	private static final String TAG = "ScaleGestureView";
	
	private static final int INVALID_POINTER_ID = -1;

	private Drawable drawable;
	private float posX;
	private float posY;

	private float lastTouchX;
	private float lastTouchY;
	private int activePointerId = INVALID_POINTER_ID;

	private ScaleGestureDetector scaleDetector;
	private float scaleFactor = 1.0f;

	public ScaleGestureView(Context context) {

		this( context, null, 0 );
	}

	public ScaleGestureView(Context context, AttributeSet attrs) {

		this( context, attrs, 0 );
	}

	public ScaleGestureView(Context context, AttributeSet attrs, int defStyle) {

		super( context, attrs, defStyle );
		
		drawable = context.getResources().getDrawable( R.drawable.android_logo );
		drawable.setBounds( 0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight() );
		scaleDetector = new ScaleGestureDetector( context, new ScaleListener() );
		
		//setBackgroundColor( Color.WHITE );
	}

	@Override
	public boolean onTouchEvent( MotionEvent ev ) {

		// Let the ScaleGestureDetector inspect all events.
		scaleDetector.onTouchEvent( ev );

		final int action = ev.getAction();
		
		switch( action & MotionEvent.ACTION_MASK ) {

			case MotionEvent.ACTION_DOWN: {
				// A single finger has touched the screen...

				final int pointerIndex = ev.getPointerId( 0 );
				final float x = ev.getX();
				final float y = ev.getY();

				Log.d( TAG, "ACTION_DOWN index = " + pointerIndex + ", x = " + x + ", " + y );
				
				lastTouchX = x;
				lastTouchY = y;
				activePointerId = pointerIndex;
			}
			break;

			case MotionEvent.ACTION_POINTER_DOWN: {
				// Another finger has touched the screen...
				
				Log.d( TAG, "ACTION_POINTER_DOWN" );
			}
			break;
			
			case MotionEvent.ACTION_MOVE: {
				// A finger or fingers have moved on the screen...

				Log.d( TAG, "ACTION_POINTER_DOWN all:" );
				
				//
				// Output all fingers...
				//
				
				StringBuilder sb = new StringBuilder();
				sb.append( "    " );

			    for( int i = 0; i < ev.getPointerCount(); ++i ) {

                    sb.append( "(pid " ).append( ev.getPointerId(i) );
                    sb.append( "," ).append( (int) ev.getX(i) );
                    sb.append( "," ).append( (int) ev.getY(i) );
                    sb.append( ") " );
			    }

			    Log.d( TAG, sb.toString() );

			    //
			    // Now, get the primary finger that we are tracking...
			    //
			    
				final int pointerIndex = ev.findPointerIndex( activePointerId );
				final float x = ev.getX( pointerIndex );
				final float y = ev.getY( pointerIndex );

				Log.d( TAG, "ACTION_POINTER_DOWN index = " + pointerIndex + ", x = " + x + ", " + y );
				
				// Only move if the ScaleGestureDetector isn't processing a gesture.
				if( !scaleDetector.isInProgress() ) {

					final float dx = x - lastTouchX;
					final float dy = y - lastTouchY;

					posX += dx;
					posY += dy;

					invalidate();
				}

				lastTouchX = x;
				lastTouchY = y;
			}
			break;

			case MotionEvent.ACTION_UP: {
				// The last finger has left the screen...

				Log.d( TAG, "ACTION_UP" );
				
				activePointerId = INVALID_POINTER_ID;
			}
			break;

			case MotionEvent.ACTION_POINTER_UP: {

				final int pointerIndex = (ev.getAction() & MotionEvent.ACTION_POINTER_INDEX_MASK) >> MotionEvent.ACTION_POINTER_INDEX_SHIFT;
				final int pointerId = ev.getPointerId( pointerIndex );

				final float x = ev.getX( pointerIndex );
				final float y = ev.getY( pointerIndex );
				
				Log.d( TAG, "ACTION_POINTER_UP index = " + pointerIndex + ", x = " + x + ", " + y );
				
				if( pointerId == activePointerId ) {

					// If we're losing the active pointer we were tracking, we will need to choose a new
					// active pointer and adjust accordingly.
					final int newPointerIndex = pointerIndex == 0 ? 1 : 0;
					lastTouchX = x;
					lastTouchY = y;
					activePointerId = ev.getPointerId( newPointerIndex );
				}
			}
			break;
			
			case MotionEvent.ACTION_CANCEL: {

				Log.d( TAG, "ACTION_CANCEL" );
				
				// All fingers have left the screen! The current gesture has been canceled.
				activePointerId = INVALID_POINTER_ID;
			}
			break;
		}

		// Tell the system that we handled the event and no further processing is required.
		return true;
	}

	@Override
	public void onDraw( Canvas canvas ) {

		super.onDraw( canvas );

		canvas.save();
		canvas.translate( posX, posY );
		canvas.scale( scaleFactor, scaleFactor );
		drawable.draw( canvas );
		canvas.restore();
	}

	private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {

		@Override
		public boolean onScale( ScaleGestureDetector detector ) {

			scaleFactor *= detector.getScaleFactor();

			// Don't let the object get too small or too large.
			scaleFactor = Math.max( 0.1f, Math.min( scaleFactor, 5.0f ) );

			invalidate();
			return true;
		}
	}
}
