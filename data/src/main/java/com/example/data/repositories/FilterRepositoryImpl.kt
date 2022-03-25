package com.example.data.repositories

import com.example.core.bases.BaseRepository
import com.example.data.network.dtos.toCharacter
import com.example.data.network.dtos.toEpisode
import com.example.data.network.apiservices.FilterApiService
import com.example.data.network.dtos.toLocation
import com.example.domain.repositories.FilterRepository
import javax.inject.Inject

class FilterRepositoryImpl @Inject constructor(
    private val service: FilterApiService
) : BaseRepository(), FilterRepository {

    override fun fetchCharacterFilter(name: String?) = doRequest {
        service.filterCharacterName(name).results.map {
            it.toCharacter()
        }
    }

    override fun fetchLLocationFilter(name: String?) = doRequest {
        service.fetchLocationName(name).results.map {
            it.toLocation()
        }
    }

    override fun fetchEpisodeFilter(name: String?) = doRequest {
        service.fetchEpisodeName(name).results.map {
            it.toEpisode()
        }
    }
}