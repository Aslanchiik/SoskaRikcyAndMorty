package com.example.domain.repositories

import com.example.common.resource.Resource
import com.example.domain.models.CharacterModel
import com.example.domain.models.EpisodeModel
import com.example.domain.models.LocationModel
import kotlinx.coroutines.flow.Flow

interface FilterRepository {
    fun fetchCharacterFilter(name: String?): Flow<Resource<List<CharacterModel>>>
    fun fetchLLocationFilter(name: String?): Flow<Resource<List<LocationModel>>>
    fun fetchEpisodeFilter(name: String?): Flow<Resource<List<EpisodeModel>>>
}