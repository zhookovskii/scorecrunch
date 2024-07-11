package com.zhukovskii.scorecrunch.repository

import com.zhukovskii.scorecrunch.db.ScoreCrunchDatabase
import com.zhukovskii.scorecrunch.model.FootballDataAPI
import com.zhukovskii.scorecrunch.util.isAfter
import com.zhukovskii.scorecrunch.util.isBefore
import com.zhukovskii.scorecrunch.util.toEntity

class ScoreCrunchRepository(
    private val api: FootballDataAPI,
    private val db: ScoreCrunchDatabase
) {

    suspend fun fetchFromApi() {

        val competitions = api.getCompetitions()
            .competitions
            .filter {
                it.currentSeason.startDate.isBefore() and it.currentSeason.endDate.isAfter()
            }.map {
                it.toEntity()
            }

        db.dao.upsertCompetitions(competitions)

        competitions.forEach { competition ->
            val matches = api.getMatches(
                id = competition.id,
                matchday = competition.currentSeasonMatchday
            ).matches.map { match ->
                match.toEntity(competition.id)
            }

            db.dao.upsertMatches(matches)
        }
    }

    suspend fun retrieveFromDb(): List<CompetitionMatches> {

        val data = db.dao
            .getAllCompetitions()
            .map { competition ->
                val matches = db.dao.getCompetitionMatches(
                    competition.id,
                    competition.currentSeasonMatchday
                )

                CompetitionMatches(
                    competition = competition,
                    matches = matches
                )
            }

        return data
    }
}