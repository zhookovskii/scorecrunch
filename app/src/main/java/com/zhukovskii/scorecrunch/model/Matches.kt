package com.zhukovskii.scorecrunch.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Matches(
    val filters: Any,
    val resultSet: Any,
    val competition: Any,
    val matches: List<Match>
)