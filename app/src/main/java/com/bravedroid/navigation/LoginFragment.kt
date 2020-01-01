package com.bravedroid.navigation


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_login.*

private const val TAG = "LoginFragment"

class LoginFragment : Fragment() {
    private val viewModel: LoginViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            viewModel.refuseAuthentication()
            //return to the destination that precede profileFragment
            findNavController().popBackStack(R.id.profileFragment, true)
        }
        viewModel.authenticationState.observe(viewLifecycleOwner, Observer { authenticationState ->
            when (authenticationState) {
                //return to whatever the precede destination in the navigation stack
                LoginViewModel.AuthenticationState.AUTHENTICATED -> findNavController().popBackStack()
                LoginViewModel.AuthenticationState.INVALID_AUTHENTICATION -> showErrorMessage()
                LoginViewModel.AuthenticationState.UNAUTHENTICATED -> Log.d(TAG, "UNAUTHENTICATED")
            }
        })
        login_btn.setOnClickListener {
            viewModel.authenticate(
                userName_editText.text.toString(),
                password_editText.text.toString()
            )
        }
    }

    private fun showErrorMessage() {
        Snackbar.make(requireView(), "AUTHENTICATION FAILED ", Snackbar.LENGTH_LONG).show()
    }
}
