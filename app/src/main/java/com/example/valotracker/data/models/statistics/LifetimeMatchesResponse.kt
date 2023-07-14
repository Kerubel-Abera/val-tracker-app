package com.example.valotracker.data.models.statistics


import com.google.gson.annotations.SerializedName

data class LifetimeMatchesResponse(
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("name")
    val name: String,
    @SerializedName("results")
    val results: Results,
    @SerializedName("status")
    val status: Int,
    @SerializedName("tag")
    val tag: String
) {
    data class Data(
        @SerializedName("meta")
        val meta: Meta,
        @SerializedName("stats")
        val stats: Stats,
        @SerializedName("teams")
        val teams: Teams
    ) {
        data class Meta(
            @SerializedName("cluster")
            val cluster: String,
            @SerializedName("id")
            val id: String,
            @SerializedName("map")
            val map: Map,
            @SerializedName("mode")
            val mode: String,
            @SerializedName("region")
            val region: String,
            @SerializedName("season")
            val season: Season,
            @SerializedName("started_at")
            val startedAt: String,
            @SerializedName("version")
            val version: String
        ) {
            data class Map(
                @SerializedName("id")
                val id: String,
                @SerializedName("name")
                val name: String
            )

            data class Season(
                @SerializedName("id")
                val id: String,
                @SerializedName("short")
                val short: String
            )
        }

        data class Stats(
            @SerializedName("assists")
            val assists: Int,
            @SerializedName("character")
            val character: Character,
            @SerializedName("damage")
            val damage: Damage,
            @SerializedName("deaths")
            val deaths: Int,
            @SerializedName("kills")
            val kills: Int,
            @SerializedName("level")
            val level: Int,
            @SerializedName("puuid")
            val puuid: String,
            @SerializedName("score")
            val score: Int,
            @SerializedName("shots")
            val shots: Shots,
            @SerializedName("team")
            val team: String,
            @SerializedName("tier")
            val tier: Int
        ) {
            data class Character(
                @SerializedName("id")
                val id: String,
                @SerializedName("name")
                val name: String
            )

            data class Damage(
                @SerializedName("made")
                val made: Int,
                @SerializedName("received")
                val received: Int
            )

            data class Shots(
                @SerializedName("body")
                val body: Int,
                @SerializedName("head")
                val head: Int,
                @SerializedName("leg")
                val leg: Int
            )
        }

        data class Teams(
            @SerializedName("blue")
            val blue: Int,
            @SerializedName("red")
            val red: Int
        )
    }

    data class Results(
        @SerializedName("after")
        val after: Int,
        @SerializedName("before")
        val before: Int,
        @SerializedName("returned")
        val returned: Int,
        @SerializedName("total")
        val total: Int
    )
}