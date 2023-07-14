package com.example.valotracker.data.models.statistics


import com.google.gson.annotations.SerializedName

data class MMRHistoryResponse(
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("name")
    val name: String,
    @SerializedName("status")
    val status: Int,
    @SerializedName("tag")
    val tag: String
) {
    data class Data(
        @SerializedName("currenttier")
        val currenttier: Int,
        @SerializedName("currenttierpatched")
        val currenttierpatched: String,
        @SerializedName("date")
        val date: String,
        @SerializedName("date_raw")
        val dateRaw: Int,
        @SerializedName("elo")
        val elo: Int,
        @SerializedName("images")
        val images: Images,
        @SerializedName("map")
        val map: Map,
        @SerializedName("match_id")
        val matchId: String,
        @SerializedName("mmr_change_to_last_game")
        val mmrChangeToLastGame: Int,
        @SerializedName("ranking_in_tier")
        val rankingInTier: Int,
        @SerializedName("season_id")
        val seasonId: String
    ) {
        data class Images(
            @SerializedName("large")
            val large: String,
            @SerializedName("small")
            val small: String,
            @SerializedName("triangle_down")
            val triangleDown: String,
            @SerializedName("triangle_up")
            val triangleUp: String
        )

        data class Map(
            @SerializedName("id")
            val id: String,
            @SerializedName("name")
            val name: String
        )
    }
}