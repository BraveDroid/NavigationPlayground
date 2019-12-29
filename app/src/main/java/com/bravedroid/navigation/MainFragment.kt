package com.bravedroid.navigation


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val callback: (View) -> Unit = {
            when (it.id) {
                R.id.view_transactions_btn -> findNavController().navigate(R.id.action_mainFragment_to_viewTransactionFragment4)
                R.id.send_money_btn -> findNavController().navigate(R.id.action_mainFragment_to_chooseRecipientFragment)
                R.id.view_balance_btn -> findNavController().navigate(R.id.action_mainFragment_to_viewBalanceFragment2)
                R.id.profile_btn -> findNavController().navigate(R.id.profileFragment)
                R.id.about_us_btn -> findNavController().navigate(R.id.aboutActivity)
            }
        }

        view_balance_btn.setOnClickListener(callback)
        send_money_btn.setOnClickListener(callback)
        view_transactions_btn.setOnClickListener(callback)
        profile_btn.setOnClickListener(callback)
        about_us_btn.setOnClickListener(callback)
    }
}
