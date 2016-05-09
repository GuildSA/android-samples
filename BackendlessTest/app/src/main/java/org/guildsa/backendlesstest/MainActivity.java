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
import com.backendless.persistence.local.UserTokenStorageFactory;

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

    public void onRemoveCommentsBtn(View v) {

        // Find all the Comments!
        Backendless.Persistence.of(Comment.class).find(new AsyncCallback<BackendlessCollection<Comment>>(){

            @Override
            public void handleResponse( BackendlessCollection<Comment> foundComments ) {

                Log.i(TAG, "Comments have been fetched:");

                // Now, remove all the Comments we found - one by one!
                for(Comment comment : foundComments.getData()) {

                    Log.i(TAG, "Remove Comment: " + comment.getObjectId());

                    // Becareful, you cannot make Synchronous database calls on the UI thread,
                    // it has to be done on a separate thread or via the Asynchronous call.
//                    Long result = Backendless.Persistence.of( Comment.class ).remove( comment );
//
//                    if(result > 0) {
//                        Log.i(TAG, "One Comment has been removed: " + result);
//                    } else {
//                        Log.i(TAG, "Server reported an error on attempted removal: " + result);
//                    }

                    // Now, Asynchronously remove this Comment!
                    Backendless.Persistence.of( Comment.class ).remove( comment, new AsyncCallback<Long>() {

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
            public void handleFault( BackendlessFault fault ) {

                Log.i(TAG, "Comments were not fetched: " + fault.toString());
            }
        });
    }
}
