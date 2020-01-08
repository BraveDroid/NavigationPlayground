package com.bravedroid.navigation


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityOptionsCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.ActivityNavigator
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.bravedroid.navigation.util.NavControllerDecorator
import com.bravedroid.navigation.util.navigateWithAnimation
import com.bravedroid.historymodule.HistoryFragment
import kotlinx.android.synthetic.main.fragment_main.*
import androidx.core.util.Pair as UtilPair

class MainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(inflatedview: View, savedInstanceState: Bundle?) {

        val callback: (View) -> Unit = { view ->
            when (view.id) {
                //navigate using action Id (findNavController is the extension of the activity )
                R.id.view_transactions_btn -> requireActivity().findNavController(view.id).navigate(
                    R.id.action_mainFragment_to_viewTransactionFragment
                )
                //navigate using action Id (findNavController is the extension of the view)
                R.id.send_money_btn -> view.findNavController().navigateWithAnimation(R.id.action_mainFragment_to_chooseRecipientFragment)
                //navigate using action Id (findNavController is the extension of the fragment)
                R.id.view_balance_btn -> {
                    NavControllerDecorator(findNavController()).navigate(
                        R.id.action_mainFragment_to_viewBalanceFragment
                    )
                }
                //navigate using action Id
                R.id.profile_btn ->
                    requireActivity().findNavController(view.id).navigate(R.id.action_mainFragment_to_profile_navigation)
                //navigate using Destination Id, it is ok even no action is defined , because same nav graph
                R.id.about_us_btn -> {
                    val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                        requireActivity(),
                        UtilPair.create(settings_imageView, "settings_imageView_transition")
                    )
                    val extras: ActivityNavigator.Extras =
                        ActivityNavigator.Extras.Builder().setActivityOptions(options).build()

                    view.findNavController().navigate(
                        MainFragmentDirections.actionMainFragmentToAboutActivity("about message"),
                        extras
                    )
                }
                //navigate using navDirections from safe args
                R.id.settings_btn,
                R.id.settings_imageView -> {
                    val extras = FragmentNavigatorExtras(
                        settings_imageView to "settings_imageView_transition"
                    )
                    view.findNavController().navigate(
                        MainFragmentDirections.actionMainFragmentToSettingsFragment(),
                        extras
                    )
                }
                R.id.history_btn -> {
                    //navigate by destination using the id of the include navigation
                    //requireActivity().findNavController(view.id).navigate(R.id.history_nav_graph)
//                    val navDirections = MainFragmentDirections.actionMainFragmentToHistoryNavGraph()
//                    findNavController().navigate(navDirections)
                    val bundle = bundleOf(HistoryFragment.MESSAGE_KEY to "message history")
                    view.findNavController().navigate(R.id.history_nav_graph, bundle)
                }

                R.id.messages_btn -> {
                    //  findNavController().navigate(R.id.action_mainFragment_to_message_nav_graph)
                    findNavController().navigate(
                        MainFragmentDirections.actionMainFragmentToMessageNavGraph(
                            "message1"
                        )
                    )
                }
            }
        }

        view_balance_btn.setOnClickListener(callback)
        send_money_btn.setOnClickListener(callback)
        view_transactions_btn.setOnClickListener(callback)
        profile_btn.setOnClickListener(callback)
        about_us_btn.setOnClickListener(callback)
        settings_btn.setOnClickListener(callback)
        settings_imageView.setOnClickListener(callback)
        history_btn.setOnClickListener(callback)
        messages_btn.setOnClickListener(callback)
    }
}
