package com.example.valotracker.navigation.destinations

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.valotracker.ui.screens.loginscreen.LoginScreen
import com.example.valotracker.ui.viewmodels.SharedViewModel
import com.example.valotracker.util.Constants.LOGIN_SCREEN

fun NavGraphBuilder.loginComposable(
    navigateToHomeScreen: () -> Unit,
    sharedViewModel: SharedViewModel
) {
    composable(
        route = LOGIN_SCREEN
    ) {
        LoginScreen(
            navigateToHomeScreen,
            sharedViewModel
        )
    }
}