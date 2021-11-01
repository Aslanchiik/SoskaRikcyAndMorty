package com.example.soskarikcyandmorty.data.repositories

import com.example.soskarikcyandmorty.bases.BaseRepository
import com.example.soskarikcyandmorty.data.network.apiservices.EpisodeApiService
import com.example.soskarikcyandmorty.data.network.dtos.toEpisode
import com.example.soskarikcyandmorty.data.network.dtos.toResponse
import com.example.soskarikcyandmorty.domain.repositories.EpisodeRepository
import javax.inject.Inject

class EpisodeRepositoryImpl @Inject constructor(
    private val service: EpisodeApiService
) : BaseRepository(), EpisodeRepository {

    override fun fetchEpisode(page: Int) = doRequest {
        service.fetchEpisode(page).toResponse().results.map { it.toEpisode() }
    }
}
