package com.example.valotracker.data.models.statistics


import com.google.gson.annotations.SerializedName

data class MmrDetailsResponse(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("status")
    val status: Int
) {
    data class Data(
        @SerializedName("by_season")
        val bySeason: BySeason,
        @SerializedName("current_data")
        val currentData: CurrentData,
        @SerializedName("highest_rank")
        val highestRank: HighestRank,
        @SerializedName("name")
        val name: String,
        @SerializedName("puuid")
        val puuid: String,
        @SerializedName("tag")
        val tag: String
    ) {
        data class BySeason(
            @SerializedName("e1a1")
            val e1a1: E1a1,
            @SerializedName("e1a2")
            val e1a2: E1a2,
            @SerializedName("e1a3")
            val e1a3: E1a3,
            @SerializedName("e2a1")
            val e2a1: E2a1,
            @SerializedName("e2a2")
            val e2a2: E2a2,
            @SerializedName("e2a3")
            val e2a3: E2a3,
            @SerializedName("e3a1")
            val e3a1: E3a1,
            @SerializedName("e3a2")
            val e3a2: E3a2,
            @SerializedName("e3a3")
            val e3a3: E3a3,
            @SerializedName("e4a1")
            val e4a1: E4a1,
            @SerializedName("e4a2")
            val e4a2: E4a2,
            @SerializedName("e4a3")
            val e4a3: E4a3,
            @SerializedName("e5a1")
            val e5a1: E5a1,
            @SerializedName("e5a2")
            val e5a2: E5a2,
            @SerializedName("e5a3")
            val e5a3: E5a3,
            @SerializedName("e6a1")
            val e6a1: E6a1,
            @SerializedName("e6a2")
            val e6a2: E6a2,
            @SerializedName("e6a3")
            val e6a3: E6a3,
            @SerializedName("e7a1")
            val e7a1: E7a1,
            @SerializedName("e7a2")
            val e7a2: E7a2,
            @SerializedName("e7a3")
            val e7a3: E7a3
        ) {
            data class E1a1(
                @SerializedName("error")
                val error: String
            )

            data class E1a2(
                @SerializedName("error")
                val error: String
            )

            data class E1a3(
                @SerializedName("error")
                val error: String
            )

            data class E2a1(
                @SerializedName("error")
                val error: String
            )

            data class E2a2(
                @SerializedName("error")
                val error: String
            )

            data class E2a3(
                @SerializedName("act_rank_wins")
                val actRankWins: List<ActRankWin>,
                @SerializedName("final_rank")
                val finalRank: Int,
                @SerializedName("final_rank_patched")
                val finalRankPatched: String,
                @SerializedName("number_of_games")
                val numberOfGames: Int,
                @SerializedName("old")
                val old: Boolean,
                @SerializedName("wins")
                val wins: Int
            ) {
                data class ActRankWin(
                    @SerializedName("patched_tier")
                    val patchedTier: String,
                    @SerializedName("tier")
                    val tier: Int
                )
            }

            data class E3a1(
                @SerializedName("act_rank_wins")
                val actRankWins: List<ActRankWin>,
                @SerializedName("final_rank")
                val finalRank: Int,
                @SerializedName("final_rank_patched")
                val finalRankPatched: String,
                @SerializedName("number_of_games")
                val numberOfGames: Int,
                @SerializedName("old")
                val old: Boolean,
                @SerializedName("wins")
                val wins: Int
            ) {
                data class ActRankWin(
                    @SerializedName("patched_tier")
                    val patchedTier: String,
                    @SerializedName("tier")
                    val tier: Int
                )
            }

            data class E3a2(
                @SerializedName("act_rank_wins")
                val actRankWins: List<ActRankWin>,
                @SerializedName("final_rank")
                val finalRank: Int,
                @SerializedName("final_rank_patched")
                val finalRankPatched: String,
                @SerializedName("number_of_games")
                val numberOfGames: Int,
                @SerializedName("old")
                val old: Boolean,
                @SerializedName("wins")
                val wins: Int
            ) {
                data class ActRankWin(
                    @SerializedName("patched_tier")
                    val patchedTier: String,
                    @SerializedName("tier")
                    val tier: Int
                )
            }

            data class E3a3(
                @SerializedName("act_rank_wins")
                val actRankWins: List<ActRankWin>,
                @SerializedName("final_rank")
                val finalRank: Int,
                @SerializedName("final_rank_patched")
                val finalRankPatched: String,
                @SerializedName("number_of_games")
                val numberOfGames: Int,
                @SerializedName("old")
                val old: Boolean,
                @SerializedName("wins")
                val wins: Int
            ) {
                data class ActRankWin(
                    @SerializedName("patched_tier")
                    val patchedTier: String,
                    @SerializedName("tier")
                    val tier: Int
                )
            }

            data class E4a1(
                @SerializedName("error")
                val error: String
            )

            data class E4a2(
                @SerializedName("act_rank_wins")
                val actRankWins: List<ActRankWin>,
                @SerializedName("final_rank")
                val finalRank: Int,
                @SerializedName("final_rank_patched")
                val finalRankPatched: String,
                @SerializedName("number_of_games")
                val numberOfGames: Int,
                @SerializedName("old")
                val old: Boolean,
                @SerializedName("wins")
                val wins: Int
            ) {
                data class ActRankWin(
                    @SerializedName("patched_tier")
                    val patchedTier: String,
                    @SerializedName("tier")
                    val tier: Int
                )
            }

            data class E4a3(
                @SerializedName("act_rank_wins")
                val actRankWins: List<ActRankWin>,
                @SerializedName("final_rank")
                val finalRank: Int,
                @SerializedName("final_rank_patched")
                val finalRankPatched: String,
                @SerializedName("number_of_games")
                val numberOfGames: Int,
                @SerializedName("old")
                val old: Boolean,
                @SerializedName("wins")
                val wins: Int
            ) {
                data class ActRankWin(
                    @SerializedName("patched_tier")
                    val patchedTier: String,
                    @SerializedName("tier")
                    val tier: Int
                )
            }

            data class E5a1(
                @SerializedName("act_rank_wins")
                val actRankWins: List<ActRankWin>,
                @SerializedName("final_rank")
                val finalRank: Int,
                @SerializedName("final_rank_patched")
                val finalRankPatched: String,
                @SerializedName("number_of_games")
                val numberOfGames: Int,
                @SerializedName("old")
                val old: Boolean,
                @SerializedName("wins")
                val wins: Int
            ) {
                data class ActRankWin(
                    @SerializedName("patched_tier")
                    val patchedTier: String,
                    @SerializedName("tier")
                    val tier: Int
                )
            }

            data class E5a2(
                @SerializedName("act_rank_wins")
                val actRankWins: List<ActRankWin>,
                @SerializedName("final_rank")
                val finalRank: Int,
                @SerializedName("final_rank_patched")
                val finalRankPatched: String,
                @SerializedName("number_of_games")
                val numberOfGames: Int,
                @SerializedName("old")
                val old: Boolean,
                @SerializedName("wins")
                val wins: Int
            ) {
                data class ActRankWin(
                    @SerializedName("patched_tier")
                    val patchedTier: String,
                    @SerializedName("tier")
                    val tier: Int
                )
            }

            data class E5a3(
                @SerializedName("act_rank_wins")
                val actRankWins: List<ActRankWin>,
                @SerializedName("final_rank")
                val finalRank: Int,
                @SerializedName("final_rank_patched")
                val finalRankPatched: String,
                @SerializedName("number_of_games")
                val numberOfGames: Int,
                @SerializedName("old")
                val old: Boolean,
                @SerializedName("wins")
                val wins: Int
            ) {
                data class ActRankWin(
                    @SerializedName("patched_tier")
                    val patchedTier: String,
                    @SerializedName("tier")
                    val tier: Int
                )
            }

            data class E6a1(
                @SerializedName("act_rank_wins")
                val actRankWins: List<ActRankWin>,
                @SerializedName("final_rank")
                val finalRank: Int,
                @SerializedName("final_rank_patched")
                val finalRankPatched: String,
                @SerializedName("number_of_games")
                val numberOfGames: Int,
                @SerializedName("old")
                val old: Boolean,
                @SerializedName("wins")
                val wins: Int
            ) {
                data class ActRankWin(
                    @SerializedName("patched_tier")
                    val patchedTier: String,
                    @SerializedName("tier")
                    val tier: Int
                )
            }

            data class E6a2(
                @SerializedName("act_rank_wins")
                val actRankWins: List<ActRankWin>,
                @SerializedName("final_rank")
                val finalRank: Int,
                @SerializedName("final_rank_patched")
                val finalRankPatched: String,
                @SerializedName("number_of_games")
                val numberOfGames: Int,
                @SerializedName("old")
                val old: Boolean,
                @SerializedName("wins")
                val wins: Int
            ) {
                data class ActRankWin(
                    @SerializedName("patched_tier")
                    val patchedTier: String,
                    @SerializedName("tier")
                    val tier: Int
                )
            }

            data class E6a3(
                @SerializedName("act_rank_wins")
                val actRankWins: List<ActRankWin>,
                @SerializedName("final_rank")
                val finalRank: Int,
                @SerializedName("final_rank_patched")
                val finalRankPatched: String,
                @SerializedName("number_of_games")
                val numberOfGames: Int,
                @SerializedName("old")
                val old: Boolean,
                @SerializedName("wins")
                val wins: Int
            ) {
                data class ActRankWin(
                    @SerializedName("patched_tier")
                    val patchedTier: String,
                    @SerializedName("tier")
                    val tier: Int
                )
            }

            data class E7a1(
                @SerializedName("error")
                val error: String
            )

            data class E7a2(
                @SerializedName("error")
                val error: String
            )

            data class E7a3(
                @SerializedName("error")
                val error: String
            )
        }

        data class CurrentData(
            @SerializedName("currenttier")
            val currenttier: Int,
            @SerializedName("currenttierpatched")
            val currenttierpatched: String,
            @SerializedName("elo")
            val elo: Int,
            @SerializedName("games_needed_for_rating")
            val gamesNeededForRating: Int,
            @SerializedName("images")
            val images: Images,
            @SerializedName("mmr_change_to_last_game")
            val mmrChangeToLastGame: Int,
            @SerializedName("old")
            val old: Boolean,
            @SerializedName("ranking_in_tier")
            val rankingInTier: Int
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
        }

        data class HighestRank(
            @SerializedName("old")
            val old: Boolean,
            @SerializedName("patched_tier")
            val patchedTier: String,
            @SerializedName("season")
            val season: String,
            @SerializedName("tier")
            val tier: Int
        )
    }
}