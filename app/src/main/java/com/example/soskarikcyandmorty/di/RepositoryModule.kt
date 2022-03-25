package com.example.soskarikcyandmorty.di

import com.example.data.network.apiservices.CharacterApiService
import com.example.data.network.apiservices.EpisodeApiService
import com.example.data.network.apiservices.FilterApiService
import com.example.data.network.apiservices.LocationApiService
import com.example.data.repositories.CharacterRepositoryImpl
import com.example.data.repositories.EpisodeRepositoryImpl
import com.example.data.repositories.FilterRepositoryImpl
import com.example.data.repositories.LocationRepositoryImpl
import com.example.domain.repositories.CharacterRepository
import com.example.domain.repositories.EpisodeRepository
import com.example.domain.repositories.FilterRepository
import com.example.domain.repositories.LocationRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideCharacterRepository(service: CharacterApiService): CharacterRepository {
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

    @Provides
    fun provideFilterRepository(service: FilterApiService): FilterRepository {
        return FilterRepositoryImpl(service)
    }
}