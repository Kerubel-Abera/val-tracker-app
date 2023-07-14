package com.example.valotracker.data.models.player

data class Player(
    val account_level: Int,
    val card: Card,
    val last_update: String,
    val last_update_raw: Int,
    val name: String,
    val puuid: String,
    val region: String,
    val tag: String
)