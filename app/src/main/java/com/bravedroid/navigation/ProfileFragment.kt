package com.bravedroid.navigation


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_profile.*

private const val TAG: String = "ProfileFragment"

class ProfileFragment : Fragment() {
    private val viewModel: LoginViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            if (!hasFoundAndDismissedDialogByTag(ConfirmationProfileDialogFragment.TAG)) {
                findNavController().popBackStack()
            }
        }

        viewModel.authenticationState.observe(
            viewLifecycleOwner,
            Observer { authenticationState: LoginViewModel.AuthenticationState ->
                when (authenticationState) {
                    LoginViewModel.AuthenticationState.AUTHENTICATED -> {
                        Log.d(TAG, "AUTHENTICATED")
                    }
                    LoginViewModel.AuthenticationState.UNAUTHENTICATED, LoginViewModel.AuthenticationState.INVALID_AUTHENTICATION -> {
                        findNavController().navigate(ProfileFragmentDirections.actionGlobalLoginFragment())
                        Log.d(TAG, "UNAUTHENTICATED or INVALID_AUTHENTICATION ")
                    }
                }
            })

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

        info_btn.setOnClickListener {
            findNavController().navigate(ProfileFragmentDirections.actionGlobalInfoFragment())
        }
    }
}

private fun Fragment.hasFoundAndDismissedDialogByTag(tag: String): Boolean {
    val dialogFragment =
        requireActivity().supportFragmentManager.findFragmentByTag(tag) as? DialogFragment

    return if (dialogFragment != null && dialogFragment.isVisible) {
        dialogFragment.dismiss()
        true
    } else {
        false
    }
}


