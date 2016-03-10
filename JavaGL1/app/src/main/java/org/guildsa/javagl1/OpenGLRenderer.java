package org.guildsa.javagl1;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.graphics.BitmapFactory;
import android.app.Activity;
import android.opengl.GLU;
import android.opengl.GLSurfaceView.Renderer;

public class OpenGLRenderer implements Renderer {

	private Quad quad;
	private float angle = 0;

	public OpenGLRenderer( Activity activity ) {

		// Initialize our textured quad.
		quad = new Quad();

		// Load a texture.
		quad.loadBitmap( BitmapFactory.decodeResource( activity.getResources(), R.drawable.android ) );
	}

	public void onSurfaceCreated( GL10 gl, EGLConfig config ) {

		// Set the background color to black ( RGBA ).
		gl.glClearColor( 0.0f, 0.0f, 0.0f, 1.0f );
	}

	public void onSurfaceChanged( GL10 gl, int width, int height ) {

		// Sets the current view port to the new size.
		gl.glViewport( 0, 0, width, height );

		// Select the projection matrix
		gl.glMatrixMode( GL10.GL_PROJECTION );

		// Reset the projection matrix
		gl.glLoadIdentity();

		// Calculate the aspect ratio of the window
		GLU.gluPerspective( gl, 45.0f, (float)width / (float)height, 0.1f, 100.0f );

		// Select the model-view matrix
		gl.glMatrixMode( GL10.GL_MODELVIEW );

		// Reset the model-view matrix
		gl.glLoadIdentity();
	}
	
	public void onDrawFrame( GL10 gl ) {

		// Clears the screen and depth buffer.
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		// Replace the current matrix with the identity matrix
		gl.glLoadIdentity();

		// Translates the quad 6 units into the screen.
		gl.glTranslatef(0.0f, 0.0f, -6.0f);

        // Rotate about the X axis.
        //gl.glRotatef(angle, 1.0f, 0.0f, 0.0f);

        // Rotate about the Y axis.
        //gl.glRotatef(angle, 0.0f, 1.0f, 0.0f);

		// Rotate about the Z axis.
		gl.glRotatef( angle, 0.0f, 0.0f, 1.0f );

		// Draw our textured quad.
		quad.draw( gl );

		// Increase the angle.
		angle++;
	}
}
