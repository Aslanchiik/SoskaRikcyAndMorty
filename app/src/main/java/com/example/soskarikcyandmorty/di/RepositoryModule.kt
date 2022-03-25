package com.example.soskarikcyandmorty.di

import com.example.data.repositories.CharacterRepositoryImpl
import com.example.data.repositories.EpisodeRepositoryImpl
import com.example.data.repositories.FilterRepositoryImpl
import com.example.data.repositories.LocationRepositoryImpl
import com.example.domain.repositories.CharacterRepository
import com.example.domain.repositories.EpisodeRepository
import com.example.domain.repositories.FilterRepository
import com.example.domain.repositories.LocationRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindCharacterRepository(characterRepositoryImpl: CharacterRepositoryImpl): CharacterRepository

    @Binds
    abstract fun bindLocationRepository(locationRepositoryImpl: LocationRepositoryImpl): LocationRepository

    @Binds
    abstract fun bindEpisodeRepository(episodeRepositoryImpl: EpisodeRepositoryImpl): EpisodeRepository

    @Binds
    abstract fun bindFilterRepository(filterRepositoryImpl: FilterRepositoryImpl): FilterRepository

//    @Provides
//    fun provideCharacterRepository(service: CharacterApiService): CharacterRepository {
//        return CharacterRepositoryImpl(service)
//    }
//
//    @Provides
//    fun provideEpisodeRepository(service: EpisodeApiService): EpisodeRepository {
//        return EpisodeRepositoryImpl(service)
//    }
//
//    @Provides
//    fun provideLocationRepository(service: LocationApiService): LocationRepository {
//        return LocationRepositoryImpl(service)
//    }
//
//    @Provides
//    fun provideFilterRepository(service: FilterApiService): FilterRepository {
//        return FilterRepositoryImpl(service)
//    }
}