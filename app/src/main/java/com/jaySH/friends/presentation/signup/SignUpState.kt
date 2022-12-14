package com.jaySH.friends.presentation.signup

import com.jaySH.friends.domain.user.User

sealed class SignUpState {
    data class SignedUp(val user: User) : SignUpState()
    object BadEmail: SignUpState()
    object BadPassword: SignUpState()
    object DuplicateAccount: SignUpState()
}