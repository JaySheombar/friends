package com.jaySH.friends.domain.validation

import java.util.regex.Pattern
import javax.inject.Inject

class RegexCredentialsValidator @Inject constructor() {
    private val emailPattern = Pattern.compile(EMAIL_REGEX)
    private val passwordPattern = Pattern.compile(PASSWORD_REGEX)

    fun validate(email: String, password: String): CredentialsValidationResult {
        val emailValid = emailPattern.matcher(email).matches()
        val passwordValid = passwordPattern.matcher(password).matches()

        val result = when {
            !emailValid -> CredentialsValidationResult.InvalidEmail
            !passwordValid -> CredentialsValidationResult.InvalidPassword
            else -> CredentialsValidationResult.Valid
        }
        return result
    }

    companion object {
        private const val EMAIL_REGEX =
            """[a-zA-Z0-9+._%\-]{1,64}@[a-zA-Z0-9][a-zA-Z0-9\-]{1,64}(\.[a-zA-Z0-9][a-zA-Z0-9\-]{1,25})"""

        private const val PASSWORD_REGEX =
            """^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*+=\-]).{8,}$"""
    }
}