package com.bravedroid.navigation

import android.os.Bundle
import android.widget.ImageView
import androidx.core.app.ActivityOptionsCompat
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.ActivityNavigator
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.Mockito
import kotlin.Pair
import androidx.core.util.Pair as PairUtil

@RunWith(AndroidJUnit4::class)

class MainFragmentTest {

    @Test
    fun test_navigation_from_MainFragment_to_AboutFragment() {
        val navControllerMock = Mockito.mock(NavController::class.java)
        val bundle: Bundle = Bundle()
        bundle.putString("messageText", "about message")
        val mainFragmentScenario: FragmentScenario<MainFragment> =
            launchFragmentInContainer<MainFragment>(
                themeResId = R.style.AppTheme
            )
        var expectedExtras: ActivityNavigator.Extras? = null
        mainFragmentScenario.onFragment { fragment ->
            Navigation.setViewNavController(fragment.requireView(), navControllerMock)
            val settingsImageView =
                fragment.requireView().findViewById<ImageView>(R.id.settings_imageView)
            val activityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(
                fragment.requireActivity(),
                PairUtil.create(settingsImageView, "settings_imageView_transition")
            )
            expectedExtras = ActivityNavigator.Extras.Builder()
                .setActivityOptions(activityOptionsCompat).build()
        }

        onView(ViewMatchers.withId(R.id.about_us_btn)).perform(click())
        Mockito.verify(navControllerMock).navigate(
            ArgumentMatchers.eq(
                MainFragmentDirections.actionMainFragmentToAboutActivity("about message")
            ),
            ArgumentMatchers.argThat<ActivityNavigator.Extras> {
                it.flags == expectedExtras?.flags
            }
        )
    }

    @Test

    fun test_navigation_from_MainFragment_to_SettingsFragment() {
        val navControllerMock = Mockito.mock(NavController::class.java)
        val mainFragmentScenario: FragmentScenario<MainFragment> =
            launchFragmentInContainer<MainFragment>(
                themeResId = R.style.AppTheme
            )
        var fragmentNavigatorExtras: FragmentNavigator.Extras? = null
        mainFragmentScenario.onFragment { fragment ->
            Navigation.setViewNavController(fragment.requireView(), navControllerMock)
            val settingsImageView =
                fragment.requireView().findViewById<ImageView>(R.id.settings_imageView)
            fragmentNavigatorExtras = FragmentNavigatorExtras(
                Pair(settingsImageView, "settings_imageView_transition")
            )
        }

        onView(ViewMatchers.withId(R.id.settings_imageView)).perform(click())
        Mockito.verify(navControllerMock).navigate(
            ArgumentMatchers.eq(
                MainFragmentDirections.actionMainFragmentToSettingsFragment()
            ),
            ArgumentMatchers.argThat<FragmentNavigator.Extras> {
                it.sharedElements == fragmentNavigatorExtras?.sharedElements
            }
        )
    }
}
