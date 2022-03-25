package com.example.domain.repositories

import com.example.domain.Resource
import com.example.domain.models.CharacterModel
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    fun fetchCharacterID(id: Int): Flow<Resource<CharacterModel>>
}