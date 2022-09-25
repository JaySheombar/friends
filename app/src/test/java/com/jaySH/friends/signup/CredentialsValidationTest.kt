package com.jaySH.friends.signup

import com.jaySH.friends.domain.user.InMemoryUserCatalog
import com.jaySH.friends.domain.user.UserRepository
import com.jaySH.friends.domain.validation.CredentialsValidationResult
import com.jaySH.friends.domain.validation.RegexCredentialsValidator
import com.jaySH.friends.presentation.signup.SignUpState
import com.jaySH.friends.presentation.signup.SignUpViewModel
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class CredentialsValidationTest {

    @ParameterizedTest
    @CsvSource(
        "'email'",
        "'a@b.c'",
        "'ab@b.c'",
        "'ab@bc.c'",
        "''",
        "'          '",
    )
    fun invalidEmail(email: String) {
        val viewModel = SignUpViewModel(
            credentialsValidator = RegexCredentialsValidator(),
            userRepository = UserRepository(InMemoryUserCatalog(mutableMapOf())),
        )

        viewModel.createAccount(email, ":password:", ":about:")

        assertEquals(SignUpState.BadEmail, viewModel.state.value.signUpState)
    }

    @ParameterizedTest
    @CsvSource(
        "''",
        "'          '",
        "'12345678'",
        "'abcd5678'",
        "'abcDEF78'",
        "'abcdef78#$'",
        "'ABCDEF78#$'",
    )
    fun invalidPassword(password: String) {
        val viewModel = SignUpViewModel(
            credentialsValidator = RegexCredentialsValidator(),
            userRepository = UserRepository(InMemoryUserCatalog(mutableMapOf())),
        )

        viewModel.createAccount("example@friends.com", password, ":about:")

        assertEquals(SignUpState.BadPassword, viewModel.state.value.signUpState)
    }

    @Test
    fun validCredentials() {
        val validator = RegexCredentialsValidator()

        val result = validator.validate("john@friends.com", "12ABcd3!^")

        assertEquals(CredentialsValidationResult.Valid, result)
    }
}