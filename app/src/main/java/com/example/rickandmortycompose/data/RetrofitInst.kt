package com.example.rickandmortycompose.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInst {
    companion object {
        private val retrifit by lazy {
            Retrofit.Builder()
                .baseUrl("https://rickandmortyapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        val apiCharacter = retrifit.create(CharacterApi::class.java)
    }
}