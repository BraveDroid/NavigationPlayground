package com.bravedroid.navigation.util

import android.app.PendingIntent
import android.content.Context
import android.os.Bundle
import androidx.annotation.IdRes
import androidx.annotation.NavigationRes
import androidx.navigation.NavController
import androidx.navigation.NavDeepLinkBuilder

class DeepLinkFactory {

    companion object {
        fun createExplicitDeepLink(
            context: Context,
            @NavigationRes navGraphId: Int,
            @IdRes destinationId: Int,
            args: Bundle?
        ): PendingIntent {
            return NavDeepLinkBuilder(context)
                .setGraph(navGraphId)
                .setDestination(destinationId)
                .setArguments(args)
                .createPendingIntent()
        }

        fun createExplicitDeepLink(
            navController: NavController,
            @NavigationRes navGraphId: Int,
            @IdRes destinationId: Int,
            args: Bundle?
        ): PendingIntent {
            return navController.createDeepLink()
                .setGraph(navGraphId)
                .setDestination(destinationId)
                .setArguments(args)
                .createPendingIntent()
        }
    }
}


