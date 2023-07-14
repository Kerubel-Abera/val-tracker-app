package com.example.valotracker.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.valotracker.navigation.destinations.homeComposable
import com.example.valotracker.navigation.destinations.loginComposable
import com.example.valotracker.navigation.destinations.matchComposable
import com.example.valotracker.ui.viewmodels.SharedViewModel
import com.example.valotracker.util.Constants.LOGIN_SCREEN

/**
 * Sets up the navigation within the app using a [NavHost] and the provided [NavHostController] and [SharedViewModel].
 *
 * @param navController The NavHostController instance.
 * @param sharedViewModel The SharedViewModel instance.
 */
@Composable
fun SetupNavigation(
    navController: NavHostController,
    sharedViewModel: SharedViewModel
) {
    val screen = Screens(navController)

    NavHost(
        navController = navController,
        startDestination = LOGIN_SCREEN
    ) {
        homeComposable(
            navigateToLoginScreen = screen.login,
            navigateToMatchScreen = screen.match,
            sharedViewModel = sharedViewModel
        )
        loginComposable(
            navigateToHomeScreen = screen.home,
            sharedViewModel = sharedViewModel
        )
        matchComposable(
            navigateBack = screen.back,
            sharedViewModel = sharedViewModel
        )
    }
}