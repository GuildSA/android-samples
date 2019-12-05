package org.guildsa.backendlesstest

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.backendless.Backendless
import com.backendless.BackendlessUser
import com.backendless.async.callback.AsyncCallback
import com.backendless.async.callback.BackendlessCallback
import com.backendless.exceptions.BackendlessFault
import com.backendless.persistence.local.UserTokenStorageFactory


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(APP_ID !== "<replace-with-your-app-id>" && API_KEY !== "<replace-with-your-api-key>") {

            Backendless.initApp(this, APP_ID, API_KEY)
        } else {

            val alert = AlertDialog.Builder(this)

            alert.setTitle("Backendless Error")
                    .setMessage("To use this sample you must register with Backendless, create an app, and replace the APP_ID and SECRET_KEY in the MainActivity with the values from your app's settings.")
                    .setNeutralButton("OK", null)
                    .show()
        }
    }

    fun onRegisterUserBtn(v: View?) {

        Log.d(TAG, "onRegisterUserBtn() called!")

        // In a real app, this where we would send the user to a register screen to collect their
        // user name and password for registering a new user. For testing purposes, we will simply
        // register a test user using a hard coded user name and password.
        val user = BackendlessUser()
        user.email = USER_NAME
        user.password = PASSWORD

        Backendless.UserService.register(user, object : BackendlessCallback<BackendlessUser?>() {

            override fun handleResponse(backendlessUser: BackendlessUser?) {
                Log.d(TAG, "User was registered: " + backendlessUser?.objectId)
            }

            override fun handleFault(fault: BackendlessFault?) {
                Log.d(TAG, "User failed to register: " + fault.toString())
            }
        })
    }

    fun onLoginUserBtn(v: View?) {

        Log.d(TAG, "onLoginUserBtn() called!")

        // First, check if the user is already logged in. If they are, we don't need to
        // ask them to login again.
        val userToken = UserTokenStorageFactory.instance().storage.get()

        if(userToken != null && userToken != "") {

            // We found a cached user token so we know for sure the user is already logged!
            Log.d(TAG, "User is already logged with token: $userToken")

        } else {

            // If we were unable to find a cached user token, the user is not logged and they'll
            // need to login. In a real app, this where we would send the user to a login screen to
            // collect their user name and password for the login attempt. For testing purposes,
            // we will simply login a test user using a hard coded user name and password.
            // Please note how we are passing 'true' for the last argument 'stayLoggedIn'.
            // This asks that the user should stay logged in by storing or caching the user's login
            // information so future logins can be skipped next time the user launches the app.
            Backendless.UserService.login(USER_NAME, PASSWORD, object : AsyncCallback<BackendlessUser?> {

                override fun handleResponse(user: BackendlessUser?) {
                    Log.d(TAG, "User logged in: " + user?.userId)
                }

                override fun handleFault(fault: BackendlessFault?) {
                    Log.d(TAG, "User failed to login: " + fault.toString())
                }
            }, true)
        }
    }

    fun onCreateCommentBtn(v: View?) {

        Log.d(TAG, "onCreateCommentBtn() called!")

       val comment = Comment(null, "Hello, from Android user!", USER_NAME)

        Backendless.Persistence.save(comment, object : AsyncCallback<Comment?> {

            override fun handleResponse(comment: Comment?) {
                Log.i(TAG, "Comment was saved: " + comment?.objectId + ", message: " + comment?.message)
            }

            override fun handleFault(fault: BackendlessFault?) {
                Log.i(TAG, "Comment failed to save: " + fault.toString())
            }
        })
    }

    fun onFetchCommentsBtn(v: View?) {

        Backendless.Data.of(Comment::class.java).find(object : AsyncCallback<List<Comment>> {

            override fun handleResponse(foundComments: List<Comment>) {

                Log.i(TAG, "Comments have been fetched:")

                for(comment in foundComments) {
                    Log.i(TAG, "Comment: " + comment.objectId + ", message: " + comment.message)
                }
            }

            override fun handleFault(fault: BackendlessFault?) {
                Log.i(TAG, "Comments were not fetched: " + fault.toString())
            }
        })
    }

    fun onRemoveCommentsBtn(v: View?) {

        Backendless.Data.of(Comment::class.java).find(object : AsyncCallback<List<Comment>> {

            override fun handleResponse(foundComments: List<Comment>) {

                Log.i(TAG, "Comments have been fetched:")

                for(comment in foundComments) {

                    Log.i(TAG, "Comment: " + comment.objectId + ", message: " + comment.message)
                    Log.i(TAG, "Remove Comment: " + comment.objectId)

                    // Now, Asynchronously remove this Comment!
                    Backendless.Persistence.of(Comment::class.java).remove(comment, object : AsyncCallback<Long> {

                        override fun handleResponse(response: Long) { // Comment has been deleted. The response is a time in milliseconds when the object was deleted
                            Log.i(TAG, "One Comment has been removed: $response")
                        }

                        override fun handleFault(fault: BackendlessFault?) { // An error has occurred, the error code can be retrieved with fault.getCode()
                            Log.i(TAG, "Server reported an error on attempted removal: $fault")
                        }
                    })
                }
            }

            override fun handleFault(fault: BackendlessFault?) {
                Log.i(TAG, "Comments were not fetched: " + fault.toString())
            }
        })
    }

    companion object {

        private val TAG = MainActivity::class.java.simpleName

        // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        // Replace these with YOUR App's ID and Secret Key from YOUR Backendless Dashboard!
        // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        private val APP_ID: String? = "<replace-with-your-app-id>"
        private val API_KEY: String? = "<replace-with-your-api-key>"

        private val USER_NAME: String? = "android_user@gmail.com"
        private val PASSWORD: String? = "password"
    }
}