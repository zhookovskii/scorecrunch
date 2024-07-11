package com.zhukovskii.scorecrunch.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Team(
    val id: Int?,
    val name: String?,
    val shortName: String?,
    val tla: String?,
    val crest: String?,
    val coach: Any?,
    val leagueRank: Int?,
    val formation: String?,
    val lineup: Any?,
    val bench: Any?
)