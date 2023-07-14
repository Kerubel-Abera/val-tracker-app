package com.example.valotracker.navigation.destinations

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.valotracker.ui.screens.matchscreen.MatchScreen
import com.example.valotracker.ui.viewmodels.SharedViewModel
import com.example.valotracker.util.Constants.MATCH_ARGUMENT_KEY
import com.example.valotracker.util.Constants.MATCH_SCREEN

fun NavGraphBuilder.matchComposable(
    navigateBack: () -> Unit,
    sharedViewModel: SharedViewModel
) {
    composable(
        route = MATCH_SCREEN,
        arguments = listOf(navArgument(MATCH_ARGUMENT_KEY) {
            type = NavType.StringType
            nullable = false
        })
    ) { backStackEntry ->
        backStackEntry.arguments!!.getString("matchId")?.let { matchId ->
            MatchScreen(matchId, sharedViewModel, navigateBack)
        }
    }
}