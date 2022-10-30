package com.example.rickandmortycompose.data.repository

import com.example.rickandmortycompose.data.CharacterApi
import com.example.rickandmortycompose.data.model.Character

class BaseRepository(
    private val characterApi: CharacterApi
) : CharacterApi {
    override suspend fun getCharacter(page: Int): Character {
        return characterApi.getCharacter(page = page)
    }
}