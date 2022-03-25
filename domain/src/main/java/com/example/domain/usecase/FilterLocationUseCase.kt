package com.example.domain.usecase

import com.example.domain.repositories.FilterRepository
import javax.inject.Inject

class FilterLocationUseCase @Inject constructor(
    private val repository: FilterRepository
) {
    operator fun invoke(name: String?) = repository.fetchLLocationFilter(name)
}