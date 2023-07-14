package com.example.valotracker.ui.screens.homescreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.valotracker.R
import com.example.valotracker.data.models.statistics.LifetimeMatchesResponse
import com.example.valotracker.data.models.statistics.MMRHistoryResponse
import com.example.valotracker.ui.theme.LARGE_PADDING
import com.example.valotracker.ui.theme.NegativeColor
import com.example.valotracker.ui.theme.PositiveColor
import com.example.valotracker.ui.theme.SMALL_PADDING
import com.example.valotracker.ui.theme.TASK_ITEM_CORNERSHAPE
import com.example.valotracker.ui.theme.textColor
import com.example.valotracker.ui.theme.textFieldBackgroundColor
import com.example.valotracker.ui.viewmodels.SharedViewModel
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale

/**
 * Composable function that displays the match history.
 *
 * @param lifetimeMatchesResponse The lifetime matches response containing match history data.
 * @param mmrHistoryResponse The MMR history response containing MMR change data.
 * @param sharedViewModel The shared view model instance.
 * @param navigateToMatchScreen The function to navigate to the match screen.
 */
@Composable
fun MatchHistory(
    lifetimeMatchesResponse: LifetimeMatchesResponse,
    mmrHistoryResponse: MMRHistoryResponse,
    sharedViewModel: SharedViewModel,
    navigateToMatchScreen: (String) -> Unit
) {

    val listSize = minOf(lifetimeMatchesResponse.data.size, mmrHistoryResponse.data.size)

    LazyColumn {
        items(listSize) { index ->
            MatchHistoryItem(
                lifetimeMatchesResponse,
                mmrHistoryResponse,
                sharedViewModel,
                index,
                navigateToMatchScreen
            )
            Spacer(
                modifier = Modifier.padding(SMALL_PADDING)
            )
        }
    }
}


/**
 * Composable function that displays a single match history item.
 *
 * @param lifetimeMatchesResponse The lifetime matches response containing match history data.
 * @param mmrHistoryResponse The MMR history response containing MMR change data.
 * @param sharedViewModel The shared view model instance.
 * @param matchNumber The index of the match in the history.
 * @param navigateToMatchScreen The function to navigate to the match screen.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MatchHistoryItem(
    lifetimeMatchesResponse: LifetimeMatchesResponse,
    mmrHistoryResponse: MMRHistoryResponse,
    sharedViewModel: SharedViewModel,
    matchNumber: Int,
    navigateToMatchScreen: (String) -> Unit
) {
    val fontSizeBottomText = MaterialTheme.typography.titleMedium.fontSize
    val (playerTeamScore, enemyTeamScore) = sharedViewModel.getTeamScores(
        lifetimeMatchesResponse,
        matchNumber
    )

    val hasWonColor = sharedViewModel.getOutcomeColor(
        PositiveColor,
        NegativeColor,
        MaterialTheme.colorScheme.textColor,
        playerTeamScore.toFloat(),
        enemyTeamScore.toFloat()
    )

    Box {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp),
            color = MaterialTheme.colorScheme.textFieldBackgroundColor,
            shape = RoundedCornerShape(TASK_ITEM_CORNERSHAPE),
            onClick = {
                navigateToMatchScreen(lifetimeMatchesResponse.data[matchNumber].meta.id)
            }
        ) {
            Row(
                modifier = Modifier
                    .padding(all = LARGE_PADDING)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AsyncImage(
                        modifier = Modifier.size(80.dp),
                        model = sharedViewModel.getAgentIcon(lifetimeMatchesResponse.data[matchNumber].stats.character.name),
                        contentDescription = stringResource(R.string.rank_image),
                        placeholder = debugPlaceholder(debugPreview = R.drawable.valorant_emblem)
                    )
                    Column(
                        verticalArrangement = Arrangement.Center
                    ) {
                        val matchRounds = annotatedScoresString(playerTeamScore, enemyTeamScore)
                        Text(
                            text = matchRounds,
                            fontWeight = FontWeight.Normal,
                            fontSize = MaterialTheme.typography.titleLarge.fontSize
                        )
                        Text(
                            modifier = Modifier
                                .padding(bottom = SMALL_PADDING),
                            text = lifetimeMatchesResponse.data[matchNumber].meta.map.name,
                            fontWeight = FontWeight.Bold,
                            fontSize = fontSizeBottomText,
                            color = MaterialTheme.colorScheme.textColor,
                            maxLines = 1
                        )
                    }

                }


                Column(
                    modifier = Modifier
                        .fillMaxHeight(),
                    horizontalAlignment = Alignment.End,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        modifier = Modifier
                            .padding(bottom = SMALL_PADDING),
                        text = "K/D",
                        fontWeight = FontWeight.Normal,
                        fontSize = MaterialTheme.typography.titleSmall.fontSize,
                        color = MaterialTheme.colorScheme.textColor
                    )

                    val kd =
                        lifetimeMatchesResponse.data[matchNumber].stats.kills.toFloat() / lifetimeMatchesResponse.data[matchNumber].stats.deaths.toFloat()

                    val decimalFormatSymbols = DecimalFormatSymbols(Locale.getDefault()).apply {
                        decimalSeparator = ','
                    }

                    val decimalFormat = DecimalFormat("#.#", decimalFormatSymbols)
                    val kdRounded = decimalFormat.parse(decimalFormat.format(kd))?.toFloat() ?: kd

                    val kdColor = sharedViewModel.getOutcomeColor(
                        PositiveColor,
                        NegativeColor,
                        MaterialTheme.colorScheme.textColor,
                        kdRounded,
                        1.0F
                    )

                    Text(
                        text = kdRounded.toString(),
                        fontWeight = FontWeight.Bold,
                        fontSize = fontSizeBottomText,
                        color = kdColor,
                        maxLines = 1
                    )

                }

                Column(
                    modifier = Modifier
                        .fillMaxHeight(),
                    horizontalAlignment = Alignment.End,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        modifier = Modifier
                            .padding(bottom = SMALL_PADDING),
                        text = "RR",
                        fontWeight = FontWeight.Normal,
                        fontSize = MaterialTheme.typography.titleSmall.fontSize,
                        color = MaterialTheme.colorScheme.textColor
                    )

                    val rrGained = mmrHistoryResponse.data[matchNumber].mmrChangeToLastGame
                    val rrColor = sharedViewModel.getOutcomeColor(
                        PositiveColor,
                        NegativeColor,
                        MaterialTheme.colorScheme.textColor,
                        rrGained.toFloat(),
                        0.0f
                    )
                    val rrGainedString = if (rrGained >= 0) "+${rrGained}" else rrGained

                    Text(
                        text = rrGainedString.toString(),
                        fontWeight = FontWeight.Bold,
                        fontSize = fontSizeBottomText,
                        color = rrColor,
                        maxLines = 1
                    )
                }

                Column(
                    modifier = Modifier
                        .fillMaxHeight(),
                    horizontalAlignment = Alignment.End,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        modifier = Modifier
                            .padding(bottom = SMALL_PADDING),
                        text = "K / D / A",
                        fontWeight = FontWeight.Normal,
                        fontSize = MaterialTheme.typography.titleSmall.fontSize,
                        color = MaterialTheme.colorScheme.textColor
                    )
                    Text(
                        text = "${lifetimeMatchesResponse.data[matchNumber].stats.kills} / ${lifetimeMatchesResponse.data[matchNumber].stats.deaths} / ${lifetimeMatchesResponse.data[matchNumber].stats.assists}",
                        fontWeight = FontWeight.Bold,
                        fontSize = fontSizeBottomText,
                        color = MaterialTheme.colorScheme.textColor,
                        maxLines = 1
                    )
                }
            }
        }

        Spacer(
            modifier = Modifier
                .width(4.dp)
                .height(60.dp)
                .background(
                    color = hasWonColor,
                    shape = RoundedCornerShape(2.dp)
                )
                .align(Alignment.CenterStart)
                .offset(x = (-2).dp)
        )
    }
}

/**
 * Composable function that creates an [AnnotatedString] for displaying match scores.
 *
 * @param playersTeamScore The score of the player's team.
 * @param enemysTeamScore The score of the enemy team.
 * @return The [AnnotatedString] representing the match scores.
 */
@Composable
private fun annotatedScoresString(
    playersTeamScore: Int,
    enemysTeamScore: Int
): AnnotatedString {
    val matchRounds = buildAnnotatedString {
        withStyle(style = SpanStyle(color = PositiveColor)) {
            append(playersTeamScore.toString())
        }
        withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.textColor)) {
            append(" : ")
        }
        withStyle(style = SpanStyle(color = NegativeColor)) {
            append(enemysTeamScore.toString())
        }
    }
    return matchRounds
}

@Composable
@Preview
fun MatchHistoryItemPreview() {
    //MatchHistoryItem()
}