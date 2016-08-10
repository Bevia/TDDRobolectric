package com.corebaseit.tddrobolectric;

import android.app.Activity;
import android.content.Intent;
import android.widget.Button;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

/**
 * by: Vincent Bevia
 */

/**
 @BeforeClass – Run once before any of the test methods in the class, public static void
 @AfterClass – Run once after all the tests in the class have been run, public static void
 @Before – Run before @Test, public void
 @After – Run after @Test, public void
 @Test – This is the test method to run, public void
 */

/**
 Robolectric 3.1.1 Changes

 RoboAttributeSet is deprecated and uses should be replaced with Robolectric.buildAttributeSet().
 RobolectricGradleTestRunner is deprecated and uses should be replaced with RobolectricTestRunner.
 */

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 22)

public class TDDRobolectricUnitTest {

    private MainActivity mainActivity;
    private WelcomeActivity welcomeActivity;
    private LoginActivity loginActivity;
    private OnActivityResultNameActivity onActivityResultNameActivity;

    // Run once, e.g. Database connection, connection pool
    @BeforeClass
    public static void runOnceBeforeClass() {
        System.out.println("@BeforeClass - runOnceBeforeClass");
    }

    @Before
    public void setUp() throws Exception {

        mainActivity = Robolectric.buildActivity(MainActivity.class)
                .create()
                .resume()
                .get();

        welcomeActivity = Robolectric.buildActivity(WelcomeActivity.class)
                .create()
                .resume()
                .get();

        loginActivity = Robolectric.buildActivity(LoginActivity.class)
                .create()
                .resume()
                .get();

        onActivityResultNameActivity = Robolectric.buildActivity(OnActivityResultNameActivity.class)
                .create()
                .resume()
                .get();

    }

    @Test
    public void seMainActivityshouldNotBeNull() throws Exception {
        assertNotNull(mainActivity);
        System.out.println("@Test - mainActivity notNull");
    }

    @Test
    public void setLoginActivityshouldNotBeNull() throws Exception {
        assertNotNull(loginActivity);
        System.out.println("@Test - loginActivity notNull");
    }

    @Test
    public void setWelcomeActivityshouldNotBeNull() throws Exception {
        assertNotNull(welcomeActivity);
        System.out.println("@Test - welcomeActivity notNull");
    }

    @Test
    public void setOnActivityResultNameActivityshouldNotBeNull() throws Exception {
        assertNotNull(onActivityResultNameActivity);
        System.out.println("@Test - onActivityResultNameActivity notNull");
    }


    // Run test:  startActivity from MainActivity to WelcomeActivity!!
    @Test
    public void buttonClickShouldStartWelcomeActivity() throws Exception {
        Button button = (Button) mainActivity.findViewById(R.id.login);
        button.performClick();

        Intent intent = Shadows.shadowOf(mainActivity).peekNextStartedActivity();
        assertEquals(WelcomeActivity.class.getCanonicalName(), intent.getComponent().getClassName());

        System.out.println("@Test - Start WelcomeActivity");
    }

    // Run test:  startActivity from WelcomeActivity to LoginActivity!!
    @Test
    public void buttonClickShouldStartLoginActivity() throws Exception {
        Button button = (Button) welcomeActivity.findViewById(R.id.login);
        button.performClick();

        Intent intent = Shadows.shadowOf(welcomeActivity).peekNextStartedActivity();
        assertEquals(LoginActivity.class.getCanonicalName(), intent.getComponent().getClassName());

        System.out.println("@Test - Start LoginActivity");
    }

    /**
     *
     * TDD testing for startActivityForResult on MainActivity - Response from OnActivityResultNameActivity
     * @throws Exception
     *
     */

    // Run test: startActivityForResult from MainActivity to OnActivityResultNameActivity and back!!!
    @Test
    public void shouldFillNameViewFromChooserResponse() throws Exception {
        mainActivity.askForUserName();

        Shadows.shadowOf(mainActivity).receiveResult(
                new Intent(mainActivity, OnActivityResultNameActivity.class),
                Activity.RESULT_OK,
                new Intent().putExtra("result", "Vincent Bevia"));

        assertEquals("Vincent Bevia", mainActivity.nameView.getText());

        System.out.println("@Test - startActivityForResult-RESULT_OK");
    }

    //Run test: testing the RESULT_CANCELLED branch of our code
    @Test
    public void shouldDisplayErrorMessageInNameViewWhenUserCancels() throws Exception {
        mainActivity.askForUserName();

        Shadows.shadowOf(mainActivity).receiveResult(
                new Intent(mainActivity, OnActivityResultNameActivity.class),
                Activity.RESULT_CANCELED,
                null);

        assertEquals("No name given.", mainActivity.nameView.getText());

        System.out.println("@Test - startActivityForResult-RESULT_CANCELLED");
    }

    // Run once, e.g close connection, cleanup
    @AfterClass
    public static void runOnceAfterClass() {
        System.out.println("@AfterClass - runOnceAfterClass");
    }
}