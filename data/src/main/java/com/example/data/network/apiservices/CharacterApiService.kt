package com.example.data.network.apiservices

import com.example.core.utils.models.RickyAndMortyResponseDto
import com.example.data.network.dtos.CharacterModelDto
import com.example.soskarikcyandmorty.constants.Constants.CHARACTER_AND_POINT
import com.example.soskarikcyandmorty.constants.Constants.CHARACTER_ID
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharacterApiService {

    @GET(CHARACTER_AND_POINT)
    suspend fun fetchCharacter(
        @Query("page") page: Int?,
        @Query("name") name: String?,
        @Query("status") status: String?,
        @Query("gender") gender: String?
    ): RickyAndMortyResponseDto<CharacterModelDto>

    @GET(CHARACTER_ID)
    suspend fun fetchCharacterId(
        @Path("id") id: Int?
    ): CharacterModelDto
}