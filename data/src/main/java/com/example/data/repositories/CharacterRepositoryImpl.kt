package com.example.data.repositories

import com.example.core.bases.BaseRepository
import com.example.data.network.dtos.toCharacter
import com.example.data.repositories.pagingsource.CharacterPagingSource
import com.example.data.network.apiservices.CharacterApiService
import com.example.domain.repositories.CharacterRepository
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val service: CharacterApiService,
) : BaseRepository(), CharacterRepository {

    fun fetchCharacter(name: String?, status: String?, gender: String?) =
        doPagingRequest(CharacterPagingSource(service, name, status, gender))

    override fun fetchCharacterID(id: Int) = doRequest {
        service.fetchCharacterId(id).toCharacter()
    }
}