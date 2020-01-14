package com.bravedroid.navigation

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.*

@RunWith(AndroidJUnit4::class)

class ViewBalanceFragmentTest {

    @Before
    fun setUp() {
    }


    @Test
    fun test_navigation_from_ViewBalanceFragment_to_InfoFragment() {
        // Create a mock NavController
        val mockNavController = mock(NavController::class.java)
        `when`(mockNavController.navigateUp()).thenReturn(true)
        // Create a graphical FragmentScenario for the TitleScreen
        val viewBalanceFragmentScenario =
            launchFragmentInContainer<ViewBalanceFragment>(themeResId = R.style.AppTheme)
        // Set the NavController property on the fragment
        viewBalanceFragmentScenario.onFragment { fragment ->
            Navigation.setViewNavController(fragment.requireView(), mockNavController)
        }
        // Verify that performing a click prompts the correct Navigation action
        onView(withId(R.id.info_btn_viewBalance)).perform(click())
        verify(mockNavController).navigate(
            ViewBalanceFragmentDirections.actionGlobalInfoFragment()
        )
    }

    @Test
    fun test_visibility_settingsFragment() {

        val titleScenario = launchFragmentInContainer<SettingsFragment>()
        titleScenario.moveToState(Lifecycle.State.RESUMED)
        // Set the NavController property on the fragment
        titleScenario.onFragment { fragment ->
        }
    }
}
