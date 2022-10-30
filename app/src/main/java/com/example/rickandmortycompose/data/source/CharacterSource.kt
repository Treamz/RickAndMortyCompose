package com.example.rickandmortycompose.data.source

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.rickandmortycompose.data.model.Result
import com.example.rickandmortycompose.data.repository.BaseRepository

class CharacterSource(
    private val baseRepository: BaseRepository
) : PagingSource<Int, com.example.rickandmortycompose.data.model.Result>() {
    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        return try {
            val nextPage = params.key ?: 1
            val character = baseRepository.getCharacter(nextPage).results
            LoadResult.Page(data = character, prevKey = if (nextPage == 1) null else nextPage-1,
            nextKey = nextPage.plus(1))
        } catch (ex: Exception) {
            LoadResult.Error(ex)
        }
    }
}