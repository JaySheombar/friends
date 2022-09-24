package com.jaySH.friends.signup

import androidx.lifecycle.ViewModel
import com.jaySH.friends.domain.validation.CredentialsValidationResult
import com.jaySH.friends.domain.validation.RegexCredentialsValidator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val credentialsValidator: RegexCredentialsValidator,
): ViewModel() {

    private val _state = MutableStateFlow(SignUpViewModelState())
    val state: StateFlow<SignUpViewModelState>
        get() = _state

    fun createAccount(
        email: String,
        password: String,
        about: String,
    ) {
        val signUpState = when(credentialsValidator.validate(email, password)) {
            CredentialsValidationResult.InvalidEmail -> SignUpState.BadEmail
            CredentialsValidationResult.InvalidPassword -> SignUpState.BadPassword
            CredentialsValidationResult.Valid -> SignUpState.Valid
        }

        _state.update { SignUpViewModelState(signUpState = signUpState) }
    }

    data class SignUpViewModelState(
        val signUpState: SignUpState = SignUpState.BadEmail,
    )
}