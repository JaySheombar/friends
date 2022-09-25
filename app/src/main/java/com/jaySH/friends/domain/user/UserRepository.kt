package com.jaySH.friends.domain.user

import com.jaySH.friends.domain.exceptions.DuplicateAccountException
import com.jaySH.friends.signup.SignUpState

class UserRepository(private val userCatalog: InMemoryUserCatalog) {
    fun signUp(email: String, password: String, about: String) = try {
        val user = userCatalog.createUser(email, password, about)
        SignUpState.SignedUp(user)
    } catch (e: DuplicateAccountException) {
        SignUpState.DuplicateAccount
    }
}