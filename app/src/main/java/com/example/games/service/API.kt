package com.example.games.service

import com.example.games.model.GamesItem
import retrofit2.Response
import retrofit2.http.GET

interface API {
    @GET("api/games")
    suspend fun getGames(): Response<MutableList<GamesItem>>

}
