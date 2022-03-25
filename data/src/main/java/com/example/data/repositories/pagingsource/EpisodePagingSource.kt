package com.example.data.repositories.pagingsource

import com.example.core.bases.BasePagingSource
import com.example.data.network.apiservices.EpisodeApiService
import com.example.data.network.dtos.EpisodeModelDto
import com.example.data.network.dtos.toEpisode
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