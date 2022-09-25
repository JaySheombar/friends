package com.jaySH.friends.signup

import androidx.lifecycle.ViewModel
import com.jaySH.friends.domain.user.InMemoryUserCatalog
import com.jaySH.friends.domain.user.UserRepository
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
    private val userRepository: UserRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(SignUpViewModelState())
    val state: StateFlow<SignUpViewModelState>
        get() = _state

    fun createAccount(email: String, password: String, about: String) {
        when (credentialsValidator.validate(email, password)) {
            CredentialsValidationResult.InvalidEmail -> _state.update {
                it.copy(signUpState = SignUpState.BadEmail)
            }
            CredentialsValidationResult.InvalidPassword -> _state.update {
                it.copy(signUpState = SignUpState.BadPassword)
            }
            CredentialsValidationResult.Valid -> _state.update {
                it.copy(signUpState = userRepository.signUp(email, password, about))
            }
        }
    }

    data class SignUpViewModelState(
        val signUpState: SignUpState = SignUpState.BadEmail,
    )
}