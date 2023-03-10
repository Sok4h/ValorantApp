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
import com.sokah.valorantapp.ui.view.adapters.WeaponAdapter.*
import com.sokah.valorantapp.utils.IdlingResource
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class WeaponListFragmentTest {

    @get:Rule
    val actvityRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setUp() {
        onView(withId(R.id.weaponListFragment)).perform(click())

        IdlingRegistry.getInstance().register(IdlingResource.countingIdlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(IdlingResource.countingIdlingResource)
    }

    @Test
    fun check_weapon_recycle_view_is_displayed() {

        onView(withId(R.id.rvWeapons)).check(matches(isDisplayed()))
    }

    @Test
    fun when_selecting_chip_it_gets_selected_correctly() {

        onView(withId(R.id.chipPistols)).perform(click())

        onView(withId(R.id.chipPistols)).check(matches(isChecked()))
    }

    @Test
    fun when_selecting_weapon_details_fragment_is_opened() {

        onView(withId(R.id.rvWeapons)).perform(
            actionOnItemAtPosition<WeaponViewHolder>(
                0, click()
            )
        )

        onView(withId(R.id.tvWeaponPriceDetail)).check(matches(isDisplayed()))
    }
}