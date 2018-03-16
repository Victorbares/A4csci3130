package com.acme.a3csci3130;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertEquals;

/**
 * Created by vibar on 14/03/2018.
 */

@RunWith(AndroidJUnit4.class)
public class CRUD_Test {
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule(MainActivity.class);
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.acme.a3csci3130", appContext.getPackageName());
    }

    /**
     * Test to create a bussness contact
     */
    @Test
    public void testCreate() {



        onView(withId(R.id.submitButton)).perform(click());
        onView(withId(R.id.name)).perform(typeText("Testname"));
        onView(withId(R.id.bussnumber)).perform(typeText("111222333"));
        //onView(withId(R.id.primbus)).perform();
        onView(withId(R.id.addr)).perform(typeText("Test address"));
        onView(withId(R.id.proTerr)).perform(typeText("AB"),closeSoftKeyboard());

        onView(withId(R.id.submitButton)).perform(click());
        //onView(withId(R.id.listView)).matches('onView(withParent(withId(R.id.exerciseLinearLayout))).check(matches(withText("Run")));')
        onView(withParent(withId(R.id.listView))).check(matches(withText("Testname")));
    }

    /**
     * Test to update an user contact need to have to other business contact before running this test.
     */
    @Test
    public void testUpdate() {



//        onView(withId(R.id.submitButton)).perform(click());
//        onView(withId(R.id.name)).perform(typeText("Testname"));
//        onView(withId(R.id.bussnumber)).perform(typeText("111222333"));
//        //onView(withId(R.id.primbus)).perform();
//        onView(withId(R.id.addr)).perform(typeText("Test address"));
//        onView(withId(R.id.proTerr)).perform(typeText("AB"),closeSoftKeyboard());
//
//        onView(withId(R.id.submitButton)).perform(click());
//        //onView(withId(R.id.listView)).matches('onView(withParent(withId(R.id.exerciseLinearLayout))).check(matches(withText("Run")));')
//        onView(withParent(withId(R.id.listView))).check(matches(withText("Testname")));

        onView(withParent(withId(R.id.listView))).perform(click());
        onView(withId(R.id.name)).perform(typeText("new"));
        onView(withId(R.id.updateButton)).perform(click());
        onView(withParent(withId(R.id.listView))).check(matches(withText("Testnamenew")));
    }

    /**
     * Test to delete a contact
     */
    @Test
    public void testDelete() {
        onView(withParent(withId(R.id.listView))).check(matches(withText("Testnamenew")));
        onView(withParent(withId(R.id.listView))).perform(click());
        onView(withId(R.id.deleteButton)).perform(click());
    }
}
