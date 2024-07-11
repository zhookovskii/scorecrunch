package com.zhukovskii.scorecrunch.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Competitions(
    val count: Int,
    val filters: Any,
    val competitions: List<Competition>
)