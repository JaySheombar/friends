package com.jaySH.friends.signup

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.jaySH.friends.MainActivity
import org.junit.Rule
import org.junit.Test

class SignUpTest {

    @get:Rule
    val signUpTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun performSignUp() {
        launchSignUpScreen(signUpTestRule) {
            typeEmail("john@friends.com")
            typePassword("12ABcd3!^")
            submit()
        } verify {
            timelineScreenIsPresent()
        }
    }
}