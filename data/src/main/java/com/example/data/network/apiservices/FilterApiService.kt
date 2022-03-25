package com.example.data.network.apiservices

import com.example.core.utils.models.RickyAndMortyResponseDto
import com.example.data.network.dtos.CharacterModelDto
import com.example.data.network.dtos.EpisodeModelDto
import com.example.data.network.dtos.LocationModelDto
import com.example.soskarikcyandmorty.constants.Constants.CHARACTER_AND_POINT
import com.example.soskarikcyandmorty.constants.Constants.EPISODE_AND_POINT
import com.example.soskarikcyandmorty.constants.Constants.LOCATION_AND_POINT
import retrofit2.http.GET
import retrofit2.http.Query

interface FilterApiService {

    @GET(CHARACTER_AND_POINT)
    suspend fun filterCharacterName(
        @Query("name") nameOfCharacter: String?
    ): RickyAndMortyResponseDto<CharacterModelDto>

    @GET(LOCATION_AND_POINT)
    suspend fun fetchLocationName(
        @Query("name") nameOfLocation: String?
    ): RickyAndMortyResponseDto<LocationModelDto>

    @GET(EPISODE_AND_POINT)
    suspend fun fetchEpisodeName(
        @Query("name") nameOfEpisode: String?
    ): RickyAndMortyResponseDto<EpisodeModelDto>
}