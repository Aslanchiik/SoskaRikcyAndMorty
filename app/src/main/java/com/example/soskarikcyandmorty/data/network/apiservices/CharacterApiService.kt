package com.example.soskarikcyandmorty.data.network.apiservices

import com.example.soskarikcyandmorty.constants.Constants.CHARACTER_AND_POINT
import com.example.soskarikcyandmorty.data.network.dtos.CharacterModelDto
import com.example.soskarikcyandmorty.data.network.dtos.RickyAndMortyResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface CharacterApiService {

    @GET(CHARACTER_AND_POINT)
    suspend fun fetchCharacter(
        @Query("page") page: Int? = null,
    ) : RickyAndMortyResponseDto<CharacterModelDto>
}