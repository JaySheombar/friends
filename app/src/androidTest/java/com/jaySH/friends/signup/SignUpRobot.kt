package com.jaySH.friends.signup

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.jaySH.friends.MainActivity
import com.jaySH.friends.R

fun launchSignUpScreen(
    rule: AndroidComposeTestRule<ActivityScenarioRule<MainActivity>, MainActivity>,
    block: SignUpRobot.() -> Unit,
): SignUpRobot {
    return SignUpRobot(rule).apply(block)
}

class SignUpRobot(
    private val rule: AndroidComposeTestRule<ActivityScenarioRule<MainActivity>, MainActivity>,
) {
    fun typeEmail(email: String) {
        val emailHint = rule.activity.getString(R.string.email)
        rule.onNodeWithTag(emailHint)
            .performTextInput(email)
    }

    fun typePassword(password: String) {
        val passwordHint = rule.activity.getString(R.string.password)
        rule.onNodeWithTag(passwordHint)
            .performTextInput(password)
    }

    fun submit() {
        val signUp = rule.activity.getString(R.string.sign_up)
        rule.onNodeWithTag(signUp)
            .performClick()
    }

    infix fun verify(block: SignUpVerification.() -> Unit): SignUpVerification {
        return SignUpVerification(rule).apply(block)
    }
}

class SignUpVerification(
    private val rule: AndroidComposeTestRule<ActivityScenarioRule<MainActivity>, MainActivity>,
) {
    fun timelineScreenIsPresent() {
        val timeline = rule.activity.getString(R.string.timeline)
        rule.onNodeWithTag(timeline)
            .assertIsDisplayed()
    }
}