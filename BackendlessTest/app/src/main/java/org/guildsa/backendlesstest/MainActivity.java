package org.guildsa.backendlesstest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.async.callback.BackendlessCallback;
import com.backendless.exceptions.BackendlessFault;

public class MainActivity extends AppCompatActivity {

    private final static String TAG = MainActivity.class.getSimpleName();

    private final static String APP_VERSION = "v1";

    // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    // Replace these with YOUR App's ID and Secret Key from YOUR Backendless Dashboard!
    // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    private final static String APP_ID = "761FB252-4CE5-99EF-FF77-C9E99ACAA400";
    private final static String SECRET_KEY = "26833F24-B8CE-471F-FFFB-BBA7054AD900";

    private final static String USER_NAME = "android_user@gmail.com";
    private final static String PASSWORD = "password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Backendless.initApp(this, APP_ID, SECRET_KEY, APP_VERSION);
    }

    public void onCreateUserBtn(View v) {

        Log.d(TAG, "onCreateUserBtn() called!");

        BackendlessUser user = new BackendlessUser();
        user.setEmail(USER_NAME);
        user.setPassword(PASSWORD);

        Backendless.UserService.register(user, new BackendlessCallback<BackendlessUser>() {

            @Override
            public void handleResponse(BackendlessUser backendlessUser) {

                Log.d(TAG, "User was registered: " + backendlessUser.getObjectId());
            }

            @Override
            public void handleFault(BackendlessFault fault) {

                Log.d(TAG, "User failed to register: " + fault.toString());
            }
        });
    }

    public void onLoginUserBtn(View v) {

        Log.d(TAG, "onLoginUserBtn() called!");

        Backendless.UserService.login(USER_NAME, PASSWORD, new AsyncCallback<BackendlessUser>() {

            public void handleResponse(BackendlessUser user) {

                Log.d(TAG, "User logged in: " + user.getUserId());
            }

            public void handleFault(BackendlessFault fault) {

                Log.d(TAG, "User failed to login: " + fault.toString());
            }
        });
    }

    public void onCreateCommentBtn(View v) {

        Log.d(TAG, "onCreateCommentBtn() called!");

        Comment comment = new Comment( "Hello, from Android user!", USER_NAME );

        Backendless.Persistence.save(comment, new AsyncCallback<Comment>() {

            @Override
            public void handleResponse(Comment comment) {

                Log.i(TAG, "Comment was saved: " + comment.getObjectId() + ", message: " + comment.getMessage());
            }

            @Override
            public void handleFault(BackendlessFault fault) {

                Log.i(TAG, "Comment failed to save: " + fault.toString());
            }
        });
    }

    public void onFetchCommentsBtn(View v) {

        Backendless.Persistence.of(Comment.class).find(new AsyncCallback<BackendlessCollection<Comment>>(){

            @Override
            public void handleResponse( BackendlessCollection<Comment> foundComments ) {

                Log.i(TAG, "Comments have been fetched:");

                for(Comment comment : foundComments.getData()) {
                    Log.i(TAG, "Comment: " + comment.getObjectId() + ", message: " + comment.getMessage());
                }
            }

            @Override
            public void handleFault( BackendlessFault fault ) {

                Log.i(TAG, "Comments were not fetched: " + fault.toString());
            }
        });
    }
}
