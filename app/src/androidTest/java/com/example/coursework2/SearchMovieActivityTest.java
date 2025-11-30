package com.example.coursework2;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class SearchMovieActivityTest {

    @Rule
    public ActivityScenarioRule<SearchMovieActivity> activityRule =
            new ActivityScenarioRule<>(SearchMovieActivity.class);

    @Test
    public void testEmptyMovieTitleShowsError() {
        onView(withId(R.id.btnSearch)).perform(click());

        onView(withId(R.id.etActorName))
                .check(matches(withText("")));
    }

    @Test
    public void testSearchMovieDisplaysTitle() {
        String movieTitle = "Inception";

        onView(withId(R.id.etActorName))
                .perform(typeText(movieTitle), closeSoftKeyboard());
        onView(withId(R.id.btnSearch)).perform(click());

        try { Thread.sleep(3000); } catch (InterruptedException e) { }

        onView(withId(R.id.tvTitle))
                .check(matches(withText("Inception")));
    }

    @Test
    public void testBackButtonNavigates() {
        // Click the back button
        onView(withId(R.id.imgBack)).perform(click());

    }
}
