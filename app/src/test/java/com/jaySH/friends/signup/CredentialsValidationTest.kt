package com.jaySH.friends.signup

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class CredentialsValidationTest {

    @Test
    fun invalidEmail() {
        val viewModel = SignUpViewModel()

        viewModel.createAccount("", ":password:", ":about:")

        assertEquals(SignUpState.BadEmail, viewModel.state.value.signUpState)
    }
}