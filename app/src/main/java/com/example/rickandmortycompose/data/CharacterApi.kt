package com.example.rickandmortycompose.data

import retrofit2.http.GET
import retrofit2.http.Query

interface CharacterApi {
    @GET("api/character")
    suspend fun getCharacter(
        @Query("page") page: Int
    ): com.example.rickandmortycompose.data.model.Character
}