package com.bravedroid.navigation


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        confirmation_btn.setOnClickListener {
            findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToConfirmationProfileDialogFragment())
        }

        confirmation_fullscreen_btn.setOnClickListener {
            val fragmentManager = requireActivity().supportFragmentManager
            fragmentManager.beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .add(
                    android.R.id.content,
                    ConfirmationProfileDialogFragment(),
                    ConfirmationProfileDialogFragment.TAG
                ).commit()
        }
    }


}
