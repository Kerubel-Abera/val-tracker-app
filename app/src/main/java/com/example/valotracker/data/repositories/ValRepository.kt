package com.example.valotracker.data.repositories

import com.example.valotracker.api.ApiHelper
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class ValRepository @Inject constructor(
    private val apiHelper: ApiHelper
) {
    suspend fun getPlayer(
        name: String,
        tag: String
    ) = apiHelper.getPlayer(name, tag)

    suspend fun getMatchHistory(
        region: String,
        name: String,
        tag: String
    ) = apiHelper.getMatchHistory(region, name, tag)

    suspend fun getMmr(
        region: String,
        name: String,
        tag: String
    ) = apiHelper.getMmr(region, name, tag)

    suspend fun getLifetimeMatches(
        region: String,
        name: String,
        tag: String,
        mode: String,
        size: Int
    ) = apiHelper.getLifetimeMatches(region, name, tag, mode, size)

    suspend fun getMatchDetails(
        matchId: String
    ) = apiHelper.getMatchDetails(matchId)
}