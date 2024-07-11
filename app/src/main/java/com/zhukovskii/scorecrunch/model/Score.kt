package com.zhukovskii.scorecrunch.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Score(
    val winner: String?,
    val duration: String,
    val fullTime: ScorePeriod,
    val halfTime: ScorePeriod
)