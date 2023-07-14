package com.example.valotracker.ui.screens.matchscreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.valotracker.R
import com.example.valotracker.ui.theme.LARGE_PADDING
import com.example.valotracker.ui.theme.NegativeColor
import com.example.valotracker.ui.theme.PositiveColor
import com.example.valotracker.ui.theme.textColor


/**
 * Composable function that displays the header of the match details.
 *
 * @param hasWon The result of the match. `true` for victory, `false` for defeat, `null` for draw.
 * @param blueScore The score of the blue team.
 * @param redScore The score of the red team.
 * @param mapName The name of the map.
 * @param matchLength The duration of the match.
 * @param matchDate The date of the match.
 */
@Composable
fun MatchHeader(
    hasWon: Boolean?,
    blueScore: Int,
    redScore: Int,
    mapName: String,
    matchLength: String,
    matchDate: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = LARGE_PADDING, end = LARGE_PADDING),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom
        ) {
            ScoreHeaderText(text = "$mapName | $matchLength | $matchDate")
        }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {
            ScoreTitleText(text = "$blueScore ", color = PositiveColor)

            val hasWonText = when (hasWon) {
                true -> stringResource(R.string.victory)
                false -> stringResource(R.string.defeat)
                else -> stringResource(R.string.draw)
            }

            ScoreTitleText(
                text = "$hasWonText ",
                color = MaterialTheme.colorScheme.textColor
            )

            ScoreTitleText(text = "$redScore", color = NegativeColor)
        }
    }
}

/**
 * Composable function that displays the score header text.
 *
 * @param text The text to display.
 */
@Composable
fun ScoreHeaderText(text: String) {
    Text(
        text = text,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.textColor,
        maxLines = 1
    )
}

/**
 * Composable function that displays the score title text.
 *
 * @param text The text to display.
 * @param color The color of the text.
 */
@Composable
fun ScoreTitleText(text: String, color: Color) {
    Text(
        text = text,
        fontWeight = FontWeight.Bold,
        color = color,
        fontSize = MaterialTheme.typography.displaySmall.fontSize,
        textAlign = TextAlign.Center,
        maxLines = 1
    )
}

@Composable
@Preview(showBackground = true)
fun MatchHeaderPreview() {
    MatchHeader(
        true,
        13,
        11,
        "Fracture",
        "45m 45s",
        "12-07-2023"
    )
}