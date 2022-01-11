package com.example.soskarikcyandmorty.data.repositories.pagingsource

import com.example.soskarikcyandmorty.bases.BasePagingSource
import com.example.soskarikcyandmorty.data.network.apiservices.CharacterApiService
import com.example.soskarikcyandmorty.data.network.dtos.CharacterModelDto
import com.example.soskarikcyandmorty.data.network.dtos.toCharacter
import com.example.soskarikcyandmorty.domain.models.CharacterModel
import javax.inject.Inject

class CharacterPagingSource @Inject constructor(
    private val service: CharacterApiService,
    private val name: String?,
    private val status: String?,
    private val gender: String?
): BasePagingSource<CharacterModelDto, CharacterModel>(
        { service.fetchCharacter(it, name, status, gender) },
        { data -> data.map { it.toCharacter() } }
)