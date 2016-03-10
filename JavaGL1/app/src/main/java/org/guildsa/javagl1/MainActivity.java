package org.guildsa.javagl1;

import android.opengl.GLSurfaceView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate( savedInstanceState );
        //setContentView(R.layout.activity_main);

        GLSurfaceView glSurfaceView = new GLSurfaceView(this);

        OpenGLRenderer glRenderer = new OpenGLRenderer(this);
        glSurfaceView.setRenderer( glRenderer );

        setContentView( glSurfaceView );
    }
}
