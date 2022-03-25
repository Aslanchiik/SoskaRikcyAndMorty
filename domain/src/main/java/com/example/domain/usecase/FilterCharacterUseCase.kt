package com.example.domain.usecase

import com.example.domain.repositories.FilterRepository
import javax.inject.Inject

class FilterCharacterUseCase @Inject constructor(
    private val repository: FilterRepository
) {
    operator fun invoke(name: String?) = repository.fetchCharacterFilter(name)
}