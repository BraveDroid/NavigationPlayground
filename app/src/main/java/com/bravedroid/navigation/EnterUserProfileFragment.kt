package com.bravedroid.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_enter_user_profile.*

class EnterUserProfileFragment : Fragment() {

    val registrationViewModel by activityViewModels<RegistrationViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_enter_user_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        next_btn.setOnClickListener {
            val name = fullName_editText.text.toString()
            val bio = bio_editText.text.toString()
            registrationViewModel.collectProfileData(name, bio)
        }
        registrationViewModel.registrationState.observe(
            viewLifecycleOwner, Observer { state ->
                if (state == RegistrationViewModel.RegistrationState.COLLECT_USER_PASSWORD) {
                    findNavController().navigate(EnterUserProfileFragmentDirections.actionEnterUserProfileFragmentToChooseUserPasswordFragment())
                }
            })
    }
}
