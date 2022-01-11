package com.example.soskarikcyandmorty.domain.usecase

import com.example.soskarikcyandmorty.domain.repositories.EpisodeRepository
import javax.inject.Inject

class EpisodeDetailUseCase @Inject constructor(
    private val repository: EpisodeRepository
) {
    operator fun invoke(id: Int) = repository.fetchEpisodeId(id)
}