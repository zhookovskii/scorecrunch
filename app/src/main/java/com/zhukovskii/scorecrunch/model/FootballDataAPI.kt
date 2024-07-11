package com.zhukovskii.scorecrunch.model

import com.zhukovskii.scorecrunch.config.Config
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface FootballDataAPI {

    @Headers("X-Auth-Token: ${Config.API_KEY}")
    @GET("v4/competitions")
    suspend fun getCompetitions(): Competitions

    @Headers("X-Auth-Token: ${Config.API_KEY}")
    @GET("v4/competitions/{id}/matches")
    suspend fun getMatches(
        @Path("id") id: Int,
        @Query("matchday") matchday: Int
    ): Matches

    @Headers("X-Auth-Token: ${Config.API_KEY}")
    @GET("v4/competitions/2000/matches?season=2022")
    suspend fun getWorldCup(): Matches
}