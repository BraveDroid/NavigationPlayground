package com.bravedroid.navigation.util

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import com.bravedroid.navigation.R

fun NavController.navigateWithAnimation(@IdRes resId: Int, args: Bundle? = null) {
    val builder = NavOptions.Builder()
        .setEnterAnim(R.anim.slide_in_from_right_to_left)
        .setExitAnim(R.anim.slide_out_from_right_to_left)
        .setPopEnterAnim(R.anim.slide_in_from_left_to_right)
        .setPopExitAnim(R.anim.slide_out_from_left_to_right)
    navigate(
        resId,
        args,
        builder.build()
    )
}

fun NavController.navigateWithAnimation(directions: NavDirections) {
    val builder = NavOptions.Builder()
        .setEnterAnim(R.anim.slide_in_from_right_to_left)
        .setExitAnim(R.anim.slide_out_from_right_to_left)
        .setPopEnterAnim(R.anim.slide_in_from_left_to_right)
        .setPopExitAnim(R.anim.slide_out_from_left_to_right)
    navigate(
        directions,
        builder.build()
    )
}

class NavControllerDecorator(private val nav: NavController) {
    fun navigate(@IdRes resId: Int, args: Bundle? = null) {
        val builder = NavOptions.Builder()
            .setEnterAnim(R.anim.slide_in_from_right_to_left)
            .setExitAnim(R.anim.slide_out_from_right_to_left)
            .setPopEnterAnim(R.anim.slide_in_from_left_to_right)
            .setPopExitAnim(R.anim.slide_out_from_left_to_right)
        nav.navigate(
            resId,
            args,
            builder.build()
        )
    }

    fun navigate(directions: NavDirections) {
        val builder = NavOptions.Builder()
            .setEnterAnim(R.anim.slide_in_from_right_to_left)
            .setExitAnim(R.anim.slide_out_from_right_to_left)
            .setPopEnterAnim(R.anim.slide_in_from_left_to_right)
            .setPopExitAnim(R.anim.slide_out_from_left_to_right)
        nav.navigate(
            directions,
            builder.build()
        )
    }
}
