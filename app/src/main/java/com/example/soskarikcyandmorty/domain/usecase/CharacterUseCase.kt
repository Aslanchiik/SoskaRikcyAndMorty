package com.example.soskarikcyandmorty.domain.usecase

import com.example.soskarikcyandmorty.domain.repositories.CharacterRepository
import javax.inject.Inject

class CharacterUseCase @Inject constructor(
    private val repository: CharacterRepository,
) {
    operator fun invoke(page : Int) = repository.fetchCharacter(page)
}