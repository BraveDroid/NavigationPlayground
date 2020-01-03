package com.bravedroid.historymodule

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class HistoryFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_history, container, false)
        val message_tv = view.findViewById<TextView>(R.id.message_tv)
        message_tv.text = arguments?.getString("MESSAGE_KEY")
        return view
    }
}

