package com.example.soskarikcyandmorty.data.repositories

import com.example.soskarikcyandmorty.bases.BaseRepository
import com.example.soskarikcyandmorty.data.network.apiservices.FilterApiService
import com.example.soskarikcyandmorty.data.network.dtos.toCharacter
import com.example.soskarikcyandmorty.data.network.dtos.toEpisode
import com.example.soskarikcyandmorty.data.network.dtos.toLocation
import com.example.soskarikcyandmorty.domain.repositories.FilterRepository
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