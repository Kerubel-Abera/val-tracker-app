package com.example.valotracker.ui.screens.homescreen

import androidx.annotation.DrawableRes
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import com.example.valotracker.R
import com.example.valotracker.data.models.statistics.MmrDetailsResponse
import com.example.valotracker.ui.theme.MEDIUM_PADDING
import com.example.valotracker.ui.theme.NegativeColor
import com.example.valotracker.ui.theme.PROGRESSBAR_CORNERSHAPE
import com.example.valotracker.ui.theme.PROGRESSBAR_HEIGHT
import com.example.valotracker.ui.theme.PositiveColor
import com.example.valotracker.ui.theme.SCREEN_CONSTRAINT
import com.example.valotracker.ui.theme.SMALL_PADDING
import com.example.valotracker.ui.theme.textColor
import com.example.valotracker.ui.theme.textFieldBackgroundColor
import com.example.valotracker.ui.viewmodels.SharedViewModel

/**
 * Composable function that displays the rank information.
 *
 * @param mmrDetailsResponse The MMR details response containing rank information.
 * @param rankColor The color associated with the rank.
 * @param paddingValues The padding values for the layout.
 * @param sharedViewModel The shared view model instance.
 */
@Composable
fun Rank(
    mmrDetailsResponse: MmrDetailsResponse?,
    rankColor: Color,
    paddingValues: PaddingValues,
    sharedViewModel: SharedViewModel
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = paddingValues.calculateTopPadding(),
                bottom = SCREEN_CONSTRAINT + SCREEN_CONSTRAINT
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        AsyncImage(
            model = mmrDetailsResponse?.data?.currentData?.images?.large
                ?: stringResource(R.string.empty_placeholder),
            contentDescription = stringResource(R.string.rank_image),
            placeholder = debugPlaceholder(debugPreview = R.drawable.valorant_emblem)
        )

        Text(
            modifier = Modifier
                .padding(bottom = MEDIUM_PADDING),
            text = mmrDetailsResponse?.data?.currentData?.currenttierpatched?.uppercase()
                ?: stringResource(
                    R.string.rank_placeholder
                ),
            fontSize = MaterialTheme.typography.displayMedium.fontSize,
            fontWeight = FontWeight.ExtraBold,
            color = rankColor
        )

        RankRatingBar(
            rankColor = rankColor,
            RR = mmrDetailsResponse?.data?.currentData?.rankingInTier ?: 50,
            RRDifference = mmrDetailsResponse?.data?.currentData?.mmrChangeToLastGame ?: 0,
            sharedViewModel = sharedViewModel
        )

    }
}


/**
 * Composable function that displays the custom rank rating bar.
 *
 * @param rankColor The color associated with the rank.
 * @param RR The current rank rating.
 * @param RRDifference The difference in rank rating from the last game.
 * @param sharedViewModel The shared view model instance.
 */
@Composable
private fun RankRatingBar(
    rankColor: Color,
    RR: Int,
    RRDifference: Int,
    sharedViewModel: SharedViewModel
) {
    val diffColor = sharedViewModel.getOutcomeColor(
        PositiveColor,
        NegativeColor,
        NegativeColor,
        RRDifference.toFloat(),
        0f
    )

    val (widthFirstBox, widthSecondBox) = sharedViewModel.getBoxWidths(RR, RRDifference)


    val animatedWidth =
        if (RRDifference >= 0) remember { Animatable(widthSecondBox) } else remember {
            Animatable(widthFirstBox)
        }
    val targetBox = if (RRDifference >= 0) widthFirstBox else widthSecondBox

    var initialRR by remember { mutableIntStateOf(if (RR - RRDifference < 0) 0 else RR - RRDifference) }

    val RRCounter by animateIntAsState(
        targetValue = initialRR,
        animationSpec = tween(
            durationMillis = 2000,
            delayMillis = 1000,
            easing = FastOutSlowInEasing
        )
    )

    LaunchedEffect(Unit) {
        initialRR = RR
    }

    LaunchedEffect(animatedWidth) {
        animatedWidth.animateTo(
            targetValue = targetBox,
            animationSpec = tween(
                durationMillis = 2000,
                delayMillis = 1000,
                easing = FastOutSlowInEasing
            )
        )
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(PROGRESSBAR_HEIGHT)
            .clip(RoundedCornerShape(PROGRESSBAR_CORNERSHAPE))
            .background(color = MaterialTheme.colorScheme.textFieldBackgroundColor)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(
                    if (RRDifference >= 0) {
                        widthFirstBox
                    } else {
                        animatedWidth.value
                    }
                )
                .fillMaxHeight()
                .clip(RoundedCornerShape(PROGRESSBAR_CORNERSHAPE))
                .background(color = diffColor)
        )
        Box(
            modifier = Modifier
                .fillMaxWidth(
                    if (RRDifference >= 0) {
                        animatedWidth.value
                    } else {
                        widthSecondBox
                    }
                )
                .fillMaxHeight()
                .clip(RoundedCornerShape(PROGRESSBAR_CORNERSHAPE))
                .background(color = rankColor)
        )

    }

    RankRatingNumbers(rankColor = rankColor, rankingInTier = RRCounter)
}

/**
 * Composable function that displays the rank rating numbers.
 *
 * @param rankColor The color associated with the rank.
 * @param rankingInTier The ranking within the current tier.
 */
@Composable
private fun RankRatingNumbers(
    rankColor: Color,
    rankingInTier: Int
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = SMALL_PADDING),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = stringResource(R.string.rank_rating),
            color = MaterialTheme.colorScheme.textColor,
            fontWeight = FontWeight.ExtraBold,
            fontSize = MaterialTheme.typography.labelMedium.fontSize
        )

        val rankRatingString = buildAnnotatedString {
            withStyle(style = SpanStyle(color = rankColor)) {
                append(rankingInTier.toString())
            }
            withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.textColor)) {
                append(stringResource(R.string.rank_rating_max))
            }
        }

        Text(
            text = rankRatingString,
            fontWeight = FontWeight.ExtraBold,
            fontSize = MaterialTheme.typography.labelMedium.fontSize
        )

    }
}

@Composable
@Preview
fun RankRatingBarPreview() {
    //RankRatingBar(rankColor = GoldRankColor, RR = 50, RRDifference = 15, sharedViewModel = )
}

@Composable
fun debugPlaceholder(@DrawableRes debugPreview: Int) =
    if (LocalInspectionMode.current) {
        painterResource(id = debugPreview)
    } else {
        null
    }