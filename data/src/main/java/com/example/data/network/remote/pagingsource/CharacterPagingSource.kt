package com.example.data.network.remote.pagingsource

import com.example.data.network.remote.apiservices.CharacterApiService
import com.example.data.network.remote.dtos.CharacterModelDto
import com.example.data.network.remote.dtos.toCharacter
import com.example.data.network.remote.pagingsource.base.BasePagingSource
import com.example.domain.models.CharacterModel
import javax.inject.Inject

class CharacterPagingSource @Inject constructor(
    private val service: CharacterApiService,
    private val name: String?,
    private val status: String?,
    private val gender: String?
) : BasePagingSource<CharacterModelDto, CharacterModel>(
    { service.fetchCharacter(it, name, status, gender) },
    { data -> data.map { it.toCharacter() } }
)