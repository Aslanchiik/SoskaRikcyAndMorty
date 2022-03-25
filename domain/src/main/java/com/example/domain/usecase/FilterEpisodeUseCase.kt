package com.example.domain.usecase

import com.example.domain.repositories.FilterRepository
import javax.inject.Inject

class FilterEpisodeUseCase @Inject constructor(
    private val repository: FilterRepository
) {
    operator fun invoke(name: String?) = repository.fetchEpisodeFilter(name)
}