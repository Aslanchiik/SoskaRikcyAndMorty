package com.example.soskarikcyandmorty.di

import com.example.soskarikcyandmorty.data.network.RetrofitClient
import com.example.soskarikcyandmorty.data.network.apiservices.CharacterApiService
import com.example.soskarikcyandmorty.data.network.apiservices.EpisodeApiService
import com.example.soskarikcyandmorty.data.network.apiservices.LocationApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    val retrofitClient = RetrofitClient()

    @Singleton
    @Provides
    fun provideCharacterApiServices(): CharacterApiService {
        return retrofitClient.providerApiService()
    }

    @Singleton
    @Provides
    fun provideEpisodeApiServices(): EpisodeApiService {
        return retrofitClient.providerEpisodeApiService()
    }

    @Singleton
    @Provides
    fun provideLocationApiService(): LocationApiService {
        return retrofitClient.provideLocationService()
    }
}