package com.example.soskarikcyandmorty.di

import com.example.data.network.remote.apiservices.CharacterApiService
import com.example.data.network.remote.apiservices.EpisodeApiService
import com.example.data.network.remote.apiservices.FilterApiService
import com.example.data.network.remote.apiservices.LocationApiService
import com.example.data.network.RetrofitClient
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

    @Singleton
    @Provides
    fun provideFilterApiService(): FilterApiService {
        return retrofitClient.provideFilterApiService()
    }
}