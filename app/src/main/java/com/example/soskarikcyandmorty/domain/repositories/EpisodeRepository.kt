package com.example.soskarikcyandmorty.domain.repositories

import com.example.soskarikcyandmorty.common.Resource
import com.example.soskarikcyandmorty.domain.models.EpisodeModel
import kotlinx.coroutines.flow.Flow

interface EpisodeRepository {

    fun fetchEpisodeId(id: Int): Flow<Resource<EpisodeModel>>
}