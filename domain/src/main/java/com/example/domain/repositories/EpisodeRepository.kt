package com.example.domain.repositories

import com.example.domain.Resource
import com.example.domain.models.EpisodeModel
import kotlinx.coroutines.flow.Flow

interface EpisodeRepository {
    fun fetchEpisodeId(id: Int): Flow<Resource<EpisodeModel>>
}