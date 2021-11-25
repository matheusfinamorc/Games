package com.example.games.service

import com.example.games.model.GamesItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface API {
    @GET("api/games")
    suspend fun getGames(): Response<MutableList<GamesItem>>

    @GET("api/games")
    suspend fun getPlatform(@Query("platform")platform: String): Response<MutableList<GamesItem>>

}
