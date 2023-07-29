package com.example.valotracker.ui.screens.loginscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.valotracker.R
import com.example.valotracker.ui.theme.Gray
import com.example.valotracker.ui.theme.HUGE_PADDING
import com.example.valotracker.ui.theme.LARGER_PADDING
import com.example.valotracker.ui.theme.MEDIUM_PADDING
import com.example.valotracker.ui.theme.Red
import com.example.valotracker.ui.theme.SCREEN_CONSTRAINT
import com.example.valotracker.ui.theme.backgroundColor
import com.example.valotracker.ui.theme.textColor
import com.example.valotracker.ui.theme.textFieldBackgroundColor
import com.example.valotracker.ui.theme.textFieldFocusedLabelColor
import com.example.valotracker.ui.viewmodels.SharedViewModel
import com.example.valotracker.util.Constants.MAX_TAG_CHARACTERS
import com.example.valotracker.util.Constants.MAX_USERNAME_CHARACTERS
import com.example.valotracker.util.Status

// TODO: Remake the login screen.


/**
 * Composable function that displays the login screen.
 *
 * @param navigateToHomeScreen The callback to navigate to the home screen.
 * @param sharedViewModel The shared view model instance.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    navigateToHomeScreen: () -> Unit,
    sharedViewModel: SharedViewModel
) {
    val player by sharedViewModel.player.observeAsState()
    var username by remember { mutableStateOf("") }
    var tag by remember { mutableStateOf("") }


    Row(
        Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.backgroundColor),
        verticalAlignment = Alignment.CenterVertically
    )
    {
        Column(
            Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){

            Image(
                painter = painterResource(id = R.drawable.valorant_emblem),
                contentDescription = stringResource(R.string.valorant_icon),
                modifier = Modifier
                    .size(100.dp)
                    .align(alignment = Alignment.CenterHorizontally)
                    .padding(bottom = LARGER_PADDING)
            )


            Text(
                text = stringResource(R.string.search_user_statistics),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                color = MaterialTheme.colorScheme.textColor,
                fontSize = MaterialTheme.typography.headlineSmall.fontSize,
                fontWeight = FontWeight.Bold
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = MEDIUM_PADDING),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically)
            {

                TextField(
                    modifier = Modifier
                        .height(60.dp)
                        .fillMaxWidth(0.4f)
                        .background(
                            color = MaterialTheme.colorScheme.textFieldBackgroundColor,
                            shape = RoundedCornerShape(8.dp)
                        ),
                    value = username,
                    onValueChange = {
                        if (it.length <= MAX_USERNAME_CHARACTERS){
                            username = it
                        }
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                        focusedLabelColor = MaterialTheme.colorScheme.textFieldFocusedLabelColor,
                        unfocusedLabelColor = Gray
                    ),
                    label = {
                        Text(
                            text = stringResource(R.string.username_label),
                            fontWeight = FontWeight.Bold,
                            fontSize = MaterialTheme.typography.labelLarge.fontSize)
                    },
                    singleLine = true
                )


                Text(
                    text = "#",
                    color = Color.White,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = MEDIUM_PADDING)
                )

                TextField(
                    modifier = Modifier
                        .height(60.dp)
                        .fillMaxWidth(0.45f)
                        .background(
                            color = MaterialTheme.colorScheme.textFieldBackgroundColor,
                            shape = RoundedCornerShape(8.dp)
                        ),
                    value = tag,
                    onValueChange = {
                        if(it.length <= MAX_TAG_CHARACTERS) {
                            tag = it
                        }
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                        focusedLabelColor = MaterialTheme.colorScheme.textFieldFocusedLabelColor,
                        unfocusedLabelColor = Gray
                    ),
                    label = {
                        Text(
                            text = stringResource(R.string.tag_placeholder),
                            fontWeight = FontWeight.Bold,
                            fontSize = MaterialTheme.typography.labelLarge.fontSize)
                    },
                    singleLine = true
                )

            }

            Column(
                modifier = Modifier
                    .padding(top = HUGE_PADDING)
                    .fillMaxWidth()
                    .height(150.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (player == null) {
                    LoginButton(
                        onClick = { sharedViewModel.getPlayer(username, tag) },
                        buttonIconId = R.drawable.ic_forward_login,
                        buttonColor = Red
                    )
                } else {
                    when (player!!.status) {
                        Status.SUCCESS -> {
                            LaunchedEffect(Unit){
                                navigateToHomeScreen()
                            }
                        }
                        Status.LOADING -> {
                            CircularProgressIndicator(
                                color = Red
                            )
                        }
                        Status.ERROR -> {
                            LoginButton(
                                onClick = { sharedViewModel.getPlayer(username, tag) },
                                buttonIconId = R.drawable.ic_forward_login,
                                buttonColor = Red
                            )

                            Text(
                                modifier = Modifier
                                    .padding(
                                        top = MEDIUM_PADDING,
                                        start = SCREEN_CONSTRAINT,
                                        end = SCREEN_CONSTRAINT
                                    ),
                                text = player!!.message ?: "Unknown Error",
                                color = Color.Red)
                        }
                    }
                }
            }

        }
    }
}

/**
 * Composable function that displays the login button.
 *
 * @param onClick The callback when the button is clicked.
 * @param buttonIconId The icon resource ID for the button.
 * @param buttonColor The color for the button.
 */
@Composable
fun LoginButton(
    onClick: () -> Unit,
    buttonIconId: Int,
    buttonColor: Color
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = buttonColor),
    ) {
        Icon(
            painter = painterResource(id = buttonIconId),
            contentDescription = stringResource(R.string.forward_icon),
            tint = Color.White
        )
    }
}


//@Composable
//@Preview
//fun LoginScreenPreview(){
//    LoginScreen(navigateToHomeScreen = {})
//}
