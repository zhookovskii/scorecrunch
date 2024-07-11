package com.zhukovskii.scorecrunch.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Competition(
    val id: Int,
    val area: Area,
    val name: String,
    val code: String,
    val type: String,
    val emblem: String?,
    val plan: String,
    val currentSeason: CurrentSeason,
    val numberOfAvailableSeasons: Int,
    val lastUpdated: String
)