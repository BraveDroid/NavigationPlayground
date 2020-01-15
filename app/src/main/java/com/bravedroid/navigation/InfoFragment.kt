package com.bravedroid.navigation


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.bravedroid.historymodule.HistoryFragment
import com.bravedroid.navigation.util.DeepLinkFactory
import com.bravedroid.navigation.util.NotificationFactory
import kotlinx.android.synthetic.main.fragment_info.*


class InfoFragment : Fragment() {
    private val fakeNotification: NotificationFactory =
        NotificationFactory()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        exit_btn.setOnClickListener {
            requireActivity().finish()
        }
        deepLinkImplicit_amazon.setOnClickListener {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW, Uri.parse(
                        "https://www.amazon.fr/"
                    )
                )
            )

        }
        deepLinkImplicit_specifyAmountFragment.setOnClickListener {
            //startActivity(
            //Intent(
            //Intent.ACTION_VIEW, Uri.parse(
            // "https://www.bravedroid.com/name/Binder"
            // )
            // ).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            // )
            findNavController().navigate(
                Uri.parse("https://www.bravedroid.com/name/Binder"),
                NavOptions.Builder().setPopUpTo(R.id.profileFragment, true).build()
            )
        }

        notification_btn.setOnClickListener {
            fakeNotification.sendFakeNotification(
                requireContext(),
                DeepLinkFactory.createExplicitDeepLink(
                    requireContext(),
                    R.navigation.history_nav_graph,
                    R.id.historyFragment,
                    bundleOf(HistoryFragment.IS_FROM_NOTIFICATION_KEY to true)
                )
            )
        }
    }
}
