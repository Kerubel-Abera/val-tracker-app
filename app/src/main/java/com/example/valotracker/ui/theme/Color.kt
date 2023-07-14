package com.example.valotracker.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)
val background = Color(0xFF303030)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

val AlmostBlack = Color(0xFF1C1C1C)

val Red = Color(0xFFFF4655)

val NegativeColorVariant = Color(0xFFcf3a46)
val PositiveColorVariant = Color(0xFF169265)
val NegativeColor = Color(0xFFFF4655)
val PositiveColor = Color(0xFF4FD2AB)

val DarkGray = Color(0xFF303030)
val Gray = Color(0xFF626262)
val MediumGray = Color(0xFFD9D9D9)
val LightGray = Color(0xFFF1F1F1)

val IronRankColor = Color(0xFF939393)
val BronzeRankColor = Color(0xFFb8946a)
val SilverRankColor = Color(0xFFc6cccb)
val GoldRankColor = Color(0xFFefd05c)
val PlatinumRankColor = Color(0xFF3ba0b0)
val DiamondRankColor = Color(0xFFc789f7)
val AscendantRankColor = Color(0xFFb8fed6)
val ImmortalRankColor = Color(0xFFac2736)
val RadiantRankColor = Color(0xFFfefefc)

val ColorScheme.backgroundColor: Color
    @Composable
    get() = if (isSystemInDarkTheme()) DarkGray else LightGray


val ColorScheme.textColor: Color
    @Composable
    get() = if (isSystemInDarkTheme()) Color.White else Color.Black

val ColorScheme.textFieldBackgroundColor: Color
    @Composable
    get() = if (isSystemInDarkTheme()) AlmostBlack else MediumGray

val ColorScheme.textFieldFocusedLabelColor: Color
    @Composable
    get() = if (isSystemInDarkTheme()) Color.White else Color.Black

