package com.example.rickandmortycompose.data.model

data class Character(
    val info: Info,
    val results: List<Result> = listOf()
)