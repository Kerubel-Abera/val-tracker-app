package com.example.valotracker.api

import com.example.valotracker.data.models.match.MatchDetailsResponse
import com.example.valotracker.data.models.player.PlayerResponse
import com.example.valotracker.data.models.statistics.LifetimeMatchesResponse
import com.example.valotracker.data.models.statistics.MMRHistoryResponse
import com.example.valotracker.data.models.statistics.MmrDetailsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("valorant/v1/account/{name}/{tag}")
    suspend fun getPlayer(
        @Path("name") name: String,
        @Path("tag") tag: String
    ): Response<PlayerResponse>

    @GET("valorant/v1/mmr-history/{region}/{name}/{tag}")
    suspend fun getMatchHistory(
        @Path("region") region: String,
        @Path("name") name: String,
        @Path("tag") tag: String
    ): Response<MMRHistoryResponse>

    @GET("valorant/v2/mmr/{region}/{name}/{tag}")
    suspend fun getMmr(
        @Path("region") region: String,
        @Path("name") name: String,
        @Path("tag") tag: String
    ): Response<MmrDetailsResponse>

    @GET("valorant/v1/lifetime/matches/{region}/{name}/{tag}")
    suspend fun getLifetimeMatches(
        @Path("region") region: String,
        @Path("name") name: String,
        @Path("tag") tag: String,
        @Query("mode") mode: String,
        @Query("size") size: Int
    ): Response<LifetimeMatchesResponse>

    @GET("valorant/v2/match/{matchId}")
    suspend fun getMatchDetails(
        @Path("matchId") matchId: String
    ): Response<MatchDetailsResponse>

}