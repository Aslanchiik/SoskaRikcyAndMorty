package com.example.soskarikcyandmorty.data.repositories

import com.example.soskarikcyandmorty.bases.BaseRepository
import com.example.soskarikcyandmorty.data.network.apiservices.CharacterApiService
import com.example.soskarikcyandmorty.data.network.dtos.toCharacter
import com.example.soskarikcyandmorty.data.network.dtos.toResponse
import com.example.soskarikcyandmorty.domain.repositories.CharacterRepository
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val service: CharacterApiService
) : BaseRepository(), CharacterRepository {

    override fun fetchCharacter(page : Int) = doRequest {
        service.fetchCharacter(page).toResponse().results.map { it.toCharacter() }
    }
}