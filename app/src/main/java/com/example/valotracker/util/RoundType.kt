package com.example.valotracker.util

enum class RoundType(val roundTypeFormatted: String, val team: String, val imageLink: String) {
    ELIMINATION_RED("Eliminated", "Red","https://trackercdn.com/cdn/tracker.gg/valorant/icons/eliminationloss1.png"),
    ELIMINATION_BLUE("Eliminated", "Blue","https://trackercdn.com/cdn/tracker.gg/valorant/icons/eliminationwin1.png"),
    DEFUSE_RED("Bomb defused", "Red","https://trackercdn.com/cdn/tracker.gg/valorant/icons/diffuseloss1.png"),
    DEFUSE_BLUE("Bomb defused", "Blue","https://trackercdn.com/cdn/tracker.gg/valorant/icons/diffusewin1.png"),
    TIME_RED("Round timer expired", "Red","https://trackercdn.com/cdn/tracker.gg/valorant/icons/timeloss1.png"),
    TIME_BLUE("Round timer expired", "Blue","https://trackercdn.com/cdn/tracker.gg/valorant/icons/timewin1.png"),
    SURRENDER("Surrendered", "Both", "https://trackercdn.com/cdn/tracker.gg/valorant/icons/earlysurrender-flag.png"),
    EXPLOSION_RED("Bomb detonated", "Red", "https://trackercdn.com/cdn/tracker.gg/valorant/icons/explosionloss1.png"),
    EXPLOSION_BLUE("Bomb detonated", "Blue", "https://trackercdn.com/cdn/tracker.gg/valorant/icons/explosionwin1.png"),
}