package com.jaySH.friends.signup

import com.jaySH.friends.domain.user.InMemoryUserCatalog
import com.jaySH.friends.domain.user.User
import com.jaySH.friends.domain.user.UserRepository
import com.jaySH.friends.domain.validation.RegexCredentialsValidator
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class CreateAnAccountTest {

    private val credentialsValidator = RegexCredentialsValidator()
    private val viewModel = SignUpViewModel(
        credentialsValidator,
        UserRepository(InMemoryUserCatalog(mutableMapOf()))
    )

    @Test
    fun accountCreated() {
        // Given
        val user = User("exampleId", "example@friends.com", "about example")
        val password = "12ABcd3!^"

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
        val usersForPassword = mutableMapOf(password to mutableListOf(user))

        val userRepository = UserRepository(InMemoryUserCatalog(usersForPassword))
        val viewModel = SignUpViewModel(credentialsValidator, userRepository)

        // When
        viewModel.createAccount(user.email, password, user.about)

        // Then
        Assertions.assertEquals(SignUpState.DuplicateAccount, viewModel.state.value.signUpState)
    }
}