package com.example.soskarikcyandmorty.domain.repositories

import com.example.soskarikcyandmorty.common.Resource
import com.example.soskarikcyandmorty.domain.models.CharacterModel
import com.example.soskarikcyandmorty.domain.models.EpisodeModel
import com.example.soskarikcyandmorty.domain.models.LocationModel
import kotlinx.coroutines.flow.Flow

interface FilterRepository {

    fun fetchCharacterFilter(name: String?): Flow<Resource<List<CharacterModel>>>
    fun fetchLLocationFilter(name: String?): Flow<Resource<List<LocationModel>>>
    fun fetchEpisodeFilter(name: String?): Flow<Resource<List<EpisodeModel>>>
}