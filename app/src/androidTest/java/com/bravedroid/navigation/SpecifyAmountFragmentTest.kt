package com.bravedroid.navigation

import android.os.Bundle
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.bravedroid.navigation.util.GlobalTransitionUtil
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.verify

@RunWith(AndroidJUnit4::class)
class SpecifyAmountFragmentTest {
    @Before
    fun setUp() {
    }

    @Test
    fun test_navigation_from_SpecifyAmountFragment_to_ConfirmationFragment() {
        val navControllerMock = Mockito.mock(NavController::class.java)
        val bundle: Bundle = Bundle()
        bundle.putString("name", "Jim")
        val specifyAmountFragmentScenario: FragmentScenario<SpecifyAmountFragment> =
            launchFragmentInContainer<SpecifyAmountFragment>(
                fragmentArgs = bundle,
                themeResId = R.style.AppTheme
            )

        specifyAmountFragmentScenario.onFragment { fragment ->
            Navigation.setViewNavController(fragment.requireView(), navControllerMock)
        }

//        Thread.sleep(3000)

        onView(withId(R.id.input_amount)).perform(typeText("100"))
        onView(withId(R.id.send_btn)).perform(click())

        verify(navControllerMock).navigate(
            SpecifyAmountFragmentDirections.actionSpecifyAmountFragmentToConfirmationFragment(
                "100",
                "Jim"
            ),
            GlobalTransitionUtil.navOptions
        )
    }
}
