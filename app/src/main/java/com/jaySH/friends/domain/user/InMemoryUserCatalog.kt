package com.jaySH.friends.domain.user

import com.jaySH.friends.domain.exceptions.DuplicateAccountException

class InMemoryUserCatalog(
    private val usersForPassword: MutableMap<String, MutableList<User>> = mutableMapOf(),
) {
    fun createUser(email: String, password: String, about: String): User {
        checkAccountExists(email)
        val userId = createUserIdForEmail(email)
        val user = User(userId, email, about)
        saveUser(user, password)
        return user
    }

    private fun saveUser(user: User, password: String) {
        usersForPassword.getOrPut(password, ::mutableListOf).add(user)
    }

    private fun createUserIdForEmail(email: String): String {
        return email.takeWhile { it != '@' } + "Id"
    }

    private fun checkAccountExists(email: String) {
        if (usersForPassword.values.flatten().any { it.email == email }) {
            throw DuplicateAccountException()
        }
    }
}