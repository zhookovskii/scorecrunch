package com.zhukovskii.scorecrunch.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ScorePeriod(
    val home: Int?,
    val away: Int?
)