package com.example.data.repositories

import com.example.data.repositories.base.BaseRepository
import com.example.data.network.remote.apiservices.EpisodeApiService
import com.example.data.network.remote.dtos.toEpisode
import com.example.data.network.remote.pagingsource.EpisodePagingSource
import com.example.domain.repositories.EpisodeRepository
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
