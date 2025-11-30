package com.example.coursework2;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void testMovieSearchButtonNavigates() {
        onView(withId(R.id.btnMovieSearch)).perform(click());
        // Navigation assertion could be added if needed
    }

    @Test
    public void testActorSearchButtonNavigates() {
        onView(withId(R.id.btnActorSearch)).perform(click());
    }

    @Test
    public void testAdvancedSearchButtonNavigates() {
        onView(withId(R.id.btnAdvancedSearch)).perform(click());
    }

    @Test
    public void testMyMoviesButtonNavigates() {
        onView(withId(R.id.btnMyMovies)).perform(click());
    }

    @Test
    public void testInfoButtonNavigates() {
        onView(withId(R.id.ivInfo)).perform(click());
    }
}
