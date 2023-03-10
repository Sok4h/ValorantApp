package com.sokah.valorantapp.ui.view.fragments

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.sokah.valorantapp.R
import com.sokah.valorantapp.ui.view.MainActivity
import com.sokah.valorantapp.ui.view.adapters.AgentAdapter.AgentViewHolder
import com.sokah.valorantapp.utils.IdlingResource
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AgentsFragmentTest {


    @get : Rule
    var mActivityRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(IdlingResource.countingIdlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(IdlingResource.countingIdlingResource)
    }

    @Test
    fun checks_that_the_recyclerview_is_displayed() {

        onView(withId(R.id.rvAgents)).check(matches(isDisplayed()))
    }

    @Test
    fun selecting_item_in_recyclerview_detail_fragment_is_displayed() {

        onView(withId(R.id.rvAgents)).perform(
            actionOnItemAtPosition<AgentViewHolder>(
                0,
                click()
            )
        )

        onView(withId(R.id.tvAgentNameDetail)).check(matches(isDisplayed()))

    }

    @Test
    fun chip_selected_works_properly() {

        onView(withId(R.id.chipDuelist)).perform(click())

        onView(withId(R.id.chipDuelist)).check(matches(isChecked()))

    }

    @Test
    fun when_item_click_checks_correct_agent_name_in_detail_fragment() {

        onView(withId(R.id.rvAgents))
            .perform(
                actionOnItem<AgentViewHolder>(
                    hasDescendant(withText("Jett")), click()
                )
            )

        onView(withId(R.id.tvAgentNameDetail)).check(matches(withText("Jett")))

    }
}