package com.example.games.model

import java.io.Serializable

data class GamesItem(
    val id: Int,
    val title: String,
    val thumbnail: String,
    val genre: String,
    val platform: String,
    val developer: String,
    val publisher: String,
    val description: String,
    var requirements: List<Requirements>
):Serializable

data class Requirements(
    val os: String,
    val processor: String,
    val memory: String,
    val graphics: String,
    val storage: String
): Serializable