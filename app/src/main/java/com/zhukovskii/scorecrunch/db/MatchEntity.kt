package com.zhukovskii.scorecrunch.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MatchEntity(
    @PrimaryKey
    val id: Int,
    val competitionId: Int,
    val utcDate: String,
    val status: String,
    val matchday: Int,
    val stage: String,
    val homeTeamName: String?,
    val homeTeamCrest: String?,
    val awayTeamName: String?,
    val awayTeamCrest: String?,
    val scoreDuration: String,
    val scoreHome: Int?,
    val scoreAway: Int?,
    val scoreWinner: String?
)