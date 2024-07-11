package com.zhukovskii.scorecrunch.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CurrentSeason(
    val id: Int,
    val startDate: String,
    val endDate: String,
    val currentMatchday: Int,
    val winner: Any?
)