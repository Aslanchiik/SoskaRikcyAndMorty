package com.example.soskarikcyandmorty.domain.usecase

import com.example.soskarikcyandmorty.domain.repositories.FilterRepository
import javax.inject.Inject

class FilterEpisodeUseCase @Inject constructor(
    private val repository: FilterRepository
) {
    operator fun invoke(name: String?) = repository.fetchEpisodeFilter(name)
}