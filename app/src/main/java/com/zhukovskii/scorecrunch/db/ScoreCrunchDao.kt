package com.zhukovskii.scorecrunch.db

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface ScoreCrunchDao {

    @Query("SELECT * FROM competitionentity")
    suspend fun getAllCompetitions(): List<CompetitionEntity>

    @Query("SELECT * FROM matchentity WHERE matchday = :matchday AND competitionId = :competitionId ORDER BY utcDate")
    suspend fun getCompetitionMatches(
        competitionId: Int,
        matchday: Int
    ): List<MatchEntity>

    @Upsert
    suspend fun upsertCompetitions(competitions: List<CompetitionEntity>)

    @Upsert
    suspend fun upsertMatches(matches: List<MatchEntity>)
}