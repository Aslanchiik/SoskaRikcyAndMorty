package com.example.soskarikcyandmorty.domain.usecase

import com.example.soskarikcyandmorty.domain.repositories.CharacterRepository
import javax.inject.Inject

class CharacterDetailUseCase @Inject constructor(
    private val repository: CharacterRepository
) {
    operator fun invoke(id: Int) = repository.fetchCharacterID(id)
}