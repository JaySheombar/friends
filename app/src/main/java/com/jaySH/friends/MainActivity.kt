package com.jaySH.friends

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jaySH.friends.presentation.signup.SignUp
import com.jaySH.friends.presentation.timeline.Timeline
import com.jaySH.friends.presentation.ui.theme.FriendsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()

            FriendsTheme {
                NavHost(navController = navController, startDestination = SIGN_UP) {
                    composable(route = SIGN_UP) {
                        SignUp(
                            onSignedUp = {
                                navController.navigate(TIMELINE) { launchSingleTop = true }
                            },
                        )
                    }

                    composable(route = TIMELINE) { Timeline() }
                }
            }
        }
    }

    private companion object {
        private const val SIGN_UP = "sign_up"
        private const val TIMELINE = "timeline"
    }
}