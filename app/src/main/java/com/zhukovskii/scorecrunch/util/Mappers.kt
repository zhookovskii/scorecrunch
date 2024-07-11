package com.zhukovskii.scorecrunch.util

import com.zhukovskii.scorecrunch.db.CompetitionEntity
import com.zhukovskii.scorecrunch.db.MatchEntity
import com.zhukovskii.scorecrunch.model.Competition
import com.zhukovskii.scorecrunch.model.Match

fun Competition.toEntity(): CompetitionEntity {
    return CompetitionEntity(
        id,
        area.name,
        name,
        code,
        type,
        emblem,
        currentSeason.startDate,
        currentSeason.endDate,
        currentSeason.currentMatchday
    )
}

fun Match.toEntity(competitionId: Int): MatchEntity {
    return MatchEntity(
        id,
        competitionId,
        utcDate,
        status,
        matchday,
        stage,
        homeTeam.name,
        homeTeam.crest,
        awayTeam.name,
        awayTeam.crest,
        score.duration,
        score.fullTime.home,
        score.fullTime.away,
        score.winner
    )
}