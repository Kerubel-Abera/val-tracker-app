package com.example.valotracker.ui.viewmodels

import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.util.Log
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.valotracker.R
import com.example.valotracker.data.models.match.MatchDetailsResponse
import com.example.valotracker.data.models.player.PlayerResponse
import com.example.valotracker.data.models.statistics.LifetimeMatchesResponse
import com.example.valotracker.data.models.statistics.MMRHistoryResponse
import com.example.valotracker.data.models.statistics.MmrDetailsResponse
import com.example.valotracker.data.repositories.ValRepository
import com.example.valotracker.ui.theme.AscendantRankColor
import com.example.valotracker.ui.theme.BronzeRankColor
import com.example.valotracker.ui.theme.DiamondRankColor
import com.example.valotracker.ui.theme.GoldRankColor
import com.example.valotracker.ui.theme.ImmortalRankColor
import com.example.valotracker.ui.theme.IronRankColor
import com.example.valotracker.ui.theme.PlatinumRankColor
import com.example.valotracker.ui.theme.SilverRankColor
import com.example.valotracker.util.Agent
import com.example.valotracker.util.Resource
import com.example.valotracker.util.RoundType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
    private val repository: ValRepository
): ViewModel() {

    private val _player = MutableLiveData<Resource<PlayerResponse>>()
    val player: LiveData<Resource<PlayerResponse>> = _player

    private val _matchHistory = MutableLiveData<Resource<MMRHistoryResponse>>()
    val matchHistory: LiveData<Resource<MMRHistoryResponse>> = _matchHistory

    private val _lifetimeMatchHistory = MutableLiveData<Resource<LifetimeMatchesResponse>>()
    val lifetimeMatchHistory: LiveData<Resource<LifetimeMatchesResponse>> = _lifetimeMatchHistory

    private val _mmr = MutableLiveData<Resource<MmrDetailsResponse>>()
    val mmr: LiveData<Resource<MmrDetailsResponse>> = _mmr

    private val _matchDetails = MutableLiveData<Resource<MatchDetailsResponse>>()
    val matchDetails: LiveData<Resource<MatchDetailsResponse>> = _matchDetails


    private val rankColors = mapOf(
        "iron" to IronRankColor,
        "bronze" to BronzeRankColor,
        "silver" to SilverRankColor,
        "gold" to GoldRankColor,
        "platinum" to PlatinumRankColor,
        "diamond" to DiamondRankColor,
        "ascendant" to AscendantRankColor,
        "radiant" to ImmortalRankColor
    )


    /**
     * Fetches player data from the repository.
     *
     * @param name The player's valorant name.
     * @param tag The player's valorant tag.
     */
    fun getPlayer(name: String, tag: String) = viewModelScope.launch {
        _player.value = Resource.loading(null)
        repository.getPlayer(name, tag).let {
            if (it.isSuccessful) {
                _player.value = Resource.success(it.body())
            } else {
                _player.value = Resource.error(it.errorBody().toString(), null)
            }
        }
    }


    /**
     * Fetches MMR data from the repository.
     */
    fun getMmrData() {
        viewModelScope.launch {
            _mmr.value = Resource.loading(null)
            val region = _player.value?.data?.data?.region ?: "eu"
            val name = _player.value?.data?.data?.name ?: "null"
            val tag = _player.value?.data?.data?.tag ?: "00000"
            Log.i("MatchHistory", "region: $region, name: $name, tag: $tag")
            repository.getMmr(region, name, tag).let {
                if (it.isSuccessful) {
                    _mmr.value = Resource.success(it.body())
                } else {
                    _mmr.value = Resource.error(it.errorBody().toString(), null)
                }
            }
        }
    }


    /**
     * Fetches match history data from the repository.
     */
    fun getHistoryData() {
        viewModelScope.launch {
            _matchHistory.value = Resource.loading(null)
            _lifetimeMatchHistory.value = Resource.loading(null)
            val region = _player.value?.data?.data?.region ?: "eu"
            val name = _player.value?.data?.data?.name ?: "null"
            val tag = _player.value?.data?.data?.tag ?: "00000"
            repository.getLifetimeMatches(region, name, tag, "competitive", 10).let {
                if (it.isSuccessful) {
                    _lifetimeMatchHistory.value = Resource.success(it.body())
                    Log.i("SharedViewModel", "SUCCESSSSSS ${lifetimeMatchHistory.value}")
                } else {
                    _lifetimeMatchHistory.value = Resource.error(it.errorBody().toString(), it.body())
                }
            }
            repository.getMatchHistory(region, name, tag).let {
                if (it.isSuccessful) {
                    _matchHistory.value = Resource.success(it.body())
                } else {
                    _matchHistory.value = Resource.error(it.errorBody().toString(), it.body())
                }
            }
        }
    }


    /**
     * Fetches match details from the repository.
     *
     * @param matchId The ID of the match.
     */
    fun getMatchDetails(matchId: String){
        viewModelScope.launch {
            Log.i("matchDetails", "LOADING")
            _matchDetails.value = Resource.loading(null)
            repository.getMatchDetails(matchId).let {
                if (it.isSuccessful) {
                    Log.i("matchDetails", "SUCCES")
                    _matchDetails.value = Resource.success(it.body())
                } else {
                    Log.i("matchDetails", "FAILURE")
                    _matchDetails.value = Resource.error(it.errorBody().toString(), it.body())
                }
            }
        }
    }


    /**
     * Returns the color associated with the given rank.
     *
     * @param rank The rank string.
     * @return The corresponding color for the rank.
     */
    fun getRankColor(rank: String): Color {
        return rankColors[rank.substringBefore(" ").lowercase()] ?: Color.White
    }


    /**
     * Converts a timestamp to a formatted string.
     *
     * @param timestamp The timestamp in milliseconds.
     * @return The formatted date string.
     */
    fun getTeamScores(lifetimeMatchesResponse: LifetimeMatchesResponse, matchNumber: Int): Pair<Int, Int> {
        val match = lifetimeMatchesResponse.data[matchNumber]
        return if (match.stats.team.lowercase() == "blue") {
            match.teams.blue to match.teams.red
        } else {
            match.teams.red to match.teams.blue
        }
    }


    /**
     * Calculates the widths of two boxes based on the given RR (Rank Rating) and RRDifference (Rank Rating Difference).
     *
     * @param RR The Rank Rating.
     * @param RRDifference The Rank Rating Difference.
     * @return A pair of floats representing the width of the first and second boxes.
     */
    fun getBoxWidths(RR: Int, RRDifference: Int): Pair<Float, Float> {
        val widthFirstBox = if (RRDifference >= 0) RR.toFloat() / 100 else (RR + (-RRDifference)).toFloat() / 100
        val widthSecondBox = if (RRDifference >= 0) (RR - RRDifference).toFloat() / 100 else RR.toFloat() / 100
        return widthFirstBox to widthSecondBox
    }


    /**
     * Determines the color based on two data values and associated colors.
     * If colorOneData is greater than colorTwoData, colorOne is returned.
     * If colorOneData is less than colorTwoData, colorTwo is returned.
     * If colorOneData is equal to colorTwoData, colorThree is returned.
     *
     * @param colorOne The color to be returned if colorOneData is greater.
     * @param colorTwo The color to be returned if colorOneData is less.
     * @param colorThree The color to be returned if colorOneData is equal to colorTwoData.
     * @param colorOneData The first data value.
     * @param colorTwoData The second data value.
     * @return The color based on the data values.
     */
    fun getOutcomeColor(
        colorOne: Color,
        colorTwo: Color,
        colorThree: Color,
        colorOneData: Float,
        colorTwoData: Float
    ): Color {
        return if (colorOneData > colorTwoData) colorOne
        else if (colorOneData < colorTwoData) colorTwo
        else colorThree
    }


    /**
     * Returns the image link for the round type and winning team combination.
     *
     * @param roundType The type of round.
     * @param winningTeam The team that won.
     * @return The image link for the round type and winning team combination.
     */
    fun getRoundTypeImageLink(
        roundType: String,
        winningTeam: String
    ): String {
        return when (roundType) {
            "Bomb defused" -> when (winningTeam) {
                "Blue" -> RoundType.DEFUSE_BLUE.imageLink
                else -> RoundType.DEFUSE_RED.imageLink
            }

            "Eliminated" -> when (winningTeam) {
                "Blue" -> RoundType.ELIMINATION_BLUE.imageLink
                else -> RoundType.ELIMINATION_RED.imageLink
            }

            "Round timer expired" -> when (winningTeam) {
                "Blue" -> RoundType.TIME_BLUE.imageLink
                else -> RoundType.TIME_RED.imageLink
            }

            "Bomb detonated" -> when (winningTeam) {
                "Blue" -> RoundType.EXPLOSION_BLUE.imageLink
                else -> RoundType.EXPLOSION_RED.imageLink
            }

            "Surrendered" -> RoundType.SURRENDER.imageLink
            else -> ""
        }
    }


    /**
     * Converts a timestamp to a formatted string.
     *
     * @param timestamp The timestamp in milliseconds.
     * @return The formatted date string.
     */
    fun convertTimestampToString(timestamp: Long): String {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = timestamp * 1000
        val formatter = SimpleDateFormat("dd-MM-yyyy | HH:mm", Locale.getDefault())
        return formatter.format(calendar.time)
    }

    /**
     * Logs out the user by resetting the LiveData values.
     */
    fun logout() {
        _player.value = null
        _mmr.value = null
        _matchHistory.value = null
    }


    /**
     * Retrieves the agent icon based on the agent name.
     * If there is a new unknown agent added, the default agent icon (Jett) is returned.
     *
     * @param agentName The name of the agent.
     * @return The image link for the agent icon.
     */
    fun getAgentIcon(agentName: String): String {
        val agent = Agent.values().find {
            it.name.equals(agentName, ignoreCase = true)
        }
        return agent?.imageLink ?: Agent.JETT.imageLink
    }

}