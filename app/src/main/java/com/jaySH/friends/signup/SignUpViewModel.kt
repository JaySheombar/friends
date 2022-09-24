package com.jaySH.friends.signup

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(): ViewModel() {

    private val _state = MutableStateFlow(SignUpViewModelState())
    val state: StateFlow<SignUpViewModelState>
        get() = _state

    fun createAccount(
        email: String,
        password: String,
        about: String,
    ) {

    }

    data class SignUpViewModelState(
        val signUpState: SignUpState = SignUpState.BadEmail,
    )
}