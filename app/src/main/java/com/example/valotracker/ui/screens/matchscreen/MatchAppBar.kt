package com.example.valotracker.ui.screens.matchscreen

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.example.valotracker.R
import com.example.valotracker.ui.theme.backgroundColor

/**
 * Composable function that displays the default app bar for the match screen.
 *
 * @param navigateBack The function to navigate back.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultMatchAppBar(
    navigateBack: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(R.string.match_details),
                fontWeight = FontWeight.ExtraBold
            )
        },
        navigationIcon = { BackButton(navigateBack) },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.backgroundColor
        )
    )
}

/**
 * Composable function that displays the back button.
 *
 * @param navigateBack The function to navigate back.
 */
@Composable
fun BackButton(
    navigateBack: () -> Unit
) {
    IconButton(
        onClick = { navigateBack() }) {
        Icon(
            imageVector = Icons.Filled.ArrowBack,
            contentDescription = null
        )
    }
}