package com.example.soskarikcyandmorty.di

import com.example.soskarikcyandmorty.data.network.apiservices.CharacterApiService
import com.example.soskarikcyandmorty.data.network.apiservices.EpisodeApiService
import com.example.soskarikcyandmorty.data.network.apiservices.LocationApiService
import com.example.soskarikcyandmorty.data.repositories.CharacterRepositoryImpl
import com.example.soskarikcyandmorty.data.repositories.EpisodeRepositoryImpl
import com.example.soskarikcyandmorty.data.repositories.LocationRepositoryImpl
import com.example.soskarikcyandmorty.domain.repositories.CharacterRepository
import com.example.soskarikcyandmorty.domain.repositories.EpisodeRepository
import com.example.soskarikcyandmorty.domain.repositories.LocationRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideRepository(service: CharacterApiService): CharacterRepository {
        return CharacterRepositoryImpl(service)
    }

    @Provides
    fun provideEpisodeRepository(service: EpisodeApiService): EpisodeRepository {
        return EpisodeRepositoryImpl(service)
    }

    @Provides
    fun provideLocationRepository(service: LocationApiService): LocationRepository {
        return LocationRepositoryImpl(service)
    }
}