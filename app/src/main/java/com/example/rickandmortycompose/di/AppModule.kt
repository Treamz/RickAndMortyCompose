package com.example.rickandmortycompose.di

import com.example.rickandmortycompose.data.CharacterApi
import com.example.rickandmortycompose.data.repository.BaseRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideCharacterApi(): CharacterApi {
        return Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(CharacterApi::class.java)
    }

    @Provides
    @Singleton
    fun provideBaseRepository(characterApi: CharacterApi): BaseRepository {
        return BaseRepository(characterApi)
    }
}