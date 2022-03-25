package com.example.data.network.remote.pagingsource

import com.example.data.network.remote.apiservices.EpisodeApiService
import com.example.data.network.remote.dtos.EpisodeModelDto
import com.example.data.network.remote.dtos.toEpisode
import com.example.data.network.remote.pagingsource.base.BasePagingSource
import com.example.domain.models.EpisodeModel
import javax.inject.Inject

class EpisodePagingSource @Inject constructor(
    private val service: EpisodeApiService,
    private val name: String?,
    private val episode: String?
) : BasePagingSource<EpisodeModelDto, EpisodeModel>(
    { service.fetchEpisode(it, name, episode) },
    { data -> data.map { it.toEpisode() } }
)