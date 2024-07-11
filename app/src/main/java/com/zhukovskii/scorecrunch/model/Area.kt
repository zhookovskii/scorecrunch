package com.zhukovskii.scorecrunch.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Area(
    val id: Int,
    val name: String,
    val code: String,
    val flag: String?
)