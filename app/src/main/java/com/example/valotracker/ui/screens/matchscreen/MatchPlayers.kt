package com.example.valotracker.ui.screens.matchscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.valotracker.R
import com.example.valotracker.data.models.match.MatchDetailsResponse.Data.Players.AllPlayer
import com.example.valotracker.ui.screens.homescreen.debugPlaceholder
import com.example.valotracker.ui.theme.LARGE_PADDING
import com.example.valotracker.ui.theme.MEDIUM_PADDING
import com.example.valotracker.ui.theme.NegativeColorVariant
import com.example.valotracker.ui.theme.PositiveColorVariant
import com.example.valotracker.ui.theme.TASK_ITEM_CORNERSHAPE
import com.example.valotracker.ui.theme.textColor
import com.example.valotracker.ui.theme.textFieldBackgroundColor
import com.example.valotracker.ui.viewmodels.SharedViewModel


/**
 * Composable function that displays the list of match players.
 *
 * @param players The list with all player data.
 * @param amountOfRounds The total number of rounds played.
 * @param sharedViewModel The ViewModel shared by the screen's components.
 */
@Composable
fun MatchPlayers(
    players: List<AllPlayer>,
    amountOfRounds: Int,
    sharedViewModel: SharedViewModel
) {
    Column(
        modifier = Modifier
            .background(
                MaterialTheme.colorScheme.textFieldBackgroundColor,
                RoundedCornerShape(
                    topStart = TASK_ITEM_CORNERSHAPE * 3,
                    topEnd = TASK_ITEM_CORNERSHAPE * 3
                )
            )
            .padding(
                top = LARGE_PADDING,
                start = LARGE_PADDING,
                end = LARGE_PADDING
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = LARGE_PADDING,
                    bottom = LARGE_PADDING,
                    end = LARGE_PADDING
                ),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                text = stringResource(R.string.player),
                color = MaterialTheme.colorScheme.textColor,
                fontWeight = FontWeight.ExtraBold,
                textAlign = TextAlign.Center
            )
            Text(
                modifier = Modifier
                    .weight(.5f)
                    .fillMaxWidth(),
                text = stringResource(R.string.avg_cs),
                color = MaterialTheme.colorScheme.textColor,
                fontWeight = FontWeight.ExtraBold,
                textAlign = TextAlign.Center
            )
            Text(
                modifier = Modifier
                    .weight(.5f)
                    .fillMaxWidth(),
                text = stringResource(R.string.kda),
                color = MaterialTheme.colorScheme.textColor,
                fontWeight = FontWeight.ExtraBold,
                textAlign = TextAlign.Center
            )
        }

        LazyColumn {
            items(players) { player ->
                MatchPlayerItem(player, amountOfRounds, sharedViewModel)
                Spacer(Modifier.padding(all = MEDIUM_PADDING))
            }
        }
    }
}

/**
 * Composable function that displays a single match player item.
 *
 * @param player The player data.
 * @param amountOfRounds The total number of rounds played.
 * @param sharedViewModel The ViewModel shared by the screen's components.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MatchPlayerItem(
    player: AllPlayer,
    amountOfRounds: Int,
    sharedViewModel: SharedViewModel
) {
    val backgroundColor = when (player.team) {
        stringResource(R.string.red) -> NegativeColorVariant
        stringResource(R.string.blue) -> PositiveColorVariant
        else -> PositiveColorVariant
    }

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp),
        color = backgroundColor,
        shape = RoundedCornerShape(TASK_ITEM_CORNERSHAPE),
        onClick = {}
    ) {
        Row(
            modifier = Modifier
                .padding(all = LARGE_PADDING)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                AsyncImage(
                    modifier = Modifier
                        .size(40.dp)
                        .padding(end = MEDIUM_PADDING),
                    model = sharedViewModel.getAgentIcon(player.character),
                    contentDescription = stringResource(R.string.character_image),
                    placeholder = debugPlaceholder(debugPreview = R.drawable.valorant_emblem)
                )
//                AsyncImage(
//                    modifier = Modifier.size(40.dp),
//                    model = player.currenttierPatched,
//                    contentDescription = stringResource(R.string.rank_image),
//                    placeholder = debugPlaceholder(debugPreview = R.drawable.valorant_emblem)
//                )
                Text(
                    text = player.name,
                    color = Color.White,
                    fontWeight = FontWeight.ExtraBold
                )
            }

            val avgCombatScore = (player.stats.score / amountOfRounds).toString()

            Text(
                modifier = Modifier
                    .weight(.5f)
                    .fillMaxWidth(),
                text = avgCombatScore,
                color = Color.White,
                fontWeight = FontWeight.ExtraBold,
                textAlign = TextAlign.Center
            )
            Text(
                modifier = Modifier
                    .weight(.5f)
                    .fillMaxWidth(),
                text = "${player.stats.kills} / ${player.stats.deaths} / ${player.stats.assists}",
                color = Color.White,
                fontWeight = FontWeight.ExtraBold,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview
@Composable
fun MatchPlayerItemPreview() {
    //MatchPlayerItem()
}