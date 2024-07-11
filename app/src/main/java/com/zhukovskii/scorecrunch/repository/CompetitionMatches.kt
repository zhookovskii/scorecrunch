package com.zhukovskii.scorecrunch.repository

import com.zhukovskii.scorecrunch.db.CompetitionEntity
import com.zhukovskii.scorecrunch.db.MatchEntity

data class CompetitionMatches(
    val competition: CompetitionEntity,
    val matches: List<MatchEntity>
)