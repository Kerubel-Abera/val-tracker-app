package com.example.valotracker.api

import com.example.valotracker.data.models.match.MatchDetailsResponse
import com.example.valotracker.data.models.player.PlayerResponse
import com.example.valotracker.data.models.statistics.LifetimeMatchesResponse
import com.example.valotracker.data.models.statistics.MMRHistoryResponse
import com.example.valotracker.data.models.statistics.MmrDetailsResponse
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(
    private val apiService: ApiService
): ApiHelper {
    override suspend fun getPlayer(name: String, tag: String): Response<PlayerResponse> =
        apiService.getPlayer(name, tag)

    override suspend fun getMatchHistory(
        region: String,
        name: String,
        tag: String
    ): Response<MMRHistoryResponse> =
        apiService.getMatchHistory(region, name, tag)

    override suspend fun getMmr(
        region: String,
        name: String,
        tag: String
    ): Response<MmrDetailsResponse> =
        apiService.getMmr(region, name, tag)

    override suspend fun getLifetimeMatches(
        region: String,
        name: String,
        tag: String,
        mode: String,
        size: Int
    ): Response<LifetimeMatchesResponse> =
        apiService.getLifetimeMatches(region, name, tag, mode, size)

    override suspend fun getMatchDetails(
        matchId: String
    ): Response<MatchDetailsResponse> =
        apiService.getMatchDetails(matchId)
}