package com.jaySH.friends.signup

sealed class SignUpState {
    object BadEmail: SignUpState()
}