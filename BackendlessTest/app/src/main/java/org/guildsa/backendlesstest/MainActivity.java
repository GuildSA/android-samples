package org.guildsa.backendlesstest;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.async.callback.BackendlessCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.local.UserTokenStorageFactory;

import java.util.List;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private final static String TAG = MainActivity.class.getSimpleName();

    // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    // Replace these with YOUR App's ID and Secret Key from YOUR Backendless Dashboard!
    // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    private final static String APP_ID = "<replace-with-your-app-id>";
    private final static String API_KEY = "<replace-with-your-api-key>";

    private final static String USER_NAME = "android_user@gmail.com";
    private final static String PASSWORD = "password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(APP_ID != "<replace-with-your-app-id>" && API_KEY != "<replace-with-your-api-key>") {

            Backendless.initApp(this, APP_ID, API_KEY);

        } else {

            AlertDialog.Builder alert = new AlertDialog.Builder( this );
            alert.setTitle( "Backendless Error" )
                    .setMessage( "To use this sample you must register with Backendless, create an app, and replace the APP_ID and SECRET_KEY in the MainActivity with the values from your app's settings." )
                    .setNeutralButton( "OK", null )
                    .show();
        }
    }

    public void onRegisterUserBtn(View v) {

        Log.d(TAG, "onRegisterUserBtn() called!");

        // In a real app, this where we would send the user to a register screen to collect their
        // user name and password for registering a new user. For testing purposes, we will simply
        // register a test user using a hard coded user name and password.
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

        // First, check if the user is already logged in. If they are, we don't need to
        // ask them to login again.
        String userToken = UserTokenStorageFactory.instance().getStorage().get();

        if(userToken != null && !userToken.equals("")) {

            // We found a cached user token so we know for sure the user is already logged!
            Log.d(TAG, "User is already logged with token: " + userToken.toString());

        } else {

            // If we were unable to find a cached user token, the user is not logged and they'll
            // need to login. In a real app, this where we would send the user to a login screen to
            // collect their user name and password for the login attempt. For testing purposes,
            // we will simply login a test user using a hard coded user name and password.

            // Please note how we are passing 'true' for the last argument 'stayLoggedIn'.
            // This asks that the user should stay logged in by storing or caching the user's login
            // information so future logins can be skipped next time the user launches the app.
            Backendless.UserService.login(USER_NAME, PASSWORD, new AsyncCallback<BackendlessUser>() {

                public void handleResponse(BackendlessUser user) {

                    Log.d(TAG, "User logged in: " + user.getUserId());
                }

                public void handleFault(BackendlessFault fault) {

                    Log.d(TAG, "User failed to login: " + fault.toString());
                }
            }, true);

        }
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

        Backendless.Data.of(Comment.class).find(new AsyncCallback<List<Comment>>(){

            @Override
            public void handleResponse(List<Comment> foundComments) {

                Log.i(TAG, "Comments have been fetched:");

                for(Comment comment : foundComments) {
                    Log.i(TAG, "Comment: " + comment.getObjectId() + ", message: " + comment.getMessage());
                }
            }

            @Override
            public void handleFault(BackendlessFault fault) {

                Log.i(TAG, "Comments were not fetched: " + fault.toString());
            }
        });
    }

    public void onRemoveCommentsBtn(View v) {

        Backendless.Data.of(Comment.class).find(new AsyncCallback<List<Comment>>() {

            @Override
            public void handleResponse(List<Comment> foundComments) {

                Log.i(TAG, "Comments have been fetched:");

                for(Comment comment : foundComments) {

                    Log.i(TAG, "Comment: " + comment.getObjectId() + ", message: " + comment.getMessage());

                    Log.i(TAG, "Remove Comment: " + comment.getObjectId());

                    // Now, Asynchronously remove this Comment!
                    Backendless.Persistence.of(Comment.class).remove( comment, new AsyncCallback<Long>() {

                        public void handleResponse(Long response) {
                            // Comment has been deleted. The response is a time in milliseconds when the object was deleted
                            Log.i(TAG, "One Comment has been removed: " + response);
                        }

                        public void handleFault(BackendlessFault fault) {
                            // An error has occurred, the error code can be retrieved with fault.getCode()
                            Log.i(TAG, "Server reported an error on attempted removal: " + fault);
                        }
                    });
                }
            }

            @Override
            public void handleFault(BackendlessFault fault) {

                Log.i(TAG, "Comments were not fetched: " + fault.toString());
            }
        });
    }
}
