package com.example.domain.usecase

import com.example.domain.repositories.CharacterRepository
import javax.inject.Inject

class CharacterDetailUseCase @Inject constructor(
    private val repository: CharacterRepository
) {
    operator fun invoke(id: Int) = repository.fetchCharacterID(id)
}