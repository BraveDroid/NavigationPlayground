package com.bravedroid.navigation


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_main.*

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
                R.id.view_transactions_btn -> requireActivity().findNavController(view.id).navigate(R.id.action_mainFragment_to_viewTransactionFragment)
                //navigate using action Id (findNavController is the extension of the view)
                R.id.send_money_btn -> view.findNavController().navigate(R.id.action_mainFragment_to_chooseRecipientFragment)
                //navigate using action Id (findNavController is the extension of the fragment)
                R.id.view_balance_btn -> findNavController().navigate(R.id.action_mainFragment_to_viewBalanceFragment)
                //navigate using destination Id
                R.id.profile_btn -> requireActivity().findNavController(view.id).navigate(R.id.profileFragment)
                R.id.about_us_btn -> requireActivity().findNavController(view.id).navigate(R.id.aboutActivity)
                //navigate using navDirections from safe args
                R.id.settings_btn -> {
                    val action = MainFragmentDirections.actionMainFragmentToChooseRecipientFragment()
                   findNavController().navigate(action)
                }
            }
        }

        view_balance_btn.setOnClickListener(callback)
        send_money_btn.setOnClickListener(callback)
        view_transactions_btn.setOnClickListener(callback)
        profile_btn.setOnClickListener(callback)
        about_us_btn.setOnClickListener(callback)
        settings_btn.setOnClickListener(callback)
    }
}
