package com.example.jokot.footballclub.home

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.Espresso.pressBack
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.scrollTo
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v4.app.FragmentActivity
import android.support.v7.widget.RecyclerView
import android.widget.ProgressBar
import com.example.jokot.footballclub.R
import com.example.jokot.footballclub.R.id.*
import com.example.jokot.footballclub.R.string.favorites
import com.example.jokot.footballclub.activity.NavigationActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NavigationActivityTest {
    @Rule
    @JvmField var activityRule = ActivityTestRule(NavigationActivity::class.java)

    @Test
    fun testAppBehaviour() {
        onView(withId(navigation)).check(matches(isDisplayed()))
        Thread.sleep(5000)
        onView(withId(list_prev_match)).check(matches(isDisplayed()))
        onView(withId(list_prev_match)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(1, click()))
        onView(withId(add_to_favorite)).perform(click())
        pressBack()
        onView(withId(navigation_next_match)).perform(click())
        onView(withId(navigation)).check(matches(isDisplayed()))
        onView(withId(navigation_prev_match)).perform(click())
        onView(withId(navigation)).check(matches(isDisplayed()))
        onView(withId(navigation_favorite)).perform(click())
        onView(withId(list_favorite)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(add_to_favorite)).perform(click())
        pressBack()

    }
}