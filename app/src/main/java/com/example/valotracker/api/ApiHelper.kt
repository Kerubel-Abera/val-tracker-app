package com.example.valotracker.api

import com.example.valotracker.data.models.match.MatchDetailsResponse
import com.example.valotracker.data.models.player.PlayerResponse
import com.example.valotracker.data.models.statistics.LifetimeMatchesResponse
import com.example.valotracker.data.models.statistics.MMRHistoryResponse
import com.example.valotracker.data.models.statistics.MmrDetailsResponse
import retrofit2.Response
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiHelper {
    suspend fun getPlayer(
        name: String,
        tag: String
    ): Response<PlayerResponse>

    suspend fun getMatchHistory(
        region: String,
        name: String,
        tag: String
    ): Response<MMRHistoryResponse>

    suspend fun getMmr(
        region: String,
        name: String,
        tag: String
    ): Response<MmrDetailsResponse>

    suspend fun getLifetimeMatches(
        region: String,
        name: String,
        tag: String,
        mode: String,
        size: Int
    ): Response<LifetimeMatchesResponse>

    suspend fun getMatchDetails(
        matchId: String
    ): Response<MatchDetailsResponse>
}