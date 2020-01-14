package com.bravedroid.navigation

import android.os.Bundle
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.CoreMatchers.containsString
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito

@RunWith(AndroidJUnit4::class)

class ConfirmationFragmentTest {
    @Test
    fun test_navigation_ConfirmationFragment() {
        val bundle: Bundle = Bundle()
        bundle.putString("amount", "100")
        bundle.putString("name", "Jim")
        val navControllerMock = Mockito.mock(NavController::class.java)
        val confirmationFragmentScenario =
            launchFragmentInContainer<ConfirmationFragment>(
                fragmentArgs = bundle,
                themeResId = R.style.AppTheme
            )
        confirmationFragmentScenario.onFragment { fragment ->
            Navigation.setViewNavController(fragment.requireView(), navControllerMock)
        }
        onView(withId(R.id.confirmation_message)).check(matches(withText(containsString("100$ was sent to Jim"))))
        //same as
        // onView(withId(R.id.confirmation_message)).check(matches(withText(`is`("100$ was sent to Jim"))))
    }
}
