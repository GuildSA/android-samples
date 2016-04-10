package org.guildsa.basictesting;

import android.content.Intent;
import android.support.v7.view.ContextThemeWrapper;
import android.test.ActivityUnitTestCase;
import android.test.suitebuilder.annotation.SmallTest;
import android.widget.TextView;

/**
 *  To test one of our Activities such as MainActivity, use the ActivityUnitTestCase class to
 *  create an Android Instrumented Unit Test that must run on an actual Android device or
 *  simulator to be tested correctly.
 */
public class MainActivityTest extends ActivityUnitTestCase<MainActivity> {

    // The Activity we're testing!
    MainActivity mainActivity;

    // One of the TextView(s) of the MainActivity to be tested.
    TextView textView;

    public MainActivityTest() {
        super(MainActivity.class);
    }

    @Override
    protected void setUp() throws Exception {

        super.setUp();

        // We need to use ContextThemeWrapper to wrap or apply the actual theme that our
        // MainActivity uses or we get an error that says this:
        // "You need to use a Theme.AppCompat theme (or descendant) with this activity."
        ContextThemeWrapper context = new ContextThemeWrapper(getInstrumentation().getTargetContext(), R.style.AppTheme);
        setActivityContext(context);

        // Start the MainActivity of the our application.
        startActivity(new Intent(getInstrumentation().getTargetContext(), MainActivity.class), null, null);

        // Cache off a reference to the MainActivity of our application.
        mainActivity = getActivity();

        // Get a reference to the TextView of the MainActivity.
        textView = (TextView) mainActivity.findViewById(R.id.textView);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    @SmallTest
    public void test_mainActivity_textView_isCorrect() throws Exception {

        // Get the actual text out of textView.
        String actual = textView.getText().toString();

        // This is the text we expect to find in the textView.
        String expected = "Hello World!";

        // Check whether both are equal, otherwise test fails!
        assertEquals(expected, actual);
    }
}