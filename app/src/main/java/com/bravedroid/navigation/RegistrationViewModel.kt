package com.bravedroid.navigation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*

class RegistrationViewModel : ViewModel() {
    enum class RegistrationState {
        COLLECT_PROFILE_DATA,
        COLLECT_USER_PASSWORD,
        REGISTRATION_COMPLETED
    }

    var authToken = ""
        private set

    val registrationState = MutableLiveData<RegistrationState>()

    init {
        registrationState.value = RegistrationState.COLLECT_PROFILE_DATA
    }

    fun collectProfileData(name: String, bio: String) {
        // ... validate(on typing) and store registration data(in memory as properties)

        // Change State to collecting username and password
        registrationState.value = RegistrationState.COLLECT_USER_PASSWORD
    }

    fun createAccountAndLogin(username: String, password: String) {
        // store registration data
        // ... create account(http request)
        // ... authenticate (http request)and get token(jwt)
        val token = if (username == password && username == "a") {
            UUID.randomUUID().toString()
        } else {
            ""
        }
        this.authToken = token//should be stored

        // Change State to registration completed
        registrationState.value = RegistrationState.REGISTRATION_COMPLETED
    }

    fun userCancelledRegistration(): Boolean {
        // Clear existing registration data( name, bio, username, password )
        authToken = ""
        registrationState.value = RegistrationState.COLLECT_PROFILE_DATA
        return true
    }
}
