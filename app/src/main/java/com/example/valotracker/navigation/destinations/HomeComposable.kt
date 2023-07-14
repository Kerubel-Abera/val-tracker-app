package com.example.valotracker.navigation.destinations

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.valotracker.ui.screens.homescreen.HomeScreen
import com.example.valotracker.ui.viewmodels.SharedViewModel
import com.example.valotracker.util.Constants.HOME_SCREEN

fun NavGraphBuilder.homeComposable(
    navigateToLoginScreen: () -> Unit,
    navigateToMatchScreen: (String) -> Unit,
    sharedViewModel: SharedViewModel
) {
    composable(
        route = HOME_SCREEN
    ) {
        HomeScreen(
            navigateToLoginScreen,
            navigateToMatchScreen,
            sharedViewModel
        )
    }
}