package com.example.domain.usecase

import com.example.domain.repositories.EpisodeRepository
import javax.inject.Inject

class EpisodeDetailUseCase @Inject constructor(
    private val repository: EpisodeRepository
) {
    operator fun invoke(id: Int) = repository.fetchEpisodeId(id)
}