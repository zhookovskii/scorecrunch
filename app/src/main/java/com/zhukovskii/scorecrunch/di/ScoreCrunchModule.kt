package com.zhukovskii.scorecrunch.di

import android.app.Application
import androidx.room.Room
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.zhukovskii.scorecrunch.config.Config
import com.zhukovskii.scorecrunch.db.ScoreCrunchDatabase
import com.zhukovskii.scorecrunch.model.FootballDataAPI
import com.zhukovskii.scorecrunch.repository.ScoreCrunchRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ScoreCrunchModule {

    @Provides
    @Singleton
    fun provideApi(): FootballDataAPI {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        return Retrofit.Builder()
            .baseUrl(Config.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(FootballDataAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideDb(app: Application): ScoreCrunchDatabase {
        return Room.databaseBuilder(
            app,
            ScoreCrunchDatabase::class.java,
            "scorecrunch.db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideRepository(
        api: FootballDataAPI,
        db: ScoreCrunchDatabase
    ): ScoreCrunchRepository {
        return ScoreCrunchRepository(api, db)
    }
}