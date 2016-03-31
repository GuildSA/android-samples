package org.guildsa.basictesting;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.test.ApplicationTestCase;
import android.test.MoreAsserts;
import android.test.suitebuilder.annotation.SmallTest;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 *
 *  To test our Application, use the ApplicationTestCase class to create an
 *  Android Instrumented Unit Test that must run on an actual Android device or simulator to
 *  be tested correctly.
 */
public class ApplicationTest extends ApplicationTestCase<Application> {

    private Application application;

    public ApplicationTest() {
        super(Application.class);
    }

    @Override
    protected void setUp() throws Exception {

        createApplication();

        application = getApplication();
    }

    @SmallTest
    public void application_version_isCorrect() throws Exception {

        PackageInfo info = application.getPackageManager().getPackageInfo(application.getPackageName(), 0);

        // Assert if info is null!
        assertNotNull(info);

        // Assert if info.versionName is not "1.0"!
        assertEquals("1.0", info.versionName);

        // Assert if info.versionCode is not 1!
        assertEquals(1, info.versionCode);

        // Assert if info.versionName is not of the form X.X where the X's are both digits.
        MoreAsserts.assertMatchesRegex("\\d\\.\\d", info.versionName);
    }
}