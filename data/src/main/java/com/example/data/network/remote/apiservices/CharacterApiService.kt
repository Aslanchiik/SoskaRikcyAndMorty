package com.example.data.network.remote.apiservices

import com.example.data.constants.Constants.CHARACTER_AND_POINT
import com.example.data.constants.Constants.CHARACTER_ID
import com.example.data.network.remote.dtos.CharacterModelDto
import com.example.data.network.remote.dtos.RickyAndMortyResponseDto
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