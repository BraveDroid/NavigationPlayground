package com.bravedroid.historymodule

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController

class HistoryFragment : Fragment() {
    companion object {
        const val IS_FROM_NOTIFICATION_KEY = "is_from_notification_key"
        const val MESSAGE_KEY = "message_key"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val isFromNotification = arguments?.getBoolean(IS_FROM_NOTIFICATION_KEY) ?: false
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            val navController = findNavController()
            if (!isFromNotification) {
                navController.popBackStack()
            } else {
                val navOptions =
                    NavOptions.Builder().setPopUpTo(R.id.historyFragment, true).build()
                navController.navigate(Uri.parse("appAndroid://mainScreen"), navOptions)
            }
        }
        val view = inflater.inflate(R.layout.fragment_history, container, false)
        val message_tv = view.findViewById<TextView>(R.id.message_tv)
        message_tv.text = arguments?.getString("MESSAGE_KEY")
        return view
    }
}

