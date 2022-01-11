package com.example.soskarikcyandmorty.domain.usecase

import com.example.soskarikcyandmorty.domain.repositories.FilterRepository
import javax.inject.Inject

class FilterCharacterUseCase @Inject constructor(
    private val repository: FilterRepository
) {
    operator fun invoke(name: String?) = repository.fetchCharacterFilter(name)
}