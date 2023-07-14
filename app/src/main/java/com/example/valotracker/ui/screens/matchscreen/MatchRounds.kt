package com.example.valotracker.ui.screens.matchscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.valotracker.R
import com.example.valotracker.data.models.match.MatchDetailsResponse.Data.Round
import com.example.valotracker.ui.theme.MEDIUM_PADDING
import com.example.valotracker.ui.theme.SMALL_PADDING
import com.example.valotracker.ui.theme.TASK_ITEM_CORNERSHAPE
import com.example.valotracker.ui.theme.textColor
import com.example.valotracker.ui.theme.textFieldBackgroundColor
import com.example.valotracker.ui.viewmodels.SharedViewModel
import com.example.valotracker.util.RoundType


/**
 * Composable function that displays the list of match rounds.
 *
 * @param rounds The list of match rounds.
 * @param sharedViewModel The ViewModel shared by the screen's components.
 */
@Composable
fun MatchRounds(
    rounds: List<Round>,
    sharedViewModel: SharedViewModel
){
    Column(
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            modifier = Modifier.padding(bottom = MEDIUM_PADDING),
            text = stringResource(R.string.rounds),
            color = MaterialTheme.colorScheme.textColor,
            fontWeight = FontWeight.ExtraBold
        )
        LazyRow {
            items(rounds.size) { roundIndex ->
                val roundType = sharedViewModel.getRoundTypeImageLink(
                    rounds[roundIndex].endType,
                    rounds[roundIndex].winningTeam
                )

                MatchRoundItem(roundNumber = roundIndex + 1, roundType = roundType)
                Spacer(Modifier.padding(4.dp))
            }
        }
    }

}

/**
 * Composable function that displays a single match round item.
 *
 * @param roundNumber The number of the round.
 * @param roundType The image link for the round type.
 */
@Composable
fun MatchRoundItem(
    roundNumber: Int,
    roundType: String
){
    Column(
        modifier = Modifier
            .background(
                MaterialTheme.colorScheme.textFieldBackgroundColor,
                RoundedCornerShape(TASK_ITEM_CORNERSHAPE * 2)
            )
            .padding(SMALL_PADDING),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = roundNumber.toString(),
            color = MaterialTheme.colorScheme.textColor
        )
        AsyncImage(
            modifier = Modifier.size(30.dp),
            model = roundType,
            contentDescription = stringResource(R.string.round_type_icon)
        )
    }
}

@Composable
@Preview(showBackground = true)
fun MatchRoundItemPreview(){
    //MatchRoundItem(1)
}