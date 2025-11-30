package com.example.coursework2;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;

@RunWith(AndroidJUnit4.class)
public class SavedMoviesActivityTest {

    @Rule
    public ActivityScenarioRule<SavedMovieActivity> activityRule =
            new ActivityScenarioRule<>(SavedMovieActivity.class);

    @Test
    public void testListViewIsDisplayed() {
        onView(withId(R.id.listViewSaved)).check(matches(isDisplayed()));
    }

    @Test
    public void testBackButtonNavigates() {
        // Click the back button
        onView(withId(R.id.ivBack2)).perform(click());

        // Optionally, check that the main activity is displayed by verifying a known view
        onView(withId(R.id.btnMovieSearch)).check(matches(isDisplayed()));
    }
}
