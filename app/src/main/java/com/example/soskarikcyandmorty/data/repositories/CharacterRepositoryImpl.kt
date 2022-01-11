package com.example.soskarikcyandmorty.data.repositories

import com.example.soskarikcyandmorty.bases.BaseRepository
import com.example.soskarikcyandmorty.data.network.apiservices.CharacterApiService
import com.example.soskarikcyandmorty.data.network.dtos.toCharacter
import com.example.soskarikcyandmorty.data.repositories.pagingsource.CharacterPagingSource
import com.example.soskarikcyandmorty.domain.repositories.CharacterRepository
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