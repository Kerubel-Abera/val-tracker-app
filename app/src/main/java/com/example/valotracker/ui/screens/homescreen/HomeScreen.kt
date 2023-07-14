package com.example.valotracker.ui.screens.homescreen

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.valotracker.ui.theme.Red
import com.example.valotracker.ui.theme.SCREEN_CONSTRAINT
import com.example.valotracker.ui.theme.backgroundColor
import com.example.valotracker.ui.viewmodels.SharedViewModel
import com.example.valotracker.util.Status


/**
 * Composable function that displays the home screen.
 *
 * @param navigateToLoginScreen The callback to navigate to the login screen.
 * @param navigateToMatchScreen The callback to navigate to the match screen.
 * @param sharedViewModel The shared view model instance.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navigateToLoginScreen: () -> Unit,
    navigateToMatchScreen: (String) -> Unit,
    sharedViewModel: SharedViewModel
) {
    val dataLoaded = rememberSaveable { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        if (!dataLoaded.value) {
            sharedViewModel.getMmrData()
            sharedViewModel.getHistoryData()
            dataLoaded.value = true
        }
    }

    val history by sharedViewModel.matchHistory.observeAsState()
    val lifetimeHistory by sharedViewModel.lifetimeMatchHistory.observeAsState()
    val mmr by sharedViewModel.mmr.observeAsState()
    var finishedLoadingMMR by remember { mutableStateOf(false) }
    var finishedLoadingHistory by remember { mutableStateOf(false) }

    Log.i("HomeScreen", lifetimeHistory?.status?.name ?: "no status")

    Scaffold(
        topBar = {
            if (finishedLoadingMMR && finishedLoadingHistory) {
                DefaultHomeAppBar(
                    onExitClicked = {
                        navigateToLoginScreen()
                        sharedViewModel.logout()
                    },
                    username = mmr!!.data?.data?.name ?: "error",
                    tag = mmr!!.data?.data?.tag ?: "error"
                )
            }
        },
        content = {

            Column(
                Modifier
                    .padding(
                        top = SCREEN_CONSTRAINT,
                        start = SCREEN_CONSTRAINT,
                        end = SCREEN_CONSTRAINT
                    )
            ) {
                if (!finishedLoadingMMR || !finishedLoadingHistory) {
                    Loading()
                } else {
                    Rank(
                        mmrDetailsResponse = mmr!!.data,
                        rankColor = sharedViewModel.getRankColor(
                            mmr!!.data?.data?.currentData?.currenttierpatched ?: "Silver 1"
                        ),
                        paddingValues = it,
                        sharedViewModel = sharedViewModel
                    )
                    MatchHistory(
                        lifetimeMatchesResponse = lifetimeHistory?.data!!,
                        mmrHistoryResponse = history?.data!!,
                        sharedViewModel = sharedViewModel,
                        navigateToMatchScreen = navigateToMatchScreen
                    )
                }

                if (mmr != null) {
                    when (mmr!!.status) {
                        Status.ERROR -> {
                            Log.d("HomeScreen", "ERROR")
                        }

                        Status.LOADING -> {
                            finishedLoadingMMR = false
                        }

                        Status.SUCCESS -> {
                            finishedLoadingMMR = true
                        }
                    }
                }
                if (lifetimeHistory != null && history != null) {
                    when {
                        lifetimeHistory!!.status == Status.ERROR || history!!.status == Status.ERROR -> {
                            Text(
                                "LIFETIMEHISTORY\nERROR STATUS: ${lifetimeHistory!!.status}\nERROR MESSAGE: ${lifetimeHistory!!.message}\n" +
                                        "ERROR DATA STATUS: ${lifetimeHistory!!.data?.status}\n" +
                                        "ERROR DATA DATA: ${lifetimeHistory!!.data?.data}"
                            )

                            Text(
                                "HISTORY\nERROR STATUS: ${history!!.status}\nERROR MESSAGE: ${history!!.message}\n" +
                                        "ERROR DATA STATUS: ${history!!.data?.status}\n" +
                                        "ERROR DATA DATA: ${history!!.data?.data}"
                            )

                        }

                        lifetimeHistory!!.status == Status.LOADING || history!!.status == Status.LOADING -> {
                            finishedLoadingHistory = false
                        }

                        lifetimeHistory!!.status == Status.SUCCESS && history!!.status == Status.SUCCESS -> {
                            finishedLoadingHistory = true
                        }
                    }
                }
            }
        },
        containerColor = MaterialTheme.colorScheme.backgroundColor
    )

}

/**
 * Composable function that displays a loading indicator.
 */
@Composable
fun Loading() {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator(color = Red)
    }
}