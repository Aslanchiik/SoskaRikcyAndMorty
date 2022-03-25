package com.example.data.network.remote.apiservices

import com.example.data.constants.Constants.CHARACTER_AND_POINT
import com.example.data.constants.Constants.EPISODE_AND_POINT
import com.example.data.constants.Constants.LOCATION_AND_POINT
import com.example.data.network.remote.dtos.CharacterModelDto
import com.example.data.network.remote.dtos.EpisodeModelDto
import com.example.data.network.remote.dtos.LocationModelDto
import com.example.data.network.remote.dtos.RickyAndMortyResponseDto
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