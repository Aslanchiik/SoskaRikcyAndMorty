package com.example.soskarikcyandmorty.domain.repositories

import com.example.soskarikcyandmorty.common.Resource
import com.example.soskarikcyandmorty.domain.models.CharacterModel
import kotlinx.coroutines.flow.Flow

interface CharacterRepository  {

    fun fetchCharacter(page : Int) : Flow<Resource<List<CharacterModel>>>
}