package com.example.valotracker.navigation

import androidx.navigation.NavHostController
import com.example.valotracker.util.Constants.HOME_SCREEN
import com.example.valotracker.util.Constants.LOGIN_SCREEN

/**
 * Class that manages the navigation actions for different screens using a [NavHostController].
 *
 * @param navController The NavHostController instance.
 */
class Screens(navController: NavHostController) {

    // Navigates to the login screen.
    val login: () -> Unit = {
        navController.navigate(LOGIN_SCREEN) {
            popUpTo(LOGIN_SCREEN) { inclusive = true }
        }
    }

    // Navigates to the home screen.
    val home: () -> Unit = {
        navController.navigate(HOME_SCREEN) {
            popUpTo(HOME_SCREEN) { inclusive = true }
        }
    }


    // Navigates to the match screen with the specified match ID.
    val match: (String) -> Unit = { matchId ->
        navController.navigate("match/$matchId")
    }

    // Performs a back navigation.
    val back: () -> Unit = {
        navController.popBackStack()
    }
}