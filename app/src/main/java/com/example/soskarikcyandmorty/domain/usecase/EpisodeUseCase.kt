package com.example.soskarikcyandmorty.domain.usecase

import com.example.soskarikcyandmorty.domain.repositories.EpisodeRepository
import javax.inject.Inject

class EpisodeUseCase @Inject constructor(
    private val repository: EpisodeRepository
) {
    operator fun invoke(page : Int) = repository.fetchEpisode(page)
}