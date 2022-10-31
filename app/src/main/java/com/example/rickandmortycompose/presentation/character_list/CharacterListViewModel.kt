package com.example.rickandmortycompose.presentation.character_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.rickandmortycompose.data.model.Result
import com.example.rickandmortycompose.data.repository.BaseRepository
import com.example.rickandmortycompose.data.source.CharacterSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class CharacterListViewModel @Inject constructor(
    private val baseRepository: BaseRepository
) : ViewModel(){

    private val _state = mutableStateOf(CharacterState())
    val state: State<CharacterState> = _state

    init {
        val character: Flow<PagingData<Result>> =
            Pager(PagingConfig(1)) {
                CharacterSource(
                    baseRepository = baseRepository
                )
            }.flow.cachedIn(viewModelScope)
        _state.value = CharacterState(character = character)

    }
}