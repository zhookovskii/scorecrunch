package com.zhukovskii.scorecrunch.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Odds(
    val homeWin: Float?,
    val draw: Float?,
    val awayWin: Float?
)