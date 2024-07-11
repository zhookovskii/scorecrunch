package com.zhukovskii.scorecrunch.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [CompetitionEntity::class, MatchEntity::class],
    version = 1
)
abstract class ScoreCrunchDatabase : RoomDatabase() {
    abstract val dao: ScoreCrunchDao
}