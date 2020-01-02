package com.bravedroid.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.bravedroid.navigation.LoginViewModel.AuthenticationState.AUTHENTICATED
import com.bravedroid.navigation.LoginViewModel.AuthenticationState.INVALID_AUTHENTICATION
import com.bravedroid.navigation.RegistrationViewModel.RegistrationState.REGISTRATION_COMPLETED
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_choose_user_password.*
import kotlinx.android.synthetic.main.fragment_login.password_editText

class ChooseUserPasswordFragment : Fragment() {

    private val loginViewModel: LoginViewModel by activityViewModels()
    private val registrationViewModel: RegistrationViewModel by activityViewModels()
    private var isRegistrationCompleted: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_choose_user_password, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        registration_and_login_btn.setOnClickListener {
            registrationViewModel.createAccountAndLogin(
                userName_editText.text.toString(),
                password_editText.text.toString()
            )
        }
        loginViewModel.authenticationState.observe(
            viewLifecycleOwner, Observer { state ->
                if (isRegistrationCompleted) {
                    if (state == AUTHENTICATED) {
                        findNavController().popBackStack(R.id.profileFragment, false)
                    } else if (state == INVALID_AUTHENTICATION) {
                        Snackbar.make(requireView(), "AUTHENTICATION FAILED ", Snackbar.LENGTH_LONG)
                            .show()
                    }
                }
            }
        )
        registrationViewModel.registrationState.observe(
            viewLifecycleOwner, Observer { state ->
                if (state == REGISTRATION_COMPLETED) {
                    isRegistrationCompleted = true
                    val authToken = registrationViewModel.authToken
                    loginViewModel.authenticate(authToken)
                }
            }
        )

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            registrationViewModel.userCancelledRegistration()
            findNavController().popBackStack(R.id.loginFragment, false)
        }
    }
}
