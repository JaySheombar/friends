package com.jaySH.friends.signup

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import java.util.regex.Pattern
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
        val emailRegex = """[a-zA-Z0-9+._%\-]{1,64}@[a-zA-Z0-9][a-zA-Z0-9\-]{1,64}(\.[a-zA-Z0-9][a-zA-Z0-9\-]{1,25})"""
        val emailPattern = Pattern.compile(emailRegex)
        val emailValid = emailPattern.matcher(email).matches()

        val passwordRegex = """^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*+=\-]).{8,}$"""
        val passwordPattern = Pattern.compile(passwordRegex)
        val passwordValid = passwordPattern.matcher(password).matches()

        val signUpState = when {
            !emailValid -> SignUpState.BadEmail
            !passwordValid -> SignUpState.BadPassword
            else -> SignUpState.Valid
        }

        _state.update { SignUpViewModelState(signUpState = signUpState) }
    }

    data class SignUpViewModelState(
        val signUpState: SignUpState = SignUpState.BadEmail,
    )
}