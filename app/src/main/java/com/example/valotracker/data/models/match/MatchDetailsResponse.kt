package com.example.valotracker.data.models.match


import com.google.gson.annotations.SerializedName

data class MatchDetailsResponse(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("status")
    val status: Int
) {
    data class Data(
        @SerializedName("coaches")
        val coaches: List<Any>,
        @SerializedName("kills")
        val kills: List<Kill>,
        @SerializedName("metadata")
        val metadata: Metadata,
        @SerializedName("observers")
        val observers: List<Any>,
        @SerializedName("players")
        val players: Players,
        @SerializedName("rounds")
        val rounds: List<Round>,
        @SerializedName("teams")
        val teams: Teams
    ) {
        data class Kill(
            @SerializedName("assistants")
            val assistants: List<Assistant>,
            @SerializedName("damage_weapon_assets")
            val damageWeaponAssets: DamageWeaponAssets?,
            @SerializedName("damage_weapon_id")
            val damageWeaponId: String,
            @SerializedName("damage_weapon_name")
            val damageWeaponName: String?,
            @SerializedName("kill_time_in_match")
            val killTimeInMatch: Int,
            @SerializedName("kill_time_in_round")
            val killTimeInRound: Int,
            @SerializedName("killer_display_name")
            val killerDisplayName: String,
            @SerializedName("killer_puuid")
            val killerPuuid: String,
            @SerializedName("killer_team")
            val killerTeam: String,
            @SerializedName("player_locations_on_kill")
            val playerLocationsOnKill: List<PlayerLocationsOnKill>,
            @SerializedName("round")
            val round: Int,
            @SerializedName("secondary_fire_mode")
            val secondaryFireMode: Boolean,
            @SerializedName("victim_death_location")
            val victimDeathLocation: VictimDeathLocation,
            @SerializedName("victim_display_name")
            val victimDisplayName: String,
            @SerializedName("victim_puuid")
            val victimPuuid: String,
            @SerializedName("victim_team")
            val victimTeam: String
        ) {
            data class Assistant(
                @SerializedName("assistant_display_name")
                val assistantDisplayName: String,
                @SerializedName("assistant_puuid")
                val assistantPuuid: String,
                @SerializedName("assistant_team")
                val assistantTeam: String
            )

            data class DamageWeaponAssets(
                @SerializedName("display_icon")
                val displayIcon: String?,
                @SerializedName("killfeed_icon")
                val killfeedIcon: String?
            )

            data class PlayerLocationsOnKill(
                @SerializedName("location")
                val location: Location,
                @SerializedName("player_display_name")
                val playerDisplayName: String,
                @SerializedName("player_puuid")
                val playerPuuid: String,
                @SerializedName("player_team")
                val playerTeam: String,
                @SerializedName("view_radians")
                val viewRadians: Double
            ) {
                data class Location(
                    @SerializedName("x")
                    val x: Int,
                    @SerializedName("y")
                    val y: Int
                )
            }

            data class VictimDeathLocation(
                @SerializedName("x")
                val x: Int,
                @SerializedName("y")
                val y: Int
            )
        }

        data class Metadata(
            @SerializedName("cluster")
            val cluster: String,
            @SerializedName("game_length")
            val gameLength: Int,
            @SerializedName("game_start")
            val gameStart: Int,
            @SerializedName("game_start_patched")
            val gameStartPatched: String,
            @SerializedName("game_version")
            val gameVersion: String,
            @SerializedName("map")
            val map: String,
            @SerializedName("matchid")
            val matchid: String,
            @SerializedName("mode")
            val mode: String,
            @SerializedName("mode_id")
            val modeId: String,
            @SerializedName("platform")
            val platform: String,
            @SerializedName("premier_info")
            val premierInfo: PremierInfo,
            @SerializedName("queue")
            val queue: String,
            @SerializedName("region")
            val region: String,
            @SerializedName("rounds_played")
            val roundsPlayed: Int,
            @SerializedName("season_id")
            val seasonId: String
        ) {
            data class PremierInfo(
                @SerializedName("matchup_id")
                val matchupId: Any?,
                @SerializedName("tournament_id")
                val tournamentId: Any?
            )
        }

        data class Players(
            @SerializedName("all_players")
            val allPlayers: List<AllPlayer>,
            @SerializedName("blue")
            val blue: List<Blue>,
            @SerializedName("red")
            val red: List<Red>
        ) {
            data class AllPlayer(
                @SerializedName("ability_casts")
                val abilityCasts: AbilityCasts,
                @SerializedName("assets")
                val assets: Assets,
                @SerializedName("behavior")
                val behavior: Behavior,
                @SerializedName("character")
                val character: String,
                @SerializedName("currenttier")
                val currenttier: Int,
                @SerializedName("currenttier_patched")
                val currenttierPatched: String,
                @SerializedName("damage_made")
                val damageMade: Int,
                @SerializedName("damage_received")
                val damageReceived: Int,
                @SerializedName("economy")
                val economy: Economy,
                @SerializedName("level")
                val level: Int,
                @SerializedName("name")
                val name: String,
                @SerializedName("party_id")
                val partyId: String,
                @SerializedName("platform")
                val platform: Platform,
                @SerializedName("player_card")
                val playerCard: String,
                @SerializedName("player_title")
                val playerTitle: String,
                @SerializedName("puuid")
                val puuid: String,
                @SerializedName("session_playtime")
                val sessionPlaytime: SessionPlaytime,
                @SerializedName("stats")
                val stats: Stats,
                @SerializedName("tag")
                val tag: String,
                @SerializedName("team")
                val team: String
            ) {
                data class AbilityCasts(
                    @SerializedName("c_cast")
                    val cCast: Int,
                    @SerializedName("e_cast")
                    val eCast: Int,
                    @SerializedName("q_cast")
                    val qCast: Int,
                    @SerializedName("x_cast")
                    val xCast: Int
                )

                data class Assets(
                    @SerializedName("agent")
                    val agent: Agent,
                    @SerializedName("card")
                    val card: Card
                ) {
                    data class Agent(
                        @SerializedName("bust")
                        val bust: String,
                        @SerializedName("full")
                        val full: String,
                        @SerializedName("killfeed")
                        val killfeed: String,
                        @SerializedName("small")
                        val small: String
                    )

                    data class Card(
                        @SerializedName("large")
                        val large: String,
                        @SerializedName("small")
                        val small: String,
                        @SerializedName("wide")
                        val wide: String
                    )
                }

                data class Behavior(
                    @SerializedName("afk_rounds")
                    val afkRounds: Float,
                    @SerializedName("friendly_fire")
                    val friendlyFire: FriendlyFire,
                    @SerializedName("rounds_in_spawn")
                    val roundsInSpawn: Float
                ) {
                    data class FriendlyFire(
                        @SerializedName("incoming")
                        val incoming: Int,
                        @SerializedName("outgoing")
                        val outgoing: Int
                    )
                }

                data class Economy(
                    @SerializedName("loadout_value")
                    val loadoutValue: LoadoutValue,
                    @SerializedName("spent")
                    val spent: Spent
                ) {
                    data class LoadoutValue(
                        @SerializedName("average")
                        val average: Int,
                        @SerializedName("overall")
                        val overall: Int
                    )

                    data class Spent(
                        @SerializedName("average")
                        val average: Int,
                        @SerializedName("overall")
                        val overall: Int
                    )
                }

                data class Platform(
                    @SerializedName("os")
                    val os: Os,
                    @SerializedName("type")
                    val type: String
                ) {
                    data class Os(
                        @SerializedName("name")
                        val name: String,
                        @SerializedName("version")
                        val version: String
                    )
                }

                data class SessionPlaytime(
                    @SerializedName("milliseconds")
                    val milliseconds: Int,
                    @SerializedName("minutes")
                    val minutes: Int,
                    @SerializedName("seconds")
                    val seconds: Int
                )

                data class Stats(
                    @SerializedName("assists")
                    val assists: Int,
                    @SerializedName("bodyshots")
                    val bodyshots: Int,
                    @SerializedName("deaths")
                    val deaths: Int,
                    @SerializedName("headshots")
                    val headshots: Int,
                    @SerializedName("kills")
                    val kills: Int,
                    @SerializedName("legshots")
                    val legshots: Int,
                    @SerializedName("score")
                    val score: Int
                )
            }

            data class Blue(
                @SerializedName("ability_casts")
                val abilityCasts: AbilityCasts,
                @SerializedName("assets")
                val assets: Assets,
                @SerializedName("behavior")
                val behavior: Behavior,
                @SerializedName("character")
                val character: String,
                @SerializedName("currenttier")
                val currenttier: Int,
                @SerializedName("currenttier_patched")
                val currenttierPatched: String,
                @SerializedName("damage_made")
                val damageMade: Int,
                @SerializedName("damage_received")
                val damageReceived: Int,
                @SerializedName("economy")
                val economy: Economy,
                @SerializedName("level")
                val level: Int,
                @SerializedName("name")
                val name: String,
                @SerializedName("party_id")
                val partyId: String,
                @SerializedName("platform")
                val platform: Platform,
                @SerializedName("player_card")
                val playerCard: String,
                @SerializedName("player_title")
                val playerTitle: String,
                @SerializedName("puuid")
                val puuid: String,
                @SerializedName("session_playtime")
                val sessionPlaytime: SessionPlaytime,
                @SerializedName("stats")
                val stats: Stats,
                @SerializedName("tag")
                val tag: String,
                @SerializedName("team")
                val team: String
            ) {
                data class AbilityCasts(
                    @SerializedName("c_cast")
                    val cCast: Int,
                    @SerializedName("e_cast")
                    val eCast: Int,
                    @SerializedName("q_cast")
                    val qCast: Int,
                    @SerializedName("x_cast")
                    val xCast: Int
                )

                data class Assets(
                    @SerializedName("agent")
                    val agent: Agent,
                    @SerializedName("card")
                    val card: Card
                ) {
                    data class Agent(
                        @SerializedName("bust")
                        val bust: String,
                        @SerializedName("full")
                        val full: String,
                        @SerializedName("killfeed")
                        val killfeed: String,
                        @SerializedName("small")
                        val small: String
                    )

                    data class Card(
                        @SerializedName("large")
                        val large: String,
                        @SerializedName("small")
                        val small: String,
                        @SerializedName("wide")
                        val wide: String
                    )
                }

                data class Behavior(
                    @SerializedName("afk_rounds")
                    val afkRounds: Float,
                    @SerializedName("friendly_fire")
                    val friendlyFire: FriendlyFire,
                    @SerializedName("rounds_in_spawn")
                    val roundsInSpawn: Float
                ) {
                    data class FriendlyFire(
                        @SerializedName("incoming")
                        val incoming: Int,
                        @SerializedName("outgoing")
                        val outgoing: Int
                    )
                }

                data class Economy(
                    @SerializedName("loadout_value")
                    val loadoutValue: LoadoutValue,
                    @SerializedName("spent")
                    val spent: Spent
                ) {
                    data class LoadoutValue(
                        @SerializedName("average")
                        val average: Int,
                        @SerializedName("overall")
                        val overall: Int
                    )

                    data class Spent(
                        @SerializedName("average")
                        val average: Int,
                        @SerializedName("overall")
                        val overall: Int
                    )
                }

                data class Platform(
                    @SerializedName("os")
                    val os: Os,
                    @SerializedName("type")
                    val type: String
                ) {
                    data class Os(
                        @SerializedName("name")
                        val name: String,
                        @SerializedName("version")
                        val version: String
                    )
                }

                data class SessionPlaytime(
                    @SerializedName("milliseconds")
                    val milliseconds: Int,
                    @SerializedName("minutes")
                    val minutes: Int,
                    @SerializedName("seconds")
                    val seconds: Int
                )

                data class Stats(
                    @SerializedName("assists")
                    val assists: Int,
                    @SerializedName("bodyshots")
                    val bodyshots: Int,
                    @SerializedName("deaths")
                    val deaths: Int,
                    @SerializedName("headshots")
                    val headshots: Int,
                    @SerializedName("kills")
                    val kills: Int,
                    @SerializedName("legshots")
                    val legshots: Int,
                    @SerializedName("score")
                    val score: Int
                )
            }

            data class Red(
                @SerializedName("ability_casts")
                val abilityCasts: AbilityCasts,
                @SerializedName("assets")
                val assets: Assets,
                @SerializedName("behavior")
                val behavior: Behavior,
                @SerializedName("character")
                val character: String,
                @SerializedName("currenttier")
                val currenttier: Int,
                @SerializedName("currenttier_patched")
                val currenttierPatched: String,
                @SerializedName("damage_made")
                val damageMade: Int,
                @SerializedName("damage_received")
                val damageReceived: Int,
                @SerializedName("economy")
                val economy: Economy,
                @SerializedName("level")
                val level: Int,
                @SerializedName("name")
                val name: String,
                @SerializedName("party_id")
                val partyId: String,
                @SerializedName("platform")
                val platform: Platform,
                @SerializedName("player_card")
                val playerCard: String,
                @SerializedName("player_title")
                val playerTitle: String,
                @SerializedName("puuid")
                val puuid: String,
                @SerializedName("session_playtime")
                val sessionPlaytime: SessionPlaytime,
                @SerializedName("stats")
                val stats: Stats,
                @SerializedName("tag")
                val tag: String,
                @SerializedName("team")
                val team: String
            ) {
                data class AbilityCasts(
                    @SerializedName("c_cast")
                    val cCast: Int,
                    @SerializedName("e_cast")
                    val eCast: Int,
                    @SerializedName("q_cast")
                    val qCast: Int,
                    @SerializedName("x_cast")
                    val xCast: Int
                )

                data class Assets(
                    @SerializedName("agent")
                    val agent: Agent,
                    @SerializedName("card")
                    val card: Card
                ) {
                    data class Agent(
                        @SerializedName("bust")
                        val bust: String,
                        @SerializedName("full")
                        val full: String,
                        @SerializedName("killfeed")
                        val killfeed: String,
                        @SerializedName("small")
                        val small: String
                    )

                    data class Card(
                        @SerializedName("large")
                        val large: String,
                        @SerializedName("small")
                        val small: String,
                        @SerializedName("wide")
                        val wide: String
                    )
                }

                data class Behavior(
                    @SerializedName("afk_rounds")
                    val afkRounds: Float,
                    @SerializedName("friendly_fire")
                    val friendlyFire: FriendlyFire,
                    @SerializedName("rounds_in_spawn")
                    val roundsInSpawn: Float
                ) {
                    data class FriendlyFire(
                        @SerializedName("incoming")
                        val incoming: Int,
                        @SerializedName("outgoing")
                        val outgoing: Int
                    )
                }

                data class Economy(
                    @SerializedName("loadout_value")
                    val loadoutValue: LoadoutValue,
                    @SerializedName("spent")
                    val spent: Spent
                ) {
                    data class LoadoutValue(
                        @SerializedName("average")
                        val average: Int,
                        @SerializedName("overall")
                        val overall: Int
                    )

                    data class Spent(
                        @SerializedName("average")
                        val average: Int,
                        @SerializedName("overall")
                        val overall: Int
                    )
                }

                data class Platform(
                    @SerializedName("os")
                    val os: Os,
                    @SerializedName("type")
                    val type: String
                ) {
                    data class Os(
                        @SerializedName("name")
                        val name: String,
                        @SerializedName("version")
                        val version: String
                    )
                }

                data class SessionPlaytime(
                    @SerializedName("milliseconds")
                    val milliseconds: Int,
                    @SerializedName("minutes")
                    val minutes: Int,
                    @SerializedName("seconds")
                    val seconds: Int
                )

                data class Stats(
                    @SerializedName("assists")
                    val assists: Int,
                    @SerializedName("bodyshots")
                    val bodyshots: Int,
                    @SerializedName("deaths")
                    val deaths: Int,
                    @SerializedName("headshots")
                    val headshots: Int,
                    @SerializedName("kills")
                    val kills: Int,
                    @SerializedName("legshots")
                    val legshots: Int,
                    @SerializedName("score")
                    val score: Int
                )
            }
        }

        data class Round(
            @SerializedName("bomb_defused")
            val bombDefused: Boolean,
            @SerializedName("bomb_planted")
            val bombPlanted: Boolean,
            @SerializedName("defuse_events")
            val defuseEvents: DefuseEvents,
            @SerializedName("end_type")
            val endType: String,
            @SerializedName("plant_events")
            val plantEvents: PlantEvents,
            @SerializedName("player_stats")
            val playerStats: List<PlayerStat>,
            @SerializedName("winning_team")
            val winningTeam: String
        ) {
            data class DefuseEvents(
                @SerializedName("defuse_location")
                val defuseLocation: DefuseLocation?,
                @SerializedName("defuse_time_in_round")
                val defuseTimeInRound: Int?,
                @SerializedName("defused_by")
                val defusedBy: DefusedBy?,
                @SerializedName("player_locations_on_defuse")
                val playerLocationsOnDefuse: List<PlayerLocationsOnDefuse>?
            ) {
                data class DefuseLocation(
                    @SerializedName("x")
                    val x: Int,
                    @SerializedName("y")
                    val y: Int
                )

                data class DefusedBy(
                    @SerializedName("display_name")
                    val displayName: String,
                    @SerializedName("puuid")
                    val puuid: String,
                    @SerializedName("team")
                    val team: String
                )

                data class PlayerLocationsOnDefuse(
                    @SerializedName("location")
                    val location: Location,
                    @SerializedName("player_display_name")
                    val playerDisplayName: String,
                    @SerializedName("player_puuid")
                    val playerPuuid: String,
                    @SerializedName("player_team")
                    val playerTeam: String,
                    @SerializedName("view_radians")
                    val viewRadians: Double
                ) {
                    data class Location(
                        @SerializedName("x")
                        val x: Int,
                        @SerializedName("y")
                        val y: Int
                    )
                }
            }

            data class PlantEvents(
                @SerializedName("plant_location")
                val plantLocation: PlantLocation?,
                @SerializedName("plant_site")
                val plantSite: String?,
                @SerializedName("plant_time_in_round")
                val plantTimeInRound: Int?,
                @SerializedName("planted_by")
                val plantedBy: PlantedBy?,
                @SerializedName("player_locations_on_plant")
                val playerLocationsOnPlant: List<PlayerLocationsOnPlant>?
            ) {
                data class PlantLocation(
                    @SerializedName("x")
                    val x: Int,
                    @SerializedName("y")
                    val y: Int
                )

                data class PlantedBy(
                    @SerializedName("display_name")
                    val displayName: String,
                    @SerializedName("puuid")
                    val puuid: String,
                    @SerializedName("team")
                    val team: String
                )

                data class PlayerLocationsOnPlant(
                    @SerializedName("location")
                    val location: Location,
                    @SerializedName("player_display_name")
                    val playerDisplayName: String,
                    @SerializedName("player_puuid")
                    val playerPuuid: String,
                    @SerializedName("player_team")
                    val playerTeam: String,
                    @SerializedName("view_radians")
                    val viewRadians: Double
                ) {
                    data class Location(
                        @SerializedName("x")
                        val x: Int,
                        @SerializedName("y")
                        val y: Int
                    )
                }
            }

            data class PlayerStat(
                @SerializedName("ability_casts")
                val abilityCasts: AbilityCasts,
                @SerializedName("bodyshots")
                val bodyshots: Int,
                @SerializedName("damage")
                val damage: Int,
                @SerializedName("damage_events")
                val damageEvents: List<DamageEvent>,
                @SerializedName("economy")
                val economy: Economy,
                @SerializedName("headshots")
                val headshots: Int,
                @SerializedName("kill_events")
                val killEvents: List<KillEvent>,
                @SerializedName("kills")
                val kills: Int,
                @SerializedName("legshots")
                val legshots: Int,
                @SerializedName("player_display_name")
                val playerDisplayName: String,
                @SerializedName("player_puuid")
                val playerPuuid: String,
                @SerializedName("player_team")
                val playerTeam: String,
                @SerializedName("score")
                val score: Int,
                @SerializedName("stayed_in_spawn")
                val stayedInSpawn: Boolean,
                @SerializedName("was_afk")
                val wasAfk: Boolean,
                @SerializedName("was_penalized")
                val wasPenalized: Boolean
            ) {
                data class AbilityCasts(
                    @SerializedName("c_casts")
                    val cCasts: Any?,
                    @SerializedName("e_cast")
                    val eCast: Any?,
                    @SerializedName("q_casts")
                    val qCasts: Any?,
                    @SerializedName("x_cast")
                    val xCast: Any?
                )

                data class DamageEvent(
                    @SerializedName("bodyshots")
                    val bodyshots: Int,
                    @SerializedName("damage")
                    val damage: Int,
                    @SerializedName("headshots")
                    val headshots: Int,
                    @SerializedName("legshots")
                    val legshots: Int,
                    @SerializedName("receiver_display_name")
                    val receiverDisplayName: String,
                    @SerializedName("receiver_puuid")
                    val receiverPuuid: String,
                    @SerializedName("receiver_team")
                    val receiverTeam: String
                )

                data class Economy(
                    @SerializedName("armor")
                    val armor: Armor,
                    @SerializedName("loadout_value")
                    val loadoutValue: Int,
                    @SerializedName("remaining")
                    val remaining: Int,
                    @SerializedName("spent")
                    val spent: Int,
                    @SerializedName("weapon")
                    val weapon: Weapon
                ) {
                    data class Armor(
                        @SerializedName("assets")
                        val assets: Assets,
                        @SerializedName("id")
                        val id: String?,
                        @SerializedName("name")
                        val name: String?
                    ) {
                        data class Assets(
                            @SerializedName("display_icon")
                            val displayIcon: String?
                        )
                    }

                    data class Weapon(
                        @SerializedName("assets")
                        val assets: Assets,
                        @SerializedName("id")
                        val id: String?,
                        @SerializedName("name")
                        val name: String?
                    ) {
                        data class Assets(
                            @SerializedName("display_icon")
                            val displayIcon: String?,
                            @SerializedName("killfeed_icon")
                            val killfeedIcon: String?
                        )
                    }
                }

                data class KillEvent(
                    @SerializedName("assistants")
                    val assistants: List<Assistant>,
                    @SerializedName("damage_weapon_assets")
                    val damageWeaponAssets: DamageWeaponAssets?,
                    @SerializedName("damage_weapon_id")
                    val damageWeaponId: String,
                    @SerializedName("damage_weapon_name")
                    val damageWeaponName: String?,
                    @SerializedName("kill_time_in_match")
                    val killTimeInMatch: Int,
                    @SerializedName("kill_time_in_round")
                    val killTimeInRound: Int,
                    @SerializedName("killer_display_name")
                    val killerDisplayName: String,
                    @SerializedName("killer_puuid")
                    val killerPuuid: String,
                    @SerializedName("killer_team")
                    val killerTeam: String,
                    @SerializedName("player_locations_on_kill")
                    val playerLocationsOnKill: List<PlayerLocationsOnKill>,
                    @SerializedName("secondary_fire_mode")
                    val secondaryFireMode: Boolean,
                    @SerializedName("victim_death_location")
                    val victimDeathLocation: VictimDeathLocation,
                    @SerializedName("victim_display_name")
                    val victimDisplayName: String,
                    @SerializedName("victim_puuid")
                    val victimPuuid: String,
                    @SerializedName("victim_team")
                    val victimTeam: String
                ) {
                    data class Assistant(
                        @SerializedName("assistant_display_name")
                        val assistantDisplayName: String,
                        @SerializedName("assistant_puuid")
                        val assistantPuuid: String,
                        @SerializedName("assistant_team")
                        val assistantTeam: String
                    )

                    data class DamageWeaponAssets(
                        @SerializedName("display_icon")
                        val displayIcon: String?,
                        @SerializedName("killfeed_icon")
                        val killfeedIcon: String?
                    )

                    data class PlayerLocationsOnKill(
                        @SerializedName("location")
                        val location: Location,
                        @SerializedName("player_display_name")
                        val playerDisplayName: String,
                        @SerializedName("player_puuid")
                        val playerPuuid: String,
                        @SerializedName("player_team")
                        val playerTeam: String,
                        @SerializedName("view_radians")
                        val viewRadians: Double
                    ) {
                        data class Location(
                            @SerializedName("x")
                            val x: Int,
                            @SerializedName("y")
                            val y: Int
                        )
                    }

                    data class VictimDeathLocation(
                        @SerializedName("x")
                        val x: Int,
                        @SerializedName("y")
                        val y: Int
                    )
                }
            }
        }

        data class Teams(
            @SerializedName("blue")
            val blue: Blue,
            @SerializedName("red")
            val red: Red
        ) {
            data class Blue(
                @SerializedName("has_won")
                val hasWon: Boolean,
                @SerializedName("roster")
                val roster: Any?,
                @SerializedName("rounds_lost")
                val roundsLost: Int,
                @SerializedName("rounds_won")
                val roundsWon: Int
            )

            data class Red(
                @SerializedName("has_won")
                val hasWon: Boolean,
                @SerializedName("roster")
                val roster: Any?,
                @SerializedName("rounds_lost")
                val roundsLost: Int,
                @SerializedName("rounds_won")
                val roundsWon: Int
            )
        }
    }
}