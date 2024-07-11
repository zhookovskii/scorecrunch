package com.zhukovskii.scorecrunch.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Match(
    val area: Area,
    val competition: Any,
    val season: Any,
    val id: Int,
    val utcDate: String,
    val status: String,
    val minute: String?,
    val injuryTime: Int?,
    val attendance: Any?,
    val venue: String?,
    val matchday: Int,
    val stage: String,
    val group: Any?,
    val lastUpdated: String,
    val homeTeam: Team,
    val awayTeam: Team,
    val score: Score,
    val goals: Any?,
    val penalties: Any?,
    val bookings: Any?,
    val substitutions: Any?,
    val odds: Odds,
    val referees: Any?
)