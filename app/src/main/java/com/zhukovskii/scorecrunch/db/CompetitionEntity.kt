package com.zhukovskii.scorecrunch.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CompetitionEntity(
    @PrimaryKey
    val id: Int,
    val areaName: String,
    val name: String,
    val code: String,
    val type: String,
    val emblem: String?,
    val currentSeasonStartDate: String,
    val currentSeasonEndDate: String,
    val currentSeasonMatchday: Int
)