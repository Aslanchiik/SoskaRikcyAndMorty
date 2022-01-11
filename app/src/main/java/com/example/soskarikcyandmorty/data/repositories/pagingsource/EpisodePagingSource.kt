package com.example.soskarikcyandmorty.data.repositories.pagingsource

import com.example.soskarikcyandmorty.bases.BasePagingSource
import com.example.soskarikcyandmorty.data.network.apiservices.EpisodeApiService
import com.example.soskarikcyandmorty.data.network.dtos.EpisodeModelDto
import com.example.soskarikcyandmorty.data.network.dtos.toEpisode
import com.example.soskarikcyandmorty.domain.models.EpisodeModel
import javax.inject.Inject

class EpisodePagingSource @Inject constructor(
    private val service: EpisodeApiService,
    private val name: String?,
    private val episode: String?
): BasePagingSource<EpisodeModelDto, EpisodeModel>(
        { service.fetchEpisode(it, name, episode) },
        { data -> data.map { it.toEpisode() } }
)