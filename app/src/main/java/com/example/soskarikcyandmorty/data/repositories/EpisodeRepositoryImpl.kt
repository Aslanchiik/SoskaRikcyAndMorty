package com.example.soskarikcyandmorty.data.repositories

import com.example.soskarikcyandmorty.bases.BaseRepository
import com.example.soskarikcyandmorty.data.network.apiservices.EpisodeApiService
import com.example.soskarikcyandmorty.data.network.dtos.toEpisode
import com.example.soskarikcyandmorty.data.repositories.pagingsource.EpisodePagingSource
import com.example.soskarikcyandmorty.domain.repositories.EpisodeRepository
import javax.inject.Inject

class EpisodeRepositoryImpl @Inject constructor(
    private val service: EpisodeApiService
) : BaseRepository(), EpisodeRepository {

    fun fetchEpisode(name: String?, episode: String?) =
        doPagingRequest(EpisodePagingSource(service, name, episode))

    override fun fetchEpisodeId(id: Int) = doRequest {
        service.fetchEpisodeId(id).toEpisode()
    }
}
