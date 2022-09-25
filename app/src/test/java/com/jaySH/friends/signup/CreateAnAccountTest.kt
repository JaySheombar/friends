package com.jaySH.friends.signup

import androidx.lifecycle.viewmodel.compose.viewModel
import com.jaySH.friends.domain.user.User
import com.jaySH.friends.domain.validation.RegexCredentialsValidator
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class CreateAnAccountTest {

    @Test
    fun accountCreated() {
        // Given
        val user = User("exampleId", "example@friends.com", "about example")
        val password = "12ABcd3!^"

        val viewModel = SignUpViewModel(RegexCredentialsValidator())

        // When
        viewModel.createAccount(user.email, password, user.about)

        // Then
        Assertions.assertEquals(SignUpState.SignedUp(user), viewModel.state.value.signUpState)
    }

    @Test
    fun anotherAccountCreated() {
        // Given
        val user = User("bobId", "bob@friends.com", "about bob")
        val password = "12ABcd3!^"

        val viewModel = SignUpViewModel(RegexCredentialsValidator())

        // When
        viewModel.createAccount(user.email, password, user.about)

        // Then
        Assertions.assertEquals(SignUpState.SignedUp(user), viewModel.state.value.signUpState)
    }

    @Test
    fun createDuplicateAccount() {
        // Given
        val user = User("annaId", "anna@friends.com", "about anna")
        val password = "12ABcd3!^"

        val viewModel = SignUpViewModel(RegexCredentialsValidator())
            .also { it.createAccount(user.email, password, user.about) }

        // When
        viewModel.createAccount(user.email, password, user.about)

        // Then
        Assertions.assertEquals(SignUpState.DuplicateAccount, viewModel.state.value.signUpState)
    }
}