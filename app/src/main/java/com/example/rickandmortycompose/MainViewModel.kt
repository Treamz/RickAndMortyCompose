package com.example.rickandmortycompose

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.rickandmortycompose.data.repository.BaseRepository
import com.example.rickandmortycompose.data.source.CharacterSource
import kotlinx.coroutines.flow.Flow

class MainViewModel(
    private val baseRepository: BaseRepository
) : ViewModel() {
    val character: Flow<PagingData<com.example.rickandmortycompose.data.model.Result>> =
        Pager(PagingConfig(1)) {
            CharacterSource(
                baseRepository = baseRepository
            )
        }.flow.cachedIn(viewModelScope)
}