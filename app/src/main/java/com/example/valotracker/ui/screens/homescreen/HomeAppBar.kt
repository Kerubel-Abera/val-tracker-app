package com.example.valotracker.ui.screens.homescreen

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ExitToApp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import com.example.valotracker.R
import com.example.valotracker.ui.theme.Gray
import com.example.valotracker.ui.theme.backgroundColor
import com.example.valotracker.ui.theme.textColor


/**
 * Composable function that displays the default home app bar.
 *
 * @param onExitClicked The callback function for the exit button click.
 * @param username The username to display in the app bar.
 * @param tag The tag to display in the app bar.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultHomeAppBar(
    onExitClicked: () -> Unit,
    username: String,
    tag: String
) {
    TopAppBar(
        title = {
            val userString = buildAnnotatedString {
                withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.textColor)) {
                    append(username)
                }
                withStyle(
                    style = SpanStyle(
                        color = Gray,
                        fontSize = MaterialTheme.typography.labelMedium.fontSize
                    )
                ) {
                    append("#$tag")
                }
            }

            Text(
                text = userString,
                fontWeight = FontWeight.ExtraBold
            )
        },
        actions = {
            HomeAppBarActions(onExitClicked)
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.backgroundColor
        )
    )
}


/**
 * Composable function that displays the actions in the home app bar.
 *
 * @param onExitClicked The callback function for the exit button click.
 */
@Composable
fun HomeAppBarActions(
    onExitClicked: () -> Unit
) {
    ExitAction(onExitClicked)
}


/**
 * Composable function that displays the exit button in the app bar.
 *
 * @param onExitClicked The callback function for the exit button click.
 */
@Composable
fun ExitAction(
    onExitClicked: () -> Unit
) {
    IconButton(
        onClick = { onExitClicked() }
    ) {
        Icon(
            imageVector = Icons.Rounded.ExitToApp,
            contentDescription = stringResource(R.string.exit_button),
            tint = MaterialTheme.colorScheme.textColor
        )
    }
}


@Composable
@Preview
fun DefaultHomeAppBarPreview() {
    DefaultHomeAppBar(onExitClicked = {}, username = "keru", tag = "sqdam")
}