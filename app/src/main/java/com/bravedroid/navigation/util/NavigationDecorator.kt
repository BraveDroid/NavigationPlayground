package com.bravedroid.navigation.util

import android.app.Activity
import android.os.Bundle
import android.widget.ImageView
import androidx.annotation.IdRes
import androidx.core.app.ActivityOptionsCompat
import androidx.navigation.ActivityNavigator
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.FragmentNavigatorExtras
import com.bravedroid.navigation.R
import kotlin.Int
import kotlin.Pair
import kotlin.getValue
import kotlin.lazy
import androidx.core.util.Pair as PairUtil

fun NavController.navigateWithAnimation(@IdRes resId: Int, args: Bundle? = null) {
    navigate(resId, args, GlobalTransitionUtil.navOptions)
}

fun NavController.navigateWithAnimation(directions: NavDirections) {
    navigate(directions, GlobalTransitionUtil.navOptions)
}

class NavControllerDecorator(private val nav: NavController) {
    fun navigate(@IdRes resId: Int, args: Bundle? = null) {
        nav.navigate(resId, args, GlobalTransitionUtil.navOptions)
    }

    fun navigate(directions: NavDirections) {
        nav.navigate(directions, GlobalTransitionUtil.navOptions)
    }
}

object GlobalTransitionUtil {
    val navOptions: NavOptions by lazy {
        createDefaultFragmentNavOptions()
    }

    private fun createDefaultFragmentNavOptions(): NavOptions = NavOptions.Builder()
        .setEnterAnim(R.anim.slide_in_from_right_to_left)
        .setExitAnim(R.anim.slide_out_from_right_to_left)
        .setPopEnterAnim(R.anim.slide_in_from_left_to_right)
        .setPopExitAnim(R.anim.slide_out_from_left_to_right).build()
}

class ActivityTransitionUtil(
    activity: Activity,
    imageView: ImageView
) {
    val extras: ActivityNavigator.Extras by lazy {
        createSharedObjectTransitionExtras(activity, imageView)
    }

    companion object {
        private fun createSharedObjectTransitionExtras(
            activity: Activity,
            imageView: ImageView
        ): ActivityNavigator.Extras {
            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                activity,
                PairUtil.create(imageView, "settings_imageView_transition")
            )
            return ActivityNavigator.Extras.Builder().setActivityOptions(options).build()
        }
    }
}

class FragmentTransitionUtil(
    imageView: ImageView
) {
    val extras: FragmentNavigator.Extras by lazy {
        createSharedObjectTransitionExtras(imageView)
    }

    companion object {
        private fun createSharedObjectTransitionExtras(
            imageView: ImageView
        ): FragmentNavigator.Extras {
            return FragmentNavigatorExtras(
                Pair(imageView, "settings_imageView_transition")
            )
        }
    }
}
