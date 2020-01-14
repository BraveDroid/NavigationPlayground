package com.bravedroid.navigation

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.bravedroid.navigation.util.GlobalTransitionUtil
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.Mockito
import org.mockito.Mockito.verify

@RunWith(AndroidJUnit4::class)
class ChooseRecipientFragmentTest {
    @Test
    fun test_navigation_from_ChooseRecipientFragment_to_SpecifyAmountFragment() {
        val navControllerMock = Mockito.mock(NavController::class.java)
        val chooseRecipientFragmentScenario =
            launchFragmentInContainer<ChooseRecipientFragment>(themeResId = R.style.AppTheme)

        chooseRecipientFragmentScenario.onFragment { fragment ->
            Navigation.setViewNavController(fragment.requireView(), navControllerMock)
        }

        onView(withId(R.id.input_recipient)).perform(typeText("Jim"))
        onView(withId(R.id.next_btn_choose_recipient)).perform(click())
//verify with  ArgumentMatchers
        verify(navControllerMock).navigate(
            ArgumentMatchers.eq(
                ChooseRecipientFragmentDirections.actionChooseRecipientFragmentToSpecifyAmountFragment(
                    "Jim"
                )
            ),
//            ArgumentMatchers.argThat<NavDirections> {
//                it.arguments.getString("name") == "Jim"
//            },
            ArgumentMatchers.argThat<NavOptions> {
                it.enterAnim == (R.anim.slide_in_from_right_to_left)
                        && it.exitAnim == (R.anim.slide_out_from_right_to_left)
                        && it.popEnterAnim == (R.anim.slide_in_from_left_to_right)
                        && it.popExitAnim == (R.anim.slide_out_from_left_to_right)
            }
        )

//verify but with raw arguments
        verify(navControllerMock).navigate(
            ChooseRecipientFragmentDirections.actionChooseRecipientFragmentToSpecifyAmountFragment("Jim"),
            GlobalTransitionUtil.navOptions
        )
    }
}
