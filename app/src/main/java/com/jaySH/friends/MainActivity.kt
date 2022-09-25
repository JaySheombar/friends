package com.jaySH.friends

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
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
                NavHost(
                    navController = navController,
                    startDestination = "sign_up",
                ) {
                    composable(route = "sign_up") {
                        SignUp(
                            onSignedUp = {
                                navController.navigate("timeline") { launchSingleTop = true }
                            }
                        )
                    }

                    composable(route = "timeline") {
                        Timeline()
                    }
                }
            }
        }
    }
}