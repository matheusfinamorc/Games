package com.example.games.repository

import com.example.games.model.GamesItem
import com.example.games.service.API
import com.example.games.service.AppRetrofit
import retrofit2.Response

class MainRepository(
    private val api: API = AppRetrofit().gamesService) {

    suspend fun getGames(): Response<MutableList<GamesItem>>{
        return api.getGames()
    }

    suspend fun getPlatform(platform: String): Response<MutableList<GamesItem>>{
        return api.getPlatform(platform)
    }

}
