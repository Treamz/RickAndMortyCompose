package com.example.rickandmortycompose.presentation.character_list

import androidx.paging.PagingData
import com.example.rickandmortycompose.data.model.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class
CharacterState(
    val character: Flow<PagingData<Result>> = emptyFlow()
)