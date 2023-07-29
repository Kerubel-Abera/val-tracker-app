package com.example.valotracker.ui.screens.matchscreen

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.valotracker.R
import com.example.valotracker.ui.screens.homescreen.Loading
import com.example.valotracker.ui.theme.SCREEN_CONSTRAINT
import com.example.valotracker.ui.theme.backgroundColor
import com.example.valotracker.ui.viewmodels.SharedViewModel
import com.example.valotracker.util.Status


/**
 * Composable function that displays the match screen.
 *
 * @param matchId The ID of the match.
 * @param sharedViewModel The ViewModel shared by the screen's components.
 * @param navigateBack Callback to navigate back.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MatchScreen(
    matchId: String,
    sharedViewModel: SharedViewModel,
    navigateBack: () -> Unit
) {
    val matchDetails by sharedViewModel.matchDetails.observeAsState()
    val player by sharedViewModel.player.observeAsState()

    LaunchedEffect(matchId) {
        sharedViewModel.getMatchDetails(matchId)
    }

    if (matchDetails != null) {
        Scaffold(
            topBar = {
                DefaultMatchAppBar(
                    navigateBack = { navigateBack() }
                )
            },
            content = { paddingValues ->
                Column {
                    when (matchDetails!!.status) {
                        Status.LOADING -> {
                            Loading()
                        }

                        Status.ERROR -> {
                            //Text(text = "ERROR: ${matchDetails!!.data!!.status}")
                        }

                        Status.SUCCESS -> {
                            Column(
                                modifier = Modifier
                                    .padding(
                                        top = paddingValues.calculateTopPadding(),
                                        start = SCREEN_CONSTRAINT,
                                        end = SCREEN_CONSTRAINT
                                    ),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.SpaceBetween
                            ) {
                                val playerTeam =
                                    matchDetails!!.data!!.data.players.allPlayers.find {
                                        it.name == player!!.data!!.data.name && it.tag == player!!.data!!.data.tag
                                    }?.team ?: stringResource(id = R.string.red)

                                val hasWon: Boolean? =
                                    if (matchDetails!!.data!!.data.teams.red.hasWon || matchDetails!!.data!!.data.teams.blue.hasWon) {
                                        when (playerTeam) {
                                            stringResource(id = R.string.red) -> matchDetails!!.data!!.data.teams.red.hasWon
                                            stringResource(id = R.string.blue) -> matchDetails!!.data!!.data.teams.blue.hasWon
                                            else -> null
                                        }
                                    } else {
                                        null
                                    }

                                val timestamp =
                                    matchDetails!!.data!!.data.metadata.gameStart.toLong()
                                val datetimeString =
                                    sharedViewModel.convertTimestampToString(timestamp)
                                val matchLength =
                                    matchDetails!!.data!!.data.metadata.gameLength.toLong()
                                val matchLengthFormatted =
                                    "${matchLength / 60}m ${matchLength % 60}s"
                                MatchHeader(
                                    hasWon = hasWon,
                                    blueScore = matchDetails!!.data!!.data.teams.blue.roundsWon,
                                    redScore = matchDetails!!.data!!.data.teams.red.roundsWon,
                                    mapName = matchDetails!!.data!!.data.metadata.map,
                                    matchLength = matchLengthFormatted,
                                    matchDate = datetimeString
                                )
                                Spacer(Modifier.padding(16.dp))
                                MatchRounds(matchDetails!!.data!!.data.rounds, sharedViewModel)
                                Spacer(Modifier.padding(16.dp))
                            }
                            MatchPlayers(
                                players = matchDetails!!.data!!.data.players.allPlayers.sortedByDescending { it.stats.score },
                                amountOfRounds = matchDetails!!.data!!.data.rounds.size,
                                sharedViewModel = sharedViewModel
                            )
                        }
                    }
                }
            },
            containerColor = MaterialTheme.colorScheme.backgroundColor
        )
    }
}


